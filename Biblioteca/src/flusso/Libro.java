package flusso;

import java.time.LocalDate;
import java.util.Objects;

public class Libro extends OggettoBibliotecario {

	private String codiceISBN;
	private String autori;
	private String casaEditrice;

	public Libro(String codiceISBN, String nome, String autori, String casaEditrice, LocalDate annoDiPubblicazione,
			int copieDisponibili) {
		super(nome, annoDiPubblicazione, copieDisponibili);
		this.codiceISBN = codiceISBN;
		this.autori = autori;
		this.casaEditrice = casaEditrice;
	}

	@Override
	public String toString() {
		return "ID = " + getId() + ", Nome=" + getNome() + ", Autori=" + autori + ", Casa Editrice="
				+ casaEditrice + ", Anno Di Pubblicazione=" + getAnnoDiPubblicazione() + ", Copie Disponibili="
				+ getCopieDisponibili() + ", Copie In Prestito=" + getCopieInPrestito()  + "CodiceISBN=" + codiceISBN;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(autori, other.autori) && Objects.equals(casaEditrice, other.casaEditrice)
				&& Objects.equals(codiceISBN, other.codiceISBN);
	}

}
