package cicli;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import exception.BibliotecaException;
import flusso.Biblioteca;
import flusso.Documentario;
import flusso.Libro;
import flusso.Prestito;
import flusso.Rivista;
import flusso.Utente;
import interfacce.IPrestito;
import utility.UtilityIO;

public class Test {

	public static void main(String[] args) throws Exception {

		LocalTime durata = LocalTime.of(2, 33);
		LocalDate data = LocalDate.of(2010, Month.APRIL, 1);
		LocalDate data1 = LocalDate.of(2010, Month.APRIL, 2);
		LocalDate data2 = LocalDate.of(2010, Month.APRIL, 3);

		Libro libro = new Libro("123456", "Libro", "Marco Zampa", "Mondadori", data2, 20);
		Documentario documentario = new Documentario("Documentario", Documentario.Tematica.STORIA, durata, data1, 1);
		Documentario documentario2 = new Documentario("Documentario2", Documentario.Tematica.STORIA, durata, data, 1);
		Rivista rivista = new Rivista("Rivista", data, 1, Rivista.Periodo.SETTIMANALE, 5);
		Rivista rivista2 = new Rivista("A", data2, 1, Rivista.Periodo.SETTIMANALE, 5);
		Rivista rivista3 = new Rivista("A", data2, 1, Rivista.Periodo.SETTIMANALE, 5);
		Biblioteca biblioteca = new Biblioteca();

		biblioteca.inserisciInBiblioteca(libro, rivista2, rivista3, documentario, documentario2, rivista);
		try {
			int scelta;
			do {
				
				menu();
				scelta = UtilityIO.leggiIntero();

				switch (scelta) {
				case 1:
					try {
						biblioteca.stampaCopiePerNome();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						biblioteca.stampaCopiePerData();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println("INSERISCI IL NOME");
						String nome = UtilityIO.leggiStringa();
						System.out.println("INSERISCI IL COGNOME");
						String cognome = UtilityIO.leggiStringa();
						System.out.println("INSERISCI L'ANNO DI NASCITA");
						int anno = UtilityIO.leggiIntero();
						System.out.println("INSERISCI IL MESE DI NASCITA(IN NUMERI)");
						int mese = UtilityIO.leggiMese();
						System.out.println("INSERISCI IL GIORNO DI NASCITA");
						int giorno = UtilityIO.leggiGiorno();
						System.out.println("INSERISCI IL CODICE FISCALE");
						String codiceFiscale = UtilityIO.leggiStringa();
						LocalDate dataDiNascita = LocalDate.of(anno, mese, giorno);
						Utente utente = new Utente(nome, cognome, dataDiNascita, codiceFiscale);
						biblioteca.registraNuovoUtente(utente);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					try {
						biblioteca.stampaUtentiPerCognome();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5:
					try {
						biblioteca.stampaUtentiConPrestitiScaduti();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 6:
					try {
						if (biblioteca.getUtentiRegistrati().isEmpty()) {
							throw new BibliotecaException("NESSUN UTENTE REGISTRATO");
						} else {

							System.out.println(biblioteca.getUtentiRegistrati().toString());
							System.out.println("INSERISCI L'UTENTE CHE DEVE EFFETTURE IL PRESTITO");
							int idUtente = UtilityIO.leggiIntero() - 1;
							if (idUtente >= biblioteca.getUtentiRegistrati().size() || idUtente == -1) {
								throw new BibliotecaException("UTENTE NON TROVATO IN BIBLIOTECA");
							} else {
								IPrestito.controlloPrestitoUtente(biblioteca.getUtentiRegistrati().get(idUtente));
								System.out.println("SCEGLI LA CATEGORIA(LIBRO,DOCUMENTARIO,RIVISTA)");
								String categoria = UtilityIO.leggiStringa();
								categoria = categoria.toLowerCase().trim();
								biblioteca.stampaCopiePerCategoria(categoria);
								System.out.println("INSERISCI L'ID DELL'OGGETTO CHE VUOI IN PRESTITO");
								int copia = UtilityIO.leggiIntero() - 1;
								Prestito prestito = null;
								switch (categoria) {
								case "documentario":
									if (copia < biblioteca.getDocumentari().size() && copia != -1) {
										prestito = new Prestito(biblioteca.getDocumentari().get(copia),
												biblioteca.getUtentiRegistrati().get(idUtente));

									} else {
										throw new BibliotecaException("DOCUMENTARIO NON TROVATO");
									}

									break;
								case "libro":
									if (copia < biblioteca.getLibri().size() && copia != -1) {
										prestito = new Prestito(biblioteca.getLibri().get(copia),
												biblioteca.getUtentiRegistrati().get(idUtente));

									} else {
										throw new BibliotecaException("LIBRO NON TROVATO");
									}
									break;
								case "rivista":
									if (copia < biblioteca.getRiviste().size() && copia != -1) {
										prestito = new Prestito(biblioteca.getRiviste().get(copia),
												biblioteca.getUtentiRegistrati().get(idUtente));

									} else {
										throw new BibliotecaException("RIVISTA NON TROVATA");
									}
									break;
								}
								prestito.presta();
								break;

							}

						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 0:
					System.out.println("FINE");
					break;
				default:
					System.out.println("SCELTA NON VALIDA");
				}
			} while (scelta != 0);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("NON PUOI NON INSERIRE NIENTE,ESECUZIONE TERMINATA");
		}
	}
	
	/*
	 * stampa il menu
	 */
	public static void menu() {
		System.out.println("\nSCEGLI\n" + "1)VISUALIZZA COPIE DISPONIBILI PER NOME\n"
				+ "2) VISUALIZZA COPIE DISPONIBILI PER DATA\n" + "3)REGISTRA NUOVO UTENTE \n"
				+ "4)VISUALIZZA LISTA UTENTI(PER COGNOME)\n"
				+ "5)VISUALIZZA LISTA DEGLI UTENTI CON UN PRESTITO SCADUTO\n"
				+ "6) CHIEDERE UN NUOVO PRESTITO\n");
	}
	
}
