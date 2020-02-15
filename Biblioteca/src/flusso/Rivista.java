package flusso;

import java.time.LocalDate;

public class Rivista extends OggettoBibliotecario {

	private int numeroRivista;
	private String tipoRivista ;

	public static enum Periodo {
		SETTIMANALE, ANNUALE, MENSILE
	}

	private Periodo periodoRivista;

	public Rivista(String nome, LocalDate annoDiPubblicazione, int numeroRivista, Periodo periodoRivista,
			int copieDisponibili) {
		super(nome, annoDiPubblicazione, copieDisponibili);
		this.numeroRivista = numeroRivista;
		this.periodoRivista = periodoRivista;
		this.tipoRivista = "periodica d'informazione";// la biblioteca tratta riviste solo di tipo periodiche di informazione,quindi si da per scontato che la rivista inserita sia di quel tipo
	}

	@Override
	public String toString() {
		return "ID = " + getId() + ", Nome=" + getNome() + ", Numero della rivista=" + numeroRivista + ", Tipo della rivista=" + tipoRivista
				+ ", Periodo della rivista=" + periodoRivista + ", Copie Disponibili=" + getCopieDisponibili()
				+ ", Copie In Prestito=" + getCopieInPrestito() + ", Anno Di Pubblicazione=" + getAnnoDiPubblicazione();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rivista other = (Rivista) obj;
		return numeroRivista == other.numeroRivista && periodoRivista == other.periodoRivista;
	}

}
