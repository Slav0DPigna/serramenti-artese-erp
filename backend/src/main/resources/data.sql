-- Creazione tabella di log per l'esaurimento scorte
CREATE TABLE IF NOT EXISTS log_scorte (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    materia_nome TEXT,
    quantita_precedente INTEGER,
    quantita_nuova INTEGER,
    data_segnalazione DATETIME DEFAULT CURRENT_TIMESTAMP
);;

-- Trigger che scatta dopo l'aggiornamento della quantità sulla Materia Prima
CREATE TRIGGER IF NOT EXISTS trigger_esaurimento_scorte
AFTER UPDATE OF quantita_disponibile ON materia_prima
WHEN NEW.quantita_disponibile <= NEW.soglia_minima
BEGIN
    INSERT INTO log_scorte (materia_nome, quantita_precedente, quantita_nuova)
    VALUES (NEW.nome, OLD.quantita_disponibile, NEW.quantita_disponibile);
END;;
