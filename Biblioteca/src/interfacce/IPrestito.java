package interfacce;

import java.time.LocalDateTime;

import exception.BibliotecaException;
import flusso.Biblioteca;
import flusso.OggettoBibliotecario;
import flusso.Prestito;
import flusso.Utente;

public interface IPrestito {

	public static final int MAX_DOCUMENTARI_IN_PRESTITO = 1;
	public static final int MAX_LIBRI_IN_PRESTITO = 2;
	public static final int MAX_RIVISTE_IN_PRESTITO = 5;

	public static final LocalDateTime DATA_DI_ATTIVAZIONE = LocalDateTime.now();
	public static final LocalDateTime DATA_DI_RESTITUZIONE = null; // variabile che potrebbe essere utilizzata nel caso si decidesse di gestire le restituzioni
	public static final int DURATA_DEL_PRESTITO = 20;
	public static final LocalDateTime DATA_DI_SCADENZA = DATA_DI_ATTIVAZIONE.plusDays(DURATA_DEL_PRESTITO);
//DATA_DI_ATTIVAZIONE.minusDays(DURATA_DEL_PRESTITO) per testare i prestiti scaduti sostituirlo all'imprementazione di DATA_DI_SCADENZA


	public void presta() throws BibliotecaException;

	public boolean controlloCopieDisponibili(OggettoBibliotecario oggetto) throws BibliotecaException;

	/*
	 * effettua il controllo sull'utente valutando se puo' prendere in prestito oggetti dalla biblioteca
	 * @input l'utente da controllare
	 * @exception BibliotecaException viene lanciata se l'utente ha un prestito scaduto o se ha superato il limite di prestiti per libri,documentari e riviste
	 */
	public static boolean controlloPrestitoUtente(Utente utente) throws BibliotecaException {

		for (Prestito p : Biblioteca.getPrestiti()) {
			if (p.getUtente().equals(utente)) {
				if (p.DATA_DI_SCADENZA.isBefore(LocalDateTime.now())) {
					throw new BibliotecaException(utente.getCognome().toUpperCase() + " " + utente.getNome().toUpperCase() + " HA UN PRESTITO SCADUTO,"
							+ "DEVE RESTITUIRE " + p.getOggetto().getNome() );
				}
			}

		}
		// limite di documentari che e' possibile prendere in prestito meno i dvd presi in prestito dall'utente
		int documentari = MAX_DOCUMENTARI_IN_PRESTITO - utente.getDVDInPrestito(); 
		// limite di libri che e' possibile prendere in prestito meno i libri presi in prestito dall'utente
		int libri = MAX_LIBRI_IN_PRESTITO - utente.getLibriInPrestito();
		// limite di riviste che e' possibile prendere in prestito meno le riviste prese in prestito dall'utente
		int riviste = MAX_RIVISTE_IN_PRESTITO - utente.getRivisteInPrestito();
		// se l'utente puo' effettuare prestiti stampa quanti e quali oggetti puo' prendere in prestito
		if (documentari >= 0 || libri >= 0 || riviste >= 0) {
			System.out.println(
					"L'UTENTE " + utente.getCognome() + " " + utente.getNome() + " PUO ' PRENDERE IN PRESTITO");
			System.out.println(documentari + " DOCUMENTARIO ");
			System.out.println(libri + " LIBRI ");
			System.out.println(riviste + " RIVISTE ");
			return true;
		} else {
			throw new BibliotecaException("L'UTENTE NON PUO' EFFETTUARE PRESTITI");
		}
	}

}
