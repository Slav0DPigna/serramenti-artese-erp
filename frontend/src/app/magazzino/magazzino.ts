import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-magazzino',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './magazzino.html',
  styleUrl: './magazzino.css'
})
export class MagazzinoComponent implements OnInit {
  materie: any[] = [];
  isDirettore = false;
  isMagazziniere = false;

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef, private keycloak: KeycloakService) {
    const roles = this.keycloak.getUserRoles();
    this.isDirettore = roles.includes('direttore');
    this.isMagazziniere = roles.includes('magazziniere');
  }

  ngOnInit() {
    this.loadMaterie();
  }

  loadMaterie() {
    this.http.get<any[]>('http://localhost:8080/api/materieprime').subscribe(
      (res) => { 
        this.materie = res.map((m: any) => ({ ...m, quantitaInput: 1 })); 
        this.cdr.detectChanges(); 
      },
      (err) => console.error(err)
    );
  }

  usaMateria(materia: any) {
    const qta = Number(materia.quantitaInput);
    if (Number.isInteger(qta) && qta > 0) {
      if (materia.quantitaDisponibile >= qta) {
        const payload = { ...materia };
        delete payload.quantitaInput;
        payload.quantitaDisponibile -= qta;

        materia.quantitaDisponibile -= qta;
        materia.quantitaInput = 1;
        this.cdr.detectChanges();
        
        this.http.post('http://localhost:8080/api/materieprime', payload).subscribe({
          next: () => this.loadMaterie(),
          error: (err) => {
            console.error(err);
            materia.quantitaDisponibile += qta; // rollback
            this.cdr.detectChanges();
          }
        });
      } else {
        alert('Quantità non sufficiente in magazzino.');
      }
    } else {
      alert('Inserisci una quantità valida (numero intero maggiore di zero).');
    }
  }

  aggiungiMateria(materia: any) {
    const qta = Number(materia.quantitaInput);
    if (Number.isInteger(qta) && qta > 0) {
      const payload = { ...materia };
      delete payload.quantitaInput;
      payload.quantitaDisponibile += qta;

      materia.quantitaDisponibile += qta;
      materia.quantitaInput = 1;
      this.cdr.detectChanges();
      
      this.http.post('http://localhost:8080/api/materieprime', payload).subscribe({
        next: () => this.loadMaterie(),
        error: (err) => {
          console.error(err);
          materia.quantitaDisponibile -= qta; // rollback
          this.cdr.detectChanges();
        }
      });
    } else {
      alert('Inserisci una quantità valida (numero intero maggiore di zero).');
    }
  }
}
