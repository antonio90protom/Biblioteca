package utility;

import java.time.LocalDate;
import java.util.Comparator;

import flusso.OggettoBibliotecario;
import flusso.Utente;

public class UtilityComparatore {

	/*
	 * @implements Comparator<OggettoBibliotecario>
	 * classe utilizzata per comparare per data gli oggetti
	 */
	public static class ComparaCopiePerData implements Comparator<OggettoBibliotecario> {
		/*
		 * verifica tra i due oggetti qual e' stato pubblicato prima
		 * @input OggettoBibliotecario a, OggettoBibliotecario b(gli oggetti da comparare)
		 */
		@Override
		public int compare(OggettoBibliotecario a, OggettoBibliotecario b) {
			LocalDate nome_1 = a.getAnnoDiPubblicazione();
			LocalDate nome_2 = b.getAnnoDiPubblicazione();
			return (nome_1.compareTo(nome_2));
		}

	}
	/*
	 * @implements Comparator<OggettoBibliotecario>
	 * classe utilizzata per comparare per nome gli oggetti
	 */
	public static class ComparaCopiePerNome implements Comparator<OggettoBibliotecario> {
		/*
		 * verifica tra i due oggetti quale dei due ha il nome che alfabeticamente viene prima dell'altro
		 * @input OggettoBibliotecario a, OggettoBibliotecario b(gli oggetti da comparare)
		 */
		@Override
		public int compare(OggettoBibliotecario a, OggettoBibliotecario b) {
			String nome_1 = a.getNome();
			String nome_2 = b.getNome();
			return (nome_1.compareTo(nome_2));
		}

	}
	/*
	 * @implements Comparator<Utente>
	 * classe utilizzata per comparare per cognome gli utenti
	 */
	public static class ComparaPerCognome implements Comparator<Utente> {
		/*
		 * verifica tra i due utenti quale dei due ha il cognome che alfabeticamente viene prima dell'altro
		 * @input Utente a, Utente b(gli utenti da comparare)
		 */
		@Override
		public int compare(Utente a, Utente b) {
			String nome_1 = a.getCognome();
			String nome_2 = b.getCognome();
			return (nome_1.compareTo(nome_2));
		}

	}

}
