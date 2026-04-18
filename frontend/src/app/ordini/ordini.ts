import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, DatePipe, CurrencyPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-ordini',
  standalone: true,
  imports: [CommonModule, DatePipe, CurrencyPipe, FormsModule],
  templateUrl: './ordini.html',
  styleUrl: './ordini.css'
})
export class OrdiniComponent implements OnInit {
  ordini: any[] = [];
  clienti: any[] = [];
  prodotti: any[] = [];

  isDirettore = false;
  isAddettoProduzione = false;
  isAddettoVendite = false;
  
  showForm = false;

  nuovoOrdine = {
    clienteId: null,
    indirizzoConsegna: '',
    installazionePrevista: false,
    useCustom: false,
    prodottoSelezionato: null,
    nomePersonalizzato: '',
    quantita: 1,
    misure: '',
    prezzoPattuito: 0
  };

  nuovaAzioneCliente = 'esistente'; // 'esistente' o 'nuovo'
  
  nuovoCliente = {
    nome: '',
    email: '',
    telefono: '',
    indirizzo: '',
    piva: ''
  };

  statiDisponibili = ['Preso in carico', 'In lavorazione', 'Pronto per la consegna', 'Spedito', 'Consegnato'];

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef, private keycloak: KeycloakService) {
    const roles = this.keycloak.getUserRoles();
    this.isDirettore = roles.includes('direttore');
    this.isAddettoProduzione = roles.includes('addetto_produzione');
    this.isAddettoVendite = roles.includes('addetto_vendite');
  }

  ngOnInit() {
    this.loadOrdini();
    if (this.isDirettore || this.isAddettoVendite) {
      this.loadClienti();
      this.loadProdotti();
    }
  }
  
  loadOrdini() {
    this.http.get<any[]>('http://localhost:8080/api/ordini').subscribe({
      next: (res) => { this.ordini = res; this.cdr.detectChanges(); },
      error: (err) => console.error(err)
    });
  }

  loadClienti() {
    this.http.get<any[]>('http://localhost:8080/api/clienti').subscribe(res => { this.clienti = res; this.cdr.detectChanges(); });
  }

  loadProdotti() {
    this.http.get<any[]>('http://localhost:8080/api/prodotti').subscribe(res => { this.prodotti = res; this.cdr.detectChanges(); });
  }

  creaOrdine() {
    if (this.nuovaAzioneCliente === 'nuovo') {
       if (!this.nuovoCliente.nome) { alert("Inserisci almeno il nome del nuovo cliente"); return; }
       this.http.post<any>('http://localhost:8080/api/clienti', this.nuovoCliente).subscribe({
          next: (savedCliente) => {
              this.nuovoOrdine.clienteId = savedCliente.id;
              this.loadClienti(); // Aggiorna a schermo casomai serva post-chiusura
              this.eseguiFlussoCreazioneOrdine();
          },
          error: (err) => { console.error(err); alert("Errore durante il censimento del nuovo cliente"); }
       });
    } else {
       if (!this.nuovoOrdine.clienteId) { alert("Seleziona un cliente esistente dalla lista"); return; }
       this.eseguiFlussoCreazioneOrdine();
    }
  }

  private eseguiFlussoCreazioneOrdine() {
    // 1. Create main order payload
    const ordinePayload = {
      dataAcquisto: new Date().toISOString().split('T')[0],
      indirizzoConsegna: this.nuovoOrdine.indirizzoConsegna,
      stato: 'Preso in carico',
      installazionePrevista: this.nuovoOrdine.installazionePrevista,
      importoTotale: this.nuovoOrdine.prezzoPattuito,
      cliente: { id: this.nuovoOrdine.clienteId }
    };

    // 2. HTTP POST Ordine
    this.http.post<any>('http://localhost:8080/api/ordini', ordinePayload).subscribe({
      next: (savedOrdine) => {
        // 3. HTTP POST ProdottoVenduto
        const pvPayload: any = {
           ordine: { id: savedOrdine.id },
           quantita: this.nuovoOrdine.quantita,
           misure: this.nuovoOrdine.misure,
           prezzoPattuito: this.nuovoOrdine.prezzoPattuito
        };

        if (this.nuovoOrdine.useCustom) {
           pvPayload.nomePersonalizzato = this.nuovoOrdine.nomePersonalizzato;
        } else {
           if(this.nuovoOrdine.prodottoSelezionato) {
             pvPayload.prodotto = { nome: this.nuovoOrdine.prodottoSelezionato };
           }
        }

        this.http.post('http://localhost:8080/api/prodottivenduti', pvPayload).subscribe({
          next: () => {
             this.showForm = false;
             this.nuovoOrdine = { clienteId: null, indirizzoConsegna: '', installazionePrevista: false, useCustom: false, prodottoSelezionato: null, nomePersonalizzato: '', quantita: 1, misure: '', prezzoPattuito: 0 };
             this.nuovoCliente = { nome: '', email: '', telefono: '', indirizzo: '', piva: '' };
             this.nuovaAzioneCliente = 'esistente';
             this.loadOrdini();
          },
          error: (err) => { console.error(err); alert("Impossibile salvare il pezzo nell'ordine"); }
        });
      },
      error: (err) => { console.error(err); alert("Impossibile generare intestazione Ordine"); }
    });
  }

  aggiornaStato(o: any) {
    this.http.post('http://localhost:8080/api/ordini', o).subscribe({
        next: () => { this.loadOrdini(); },
        error: (err) => console.error(err)
    });
  }
}
