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

  nuovoDipendente = {
    cf: '',
    nome: '',
    ruolo: 'Addetto Produzione',
    email: '',
    telefono: '',
    stipendio: 1500,
    oreLavorate: 0,
    ferieResidue: 20
  };

  valutaPermesso(id: number, approvato: boolean) {
    this.http.put(`http://localhost:8080/api/permessi/${id}/valuta`, { validato: approvato }).subscribe({
       next: () => { this.loadPermessi(); },
       error: (err) => console.error(err)
    });
  }

  assumiDipendente() {
    if (!this.nuovoDipendente.cf || !this.nuovoDipendente.nome || !this.nuovoDipendente.email) {
      alert("Codice Fiscale, Nome ed Email sono obbligatori per l'assunzione!");
      return;
    }

    this.nuovoDipendente.cf = this.nuovoDipendente.cf.toUpperCase().trim();
    const cfRegex = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/;
    if (!cfRegex.test(this.nuovoDipendente.cf)) {
      alert("Formato Codice Fiscale non valido! Deve essere di 16 caratteri alfanumerici (es. RSSMRA80A01H501Z).");
      return;
    }
    
    this.http.post('http://localhost:8080/api/dipendenti', this.nuovoDipendente).subscribe({
      next: () => {
         this.nuovoDipendente = { cf: '', nome: '', ruolo: 'Addetto Produzione', email: '', telefono: '', stipendio: 1500, oreLavorate: 0, ferieResidue: 20 };
         this.loadDipendenti();
      },
      error: (err) => {
         console.error(err);
         alert("Errore durante l'assunzione. Verifica che il CF o l'Email non siano già in uso.");
      }
    });
  }

  licenziaDipendente(cf: string, nome: string) {
    if(confirm(`Sei sicuro di voler terminare il contratto di ${nome} (${cf})? L'operazione è irreversibile.`)) {
      this.http.delete(`http://localhost:8080/api/dipendenti/${cf}`).subscribe({
        next: () => { this.loadDipendenti(); },
        error: (err) => {
           console.error(err);
           alert("Impossibile licenziare. Verifica se ci sono permessi o altre entità collegate a questo dipendente.");
        }
      });
    }
  }

  modificaStipendio(cf: string, stipendioAttuale: number, variazione: number) {
    const nuovoStipendio = stipendioAttuale + variazione;
    if (nuovoStipendio < 500) {
      alert("Operazione negata. Lo stipendio base non può scendere sotto i 500€ mensili.");
      return;
    }
    this.http.put(`http://localhost:8080/api/dipendenti/${cf}/stipendio`, { stipendio: nuovoStipendio }).subscribe({
      next: () => { this.loadDipendenti(); },
      error: (err) => console.error(err)
    });
  }
}
