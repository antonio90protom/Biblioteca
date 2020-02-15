package flusso;

import exception.BibliotecaException;
import interfacce.IPrestito;

public class Prestito implements IPrestito {

	private OggettoBibliotecario oggetto;
	private Utente utente;
	
	public Prestito(OggettoBibliotecario oggettoBibliotecario, Utente utente) {
		this.oggetto = oggettoBibliotecario;
		this.utente = utente;
	}

	public OggettoBibliotecario getOggetto() {
		return oggetto;
	}

	public void setOggetto(OggettoBibliotecario oggetto) {
		this.oggetto = oggetto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	
	
	/*
	 *  il metodo si occupa di prestare la copia ad un determinato utente
	 * @exception BibliotecaException viene lanciata se l'utente ha preso in prestito troppi documentari,riviste o libri o se l'oggetto non ha copie disponibili
	 * @output se non vengono lanciate eccezioni avvisa che il prestito e' stato effettuato e stampa la data di scadenza	  
	 */
	public void presta() throws BibliotecaException {
		

			controlloCopieDisponibili(oggetto);

			
			if (oggetto instanceof Documentario) {
				if (utente.getDVDInPrestito() < MAX_DOCUMENTARI_IN_PRESTITO) {

					utente.incrementaDVDInPrestito();
				
				} else {
					throw new BibliotecaException("TROPPI DOCUMENTARI IN PRESTITO");
				}

			} else if (oggetto instanceof Rivista) {

				if (utente.getRivisteInPrestito() < MAX_RIVISTE_IN_PRESTITO) {

					utente.incrementaRivisteInPrestito();
					
				} else {
					throw new BibliotecaException("TROPPE RIVISTE IN PRESTITO");
				}

			} else {
				if (utente.getLibriInPrestito() < MAX_LIBRI_IN_PRESTITO) {

					utente.incrementaLibriInPrestito();
					
				} else {
					throw new BibliotecaException("TROPPI LIBRI IN PRESTITO");
				}
			}

			Biblioteca.addPrestito(this);
			System.out.println("PRESTITO EFFETTUATO");
			System.out.println("DATA DI SCANDENZA : " + DATA_DI_SCADENZA);
			oggetto.decrementaCopieDisponibili();
			oggetto.incrementaCopieInPrestito();
		}

	

/*
 * controlla se l'oggetto passato in input ha copie disponibili
 * @input l'oggetto da controllare
 * @exception BibliotecaException viene lanciata se non ci sono copie disponibili per quell'oggetto
 */
	public boolean controlloCopieDisponibili(OggettoBibliotecario oggetto) throws BibliotecaException {

		if (oggetto.getCopieDisponibili() > 0) {
			return true;
		} else {
			throw new BibliotecaException("COPIE NON DISPONIBILI");
		}
	}

	

}
