package flusso;

import java.time.LocalDate;

public abstract class OggettoBibliotecario {

	private String nome;
	private LocalDate annoDiPubblicazione;
	private int copieDisponibili;
	private int copieInPrestito;
	private int id;




	public OggettoBibliotecario(String nome, LocalDate annoDiPubblicazione, int copieDisponibili) {
		this.nome = nome;
		this.annoDiPubblicazione = annoDiPubblicazione;
		this.copieDisponibili = copieDisponibili;
		this.copieInPrestito = 0;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCopieDisponibili() {
		return copieDisponibili;
	}

	/*
	 * aumenta il contatore di copie disponibili
	 */
	public void incrementaCopieDisponibili() {
		this.copieDisponibili += 1;
	}

	/*
	 * decrementa il contatore di copie disponibili
	 */
	public void decrementaCopieDisponibili() {
		this.copieDisponibili -= 1;
	}

	public int getCopieInPrestito() {
		return copieInPrestito;
	}
	/*
	 * aumenta il contatore di copie in prestito
	 */
	public void incrementaCopieInPrestito() {
		this.copieInPrestito += 1;
	}
	/*
	 * decrementa il contatore di copie in prestito
	 */
	public void decrementaCopieInPrestito() {
		this.copieInPrestito -= 1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getAnnoDiPubblicazione() {
		return annoDiPubblicazione;
	}

	public void setAnnoDiPubblicazione(LocalDate annoDiPubblicazione) {
		this.annoDiPubblicazione = annoDiPubblicazione;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OggettoBibliotecario other = (OggettoBibliotecario) obj;
		if (annoDiPubblicazione == null) {
			if (other.annoDiPubblicazione != null)
				return false;
		} else if (!annoDiPubblicazione.equals(other.annoDiPubblicazione))
			return false;
		if (copieDisponibili != other.copieDisponibili)
			return false;
		if (copieInPrestito != other.copieInPrestito)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
