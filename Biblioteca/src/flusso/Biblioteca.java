package flusso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import exception.BibliotecaException;
import interfacce.IGestioneCopie;
import interfacce.IGestioneUtenti;
import utility.UtilityComparatore;
import utility.UtilityIO;

public class Biblioteca implements IGestioneUtenti, IGestioneCopie {

	private static Set<Prestito> prestiti; // contiene tutti i prestiti effettuati
	private HashMap<Integer, OggettoBibliotecario> oggettiInBiblioteca; // contiene tutti gli oggetti della biblioteca
	private ArrayList<Libro> libri; // contiene tutti i libri della biblioteca
	private ArrayList<Documentario> documentari; // contiene tutti i documentari della biblioteca
	private ArrayList<Rivista> riviste; // contiene tutti le riviste in biblioteca
	private ArrayList<Utente> utentiRegistrati; // contiene tutti gli utenti registrati in biblioteca
	private int id; // identificatore utilizzato per gli oggetti in biblioteca

	public Biblioteca() {
		prestiti = new HashSet<Prestito>();
		oggettiInBiblioteca = new HashMap<Integer, OggettoBibliotecario>();
		utentiRegistrati = new ArrayList<Utente>();
		libri = new ArrayList<Libro>();
		documentari = new ArrayList<Documentario>();
		riviste = new ArrayList<Rivista>();
	}

	public ArrayList<Libro> getLibri() {
		return libri;
	}

	public ArrayList<Documentario> getDocumentari() {
		return documentari;
	}

	public ArrayList<Rivista> getRiviste() {
		return riviste;
	}

	public HashMap<Integer, OggettoBibliotecario> getOggettiInBiblioteca() {
		return oggettiInBiblioteca;
	}

	public ArrayList<Utente> getUtentiRegistrati() {
		return utentiRegistrati;
	}

	public void setUtentiRegistrati(ArrayList<Utente> utentiRegistrati) {
		this.utentiRegistrati = utentiRegistrati;
	}

	public static Set<Prestito> getPrestiti() {
		return prestiti;
	}

	/*
	 * @input prende in input un prestito lo aggiunge alla lista di prestiti
	 */
	public static void addPrestito(Prestito prestiti) {
		Biblioteca.prestiti.add(prestiti);
	}

	/*
	 * @exception BibliotecaException viene lanciata se la lista prestiti e' vuota
	 * 
	 * setta true alla variabile prestitiScaduti a tutti gli utenti che hanno almeno
	 * un prestito scaduto
	 */
	@Override
	public void verificaUtenteConPrestitoScaduto() throws BibliotecaException {
		if (Biblioteca.prestiti.size() == 0) {
			throw new BibliotecaException("NESSUN PRESTITO EFFETTUATO");
		} else {
			for (Prestito prestito : Biblioteca.prestiti) {
				if (prestito.DATA_DI_SCADENZA.isBefore(LocalDateTime.now())) {
					prestito.getUtente().setPrestitiScaduti(true);
				}
			}
		}
	}

	/*
	 * @exception BibliotecaException viene lanciata se la lista prestiti e' vuota o
	 * se nessun utente ha un prestito scaduto
	 * 
	 * @output stampa tutti gli utenti con un prestito scaduto richiamando il metodo
	 * verificaUtenteConPrestitoScaduto() e se trova almeno un prestito scaduto
	 * setta la booleana a false,altrimenti lancia un eccezione
	 */
	@Override
	public void stampaUtentiConPrestitiScaduti() throws BibliotecaException {
		verificaUtenteConPrestitoScaduto();
		boolean nessunUtenteConPrestitoScaduto = true;
		for (Prestito prestito : Biblioteca.prestiti) {
			if (prestito.getUtente().isPrestitiScaduti() == true) {
				System.out.println(prestito.getUtente());
				nessunUtenteConPrestitoScaduto = false;
			}
		}

		if (nessunUtenteConPrestitoScaduto) {
			throw new BibliotecaException("NESSUN UTENTE CON PRESTITO SCADUTO TROVATO");
		}

	}

	/*
	 * effettua l'ordine degli utenti per cognome sfruttando la classe statica
	 * ComparaPerCognome presente in UtilityComparatore
	 */
	@Override
	public void ordinaUtentiPerCognome() {

		Collections.sort(utentiRegistrati, new UtilityComparatore.ComparaPerCognome());

	}

	/*
	 *
	 * @exception BibliotecaException lancia eccezione se non trova alcun utente
	 * registrato
	 * 
	 * @output stampa gli utenti per cognome 
	 * 
	 * effettua l'ordinamento per cognome della lista utentiRegistrati e li stampa
	 */
	@Override
	public void stampaUtentiPerCognome() throws BibliotecaException {
		if (this.utentiRegistrati.size() == 0) {
			throw new BibliotecaException("NESSUN UTENTE REGISTRATO");
		} else {
			System.out.println("UTENTI ORDINATI PER COGNOME");
			ordinaUtentiPerCognome();
			for (Utente utente : utentiRegistrati) {
				System.out.println(utente);
			}
		}
	}

	/*
	 * @exception BibliotecaException viene lanciata se non ci sono copie
	 * disponibili
	 * 
	 * effettua l'ordinamento dei libri,documentari e riviste per nome
	 */
	@Override
	public void ordinaCopiePerNome() throws BibliotecaException {

		if (oggettiInBiblioteca.isEmpty()) {
			throw new BibliotecaException("NESSUNA COPIA DISPONIBILE");
		} else {
			Collections.sort(libri, new UtilityComparatore.ComparaCopiePerNome());
			Collections.sort(documentari, new UtilityComparatore.ComparaCopiePerNome());
			Collections.sort(riviste, new UtilityComparatore.ComparaCopiePerNome());
		}

	}

	/*
	 * @input prende input la categoria da stampare
	 * 
	 * @output stampa le copie per categoria
	 * 
	 * @exception BibliotecaException viene lanciata se la biblioteca e' vuota,se la
	 * categoria inserita e' vuota o non valida, o se non ci sono libri o
	 * documentari o riviste in biblioteca
	 * 
	 * 
	 * se non si verificano eccezioni stampa le copie in base ad una categoria data
	 * in input,la variabile nonTrovato controlla se e' disponibile almeno una copia
	 * di quella categoria. In base alla categoria si esegue il ciclo su quella
	 * determinata lista di copie e vengono stampate le copie disponibili
	 * 
	 */
	@Override
	public void stampaCopiePerCategoria(String categoria) throws BibliotecaException {

		if (libri.isEmpty() && documentari.isEmpty() && riviste.isEmpty()) {
			throw new BibliotecaException("BIBLIOTECA VUOTA");
		} else {
			boolean nonTrovato = true;
			switch (categoria) {
			case "libro":
				if (libri.isEmpty()) {
					throw new BibliotecaException("NESSUN LIBRO DISPONIBILE");
				}

				for (Libro libro : libri) {

					if (libro.getCopieDisponibili() > 0) {
						nonTrovato = false;
						System.out.println(libro.toString());
					}
				}
				break;
			case "documentario":
				if (documentari.isEmpty()) {
					throw new BibliotecaException("NESSUN DOCUMENTARIO DISPONIBILE");
				}

				for (Documentario documentario : documentari) {
					if (documentario.getCopieDisponibili() > 0) {
						nonTrovato = false;

						System.out.println(documentario.toString());
					}
				}
				break;
			case "rivista":
				if (riviste.isEmpty()) {
					nonTrovato = false;
					throw new BibliotecaException("NESSUNA RIVISTA DISPONIBILE");
				} else {

					for (Rivista rivista : riviste) {
						if (rivista.getCopieDisponibili() > 0) {
							nonTrovato = false;
							System.out.println(rivista.toString());
						}
					}
				}
				break;
			default:
				throw new BibliotecaException("CATEGORIA NON VALIDA");
			}
			if (nonTrovato) {
				throw new BibliotecaException("IL REPARTO " + categoria.toUpperCase() + " E' VUOTO ");
			}
		}
	}

	/*
	 * @output stampa le copie per nome
	 * 
	 * @exception BibliotecaException lancia eccezione se nessuna copia e'
	 * disponibile 
	 * 
	 * ordina le copie per nome e le stampa per categoria
	 */

	@Override
	public void stampaCopiePerNome() throws BibliotecaException {
		ordinaCopiePerNome();
		System.out.println("COPIE DISPONIBILI ORDINATE PER NOME");
		UtilityIO.stampaPerCategoria(riviste, documentari, libri);
	}

	/*
	 * @exception BibliotecaException viene lanciata se nessuna copia e' disponibile
	 * sfrutta il comparator presente nella classe ComparaCopiePerData in
	 * UtilityComparatore, per ordinare le copie per data
	 */

	@Override
	public void ordinaCopiePerData() throws BibliotecaException {

		if (oggettiInBiblioteca.isEmpty()) {
			throw new BibliotecaException("NESSUNA COPIA DISPONIBILE");
		} else {
			Collections.sort(libri, new UtilityComparatore.ComparaCopiePerData());
			Collections.sort(documentari, new UtilityComparatore.ComparaCopiePerData());
			Collections.sort(riviste, new UtilityComparatore.ComparaCopiePerData());
		}

	}

	/*
	 * @output stampa le copie per data
	 * 
	 * @exception BibliotecaException viene lanciata se non ci sono copie disponibili 
	 * 
	 * ordina le copie per data e le stampa per categoria sfruttando le
	 * rispettive funzioni
	 */
	@Override
	public void stampaCopiePerData() throws BibliotecaException {
		ordinaCopiePerData();
		System.out.println("\nCOPIE DISPONIBILI ORDINATE PER DATA");
		UtilityIO.stampaPerCategoria(riviste, documentari, libri);
	}

	/*
	 * @output stampa la copia specificando se e' stata inserita o meno
	 * 
	 * @exception BibliotecaException viene lanciata se nessun oggetto e' stato
	 * passato in input
	 * 
	 * @input viene passato un var args di OggettoBibliotecario
	 * 
	 * inserisce gli oggetti in biblioteca aggiunge l'oggetto o gli oggetti alla
	 * lista oggettiInBiblioteca e in base all'istanza anche alla rispettiva lista
	 */

	@Override
	public void inserisciInBiblioteca(OggettoBibliotecario... oggettoBibliotecario) throws BibliotecaException {
		if (oggettoBibliotecario.length == 0) {
			throw new BibliotecaException("NESSUN OGGETTO INSERITO IN BIBLIOTECA");
		} else {
			for (OggettoBibliotecario oggettoDaInserire : oggettoBibliotecario) {
				if (oggettiInBiblioteca.containsValue(oggettoDaInserire)) {
					System.out
							.println("LA COPIA " + oggettoDaInserire + " NON E' STATA INSERITA,SICCOME GIA' PRESENTE");
				} else {
					oggettiInBiblioteca.put(++id, oggettoDaInserire);

					System.out.println("COPIA INSERITA : " + oggettoDaInserire);
					if (oggettoDaInserire instanceof Libro) {
						libri.add((Libro) oggettoDaInserire);
						oggettoDaInserire.setId(libri.size());
					} else if (oggettoDaInserire instanceof Documentario) {
						documentari.add((Documentario) oggettoDaInserire);
						oggettoDaInserire.setId(documentari.size());
					} else {
						riviste.add((Rivista) oggettoDaInserire);
						oggettoDaInserire.setId(riviste.size());
					}

				}
			}
		}
	}

	/*
	 * @input nuovo utente da registrare
	 * 
	 * @output se l'utente non e' gia presente stampa utente gia' inserito
	 * 
	 * @exception BibliotecaException viene lanciata se l'utente e' gia' presente
	 * 
	 * registra un nuovo utente sfruttando la funzione che si occupa di controllare
	 * se l'utente e' presente nella lista di utenti e setta l'id dell'utente uguale
	 * al numero di elementi della lista utentiRegistrati
	 */
	@Override
	public void registraNuovoUtente(Utente nuovoUtente) throws BibliotecaException {
		boolean trovato = controlloInserimentoUtente(nuovoUtente);

		if (trovato) {
			throw new BibliotecaException("UTENTE GIA' PRESENTE");
		} else {
			utentiRegistrati.add(nuovoUtente);
			nuovoUtente.setIdUtente(utentiRegistrati.size());
			System.out.println("UTENTE INSERITO");
		}

	}

	/*
	 * @return ritorna un boolean che e' true se e' gia' presente,altrimenti false
	 * 
	 * @input prende in input un utente da controllare valuta se l'utente e' gia'
	 * presente nella lista di utentiRegistrati
	 */

	public boolean controlloInserimentoUtente(Utente utente) {
		boolean trovato = false;
		for (Utente utenteDaControllare : utentiRegistrati) {
			if (utenteDaControllare.equals(utente)) {
				trovato = true;
			}
		}
		return trovato;
	}

	/*
	 * @output stampa gli utenti registrati sfruttando il toString()
	 */

	@Override
	public void visualizzaUtenti() {

		System.out.println(this.utentiRegistrati.toString());

	}

}
