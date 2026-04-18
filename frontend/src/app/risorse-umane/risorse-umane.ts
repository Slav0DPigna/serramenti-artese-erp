import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-risorse-umane',
  standalone: true,
  imports: [CommonModule, FormsModule, DatePipe],
  templateUrl: './risorse-umane.html',
  styleUrl: './risorse-umane.css'
})
export class RisorseUmaneComponent implements OnInit {
  dipendenti: any[] = [];
  permessi: any[] = [];
  
  isDirettore = false;
  mioProfilo: any = null;
  
  nuovaRichiesta = {
    dataInizio: '',
    dataFine: '',
    motivazione: '',
    richiedenteCf: null as string | null
  };

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef, private keycloak: KeycloakService) {
      const roles = this.keycloak.getUserRoles();
      this.isDirettore = roles.includes('direttore');
  }

  ngOnInit() {
    this.loadDipendenti();
  }

  loadDipendenti() {
    this.http.get<any[]>('http://localhost:8080/api/dipendenti').subscribe(
      (res) => { 
          this.dipendenti = res; 
          
          if (!this.isDirettore) {
              try {
                  const kc = this.keycloak.getKeycloakInstance();
                  const uName = kc.tokenParsed?.['preferred_username'] || kc.subject;
                  this.mioProfilo = this.dipendenti.find(d => d.email === uName);
                  if (this.mioProfilo) {
                      this.nuovaRichiesta.richiedenteCf = this.mioProfilo.cf;
                  }
              } catch(e) { console.error('Could not determine username', e); }
          }
          // Concateno per evitare race condition: ora che so chi sono, posso caricare i permessi filtrati
          this.loadPermessi();
      },
      (err) => console.error(err)
    );
  }

  loadPermessi() {
    this.http.get<any[]>('http://localhost:8080/api/permessi').subscribe(
      (res) => { 
          if (this.isDirettore) {
              this.permessi = res;
          } else {
              if (this.mioProfilo) {
                  this.permessi = res.filter(p => p.richiedente && p.richiedente.cf === this.mioProfilo.cf);
              } else {
                  this.permessi = [];
              }
          }
          this.cdr.detectChanges(); 
      },
      (err) => console.error(err)
    );
  }

  richiediPermesso() {
    if (!this.nuovaRichiesta.richiedenteCf || !this.nuovaRichiesta.dataInizio || !this.nuovaRichiesta.motivazione) {
      alert("Compila tutti i campi obbligatori (Identità, Data, Motivazione)!");
      return;
    }
    const payload = {
       dataInizio: this.nuovaRichiesta.dataInizio,
       dataFine: this.nuovaRichiesta.dataFine,
       motivazione: this.nuovaRichiesta.motivazione,
       validato: null,
       richiedente: { cf: this.nuovaRichiesta.richiedenteCf }
    };
    
    this.http.post('http://localhost:8080/api/permessi', payload).subscribe({
      next: () => {
         this.nuovaRichiesta = { dataInizio: '', dataFine: '', motivazione: '', richiedenteCf: this.nuovaRichiesta.richiedenteCf };
         this.loadPermessi();
      },
      error: (err) => console.error(err)
    });
  }

  valutaPermesso(id: number, approvato: boolean) {
    this.http.put(`http://localhost:8080/api/permessi/${id}/valuta`, { validato: approvato }).subscribe({
       next: () => { this.loadPermessi(); },
       error: (err) => console.error(err)
    });
  }
}
