
package utility;

import java.util.ArrayList;
import java.util.Scanner;

import exception.BibliotecaException;
import flusso.Documentario;
import flusso.Libro;
import flusso.Rivista;

public class UtilityIO {
	static Scanner scanner;

	/*
	 * @exception BibliotecaException viene lanciata se non ci sono riviste/documentari o libri disponibili
	 * @input Arraylist di riviste,documentari e libri presenti nella biblioteca
	 * @output stampa le copie disponibili ordinate per categoria
	 */
	public static void stampaPerCategoria(ArrayList<Rivista> riviste, ArrayList<Documentario> documentari,
			ArrayList<Libro> libri) throws BibliotecaException {

		if (riviste.isEmpty()) {
			throw new BibliotecaException("NESSUNA RIVISTA DISPONIBILE");
		} else {
			System.out.println("RIVISTE DISPONIBILI");
			for (Rivista rivistaDisponibili : riviste) {
				if (rivistaDisponibili.getCopieDisponibili() > 0) {
					System.out.println(rivistaDisponibili.toString());
				}
			}
		}

		if (documentari.isEmpty()) {
			throw new BibliotecaException("NESSUNA DOCUMENTARIO DISPONIBILE");
		} else {
			System.out.println("DOCUMENTARI DISPONIBILI");
			for (Documentario documentariDisponibili : documentari) {
				if (documentariDisponibili.getCopieDisponibili() > 0) {
					System.out.println(documentariDisponibili.toString());
				}
			}
		}

		if (libri.isEmpty()) {
			throw new BibliotecaException("NESSUNA LIBRO DISPONIBILE");
		} else {
			System.out.println("LIBRI DISPONIBILI");
			for (Libro libriDisponibili : libri) {
				if (libriDisponibili.getCopieDisponibili() > 0) {
					System.out.println(libriDisponibili.toString());
				}
			}
		}

	}

	/*
	 * legge un intero, la funzione non termina fino a quando non viene passato un intero
	 */
	public static int leggiIntero() {
		System.out.println("INSERISCI LA SCELTA = ");
		scanner = new Scanner(System.in);
		Integer numero = null;

		do {

			String input = scanner.nextLine();
			if (Character.isDigit(input.charAt(0))) {
				numero = Integer.decode(input);

			} else {
				System.out.println("REINSERISCI IL NUMERO ");
			}

		} while (numero == null);
		return numero;
	}

	/*
	 * legge un intero, la funzione non termina fino a quando non viene passato un intero che rispecchi i numeri dei mesi(1 a 12)
	 */
	public static int leggiMese() {
		System.out.println("INSERISCI LA SCELTA = ");
		scanner = new Scanner(System.in);
		Integer numero = null;

		do {

			String input = scanner.nextLine();
			if (Character.isDigit(input.charAt(0))) {
				numero = Integer.decode(input);

			} else {
				System.out.println("REINSERISCI IL NUMERO ");
			}
			if (numero <= 0 || numero > 12) {
				System.out.println("REINSERISCI IL NUMERO");
			}
		} while (numero == null || numero <= 0 || numero > 12);
		return numero;
	}

	/*
	 * legge un intero, la funzione non termina fino a quando non viene passato un intero che rispecchia i numeri dei giorni(1 a 31)
	 */
	public static int leggiGiorno() {
		System.out.println("INSERISCI LA SCELTA = ");
		scanner = new Scanner(System.in);
		Integer numero = null;

		do {

			String input = scanner.nextLine();
			if (Character.isDigit(input.charAt(0))) {
				numero = Integer.decode(input);

			} else {
				System.out.println("REINSERISCI IL NUMERO ");
			}
			if (numero <= 0 || numero > 31) {
				System.out.println("REINSERISCI IL NUMERO");
			}

		} while (numero == null || numero <= 0 || numero > 31);
		return numero;
	}

	/*
	 * legge un intero, la funzione non termina fino a quando non viene passato una stringa
	 */
	public static String leggiStringa() {
		scanner = new Scanner(System.in);
		String stringa = scanner.nextLine();

		return stringa;
	}

}