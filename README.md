# Serramenti Artese - README

Questa è l'applicazione di ERP per l'impesa Serramenti Artese protetta con Single-Sign On e RBAC (Role-Based Access Control) interamente orchestrati tramite **Keycloak**.

## Prerequisiti
Assicurati di avere [Docker](https://www.docker.com/) in esecuzione sul terminale e [Maven] installato o pronto nel folder backend.

## 1. Avviare Keycloak 

L'applicazione e il file script automatizzato `./start.sh` presumono che il servizio Identity Provider sia *già avviato e pronto*.
Apri il terminale del backend e digita:
```bash
docker-compose up -d
```
Verifica che Keycloak sia avviato con successo all'indirizzo http://localhost:8180 (le credenziali dell'Admin globale Keycloak sono `admin:admin`).

## 2. Avviare la Piattaforma

Per avviare l'intero sistema, dovrai aprire due terminali separati partendo dalla cartella radice del progetto (`serramenti artese due`).

**Terminal 1 (Backend - Java Spring Boot in Modalità Sviluppo):**
Spostati nella cartella del backend e avvia il server sulla porta 8080. Il comando `spring-boot:run` fa partire l'ambiente di sviluppo in modo tale da ricaricare le modifiche in automatico.
```bash
cd backend
./mvnw spring-boot:run
```

**Terminal 2 (Frontend - Angular in Modalità Sviluppo):**
Spostati nella cartella del frontend e avvia la Web App sulla porta 4200. Il comando fa avviare il dev server di Angular con *Live Reload* attivato (per cui ogni volta che salvi un file visivo, la pagina web si aggiornerà da sola).
```bash
cd frontend
npm start
```
*(L'applicazione sarà accessibile all'indirizzo http://localhost:4200 e si connetterà in automatico a Keycloak su localhost:8180).*

## Tabella UTENTI per Verifiche Interfaccia

Una volta all'indirizzo [http://localhost:4200](http://localhost:4200), sarai forzato a visualizzare la schermata Login dell'organizzazione "Serramenti Artese SSO".

Puoi provare i ruoli loggandoti con uno tra i seguenti test base già configurati (e importati grazie a `realm-export.json`):

| Username | Password | Ruolo in Piattaforma | Scopo Interfaccia Navigabile |
| -------- | -------- | ---------------- | ---------------------------- |
| **admin** | **admin** | **DIRETTORE** | Visualizza tutto, accede a tutte le schede (Risorse Umane, Magazzino, Ordini, Catalogo) |
| **giovanni.vendite** | **1234** | **ADDETTO VENDITE** | Gestisce clienti, preventivi e ordini (Non ha accesso al Magazzino logistico e HR). |
| **luigi.magazziniere** | **1234** | **MAGAZZINIERE** | Pieno accesso alla gestione scorte del Magazzino (aggiunge materiali, elabora invii ordini). |
| **marco.produzione** | **1234** | **ADDETTO PRODUZIONE** | Visualizza gli ordini in coda e decurta scorte in Magazzino per usarle (non fa rifornimenti). |
| **giulia.cliente** | **1234** | **CLIENTE** | Accede al catalogo e al proprio storico di Ordini. |
