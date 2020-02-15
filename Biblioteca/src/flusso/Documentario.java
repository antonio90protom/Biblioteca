package flusso;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Documentario extends OggettoBibliotecario {

	private LocalTime durata;
	private String formato;
	

	public static enum Tematica {
		STORIA, MEDICINA, INFORMATICA, SPORT, ALTRO
	}

	private Tematica tematica;

	public Documentario(String nome, Tematica tematica, LocalTime durata, LocalDate annoDiPubblicazione,
			int copieDisponibili) {
		super(nome, annoDiPubblicazione, copieDisponibili);
		this.durata = durata;
		this.tematica = tematica;
		this.formato = "dvd"; // la biblioteca tratta documentari solo in formato dvd,quindi si da per scontato che il documentario inserito sia in formato dvd
	}

	@Override
	public String toString() {
		return "ID = " + getId() + ", Nome= " + getNome() + ", durata=" + durata + ", formato=" + formato + ", tematica=" + tematica
				+ ", Copie Disponibili=" + getCopieDisponibili() + ", Copie In Prestito=" + getCopieInPrestito()
				+ ", Anno Di Pubblicazione=" + getAnnoDiPubblicazione();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documentario other = (Documentario) obj;
		return Objects.equals(durata, other.durata)
				&& tematica == other.tematica;
	}
	
	

}
