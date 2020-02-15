package flusso;

import java.time.LocalDate;

public class Utente {

	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;
	private String codiceFiscale;
	private int libriInPrestito;
	private int rivisteInPrestito;
	private int DVDInPrestito;
	private boolean prestitiScaduti; // un booleano che viene utilizzato per capire se l'utente ha un prestito scaduto
	private int idUtente;
	

	public Utente(String nome, String cognome, LocalDate dataDiNascita, String codiceFiscale) {
		this.nome = nome.toLowerCase().trim();
		this.cognome = cognome.toLowerCase().trim();
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale.toUpperCase();
		this.libriInPrestito = 0;
		this.rivisteInPrestito = 0;
		this.DVDInPrestito = 0;
		this.prestitiScaduti = false;
	}

	@Override
	public String toString() {
		return "ID = " + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", data di nascita=" + dataDiNascita
				+ ", codice fiscale=" + codiceFiscale + ", libri in prestito=" + libriInPrestito
				+ ", riviste in prestito=" + rivisteInPrestito + ", documentari in prestito=" + DVDInPrestito;
	}
	
	
	

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public boolean isPrestitiScaduti() {
		return prestitiScaduti;
	}

	public void setPrestitiScaduti(boolean prestitiScaduti) {
		this.prestitiScaduti = prestitiScaduti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public int getLibriInPrestito() {
		return libriInPrestito;
	}

	
	/*
	 * incrementa il contatore di libri presi in prestito
	 */
	public void incrementaLibriInPrestito() {
		libriInPrestito += 1;
	}
	/*
	 * decrementa il contatore di libri presi in prestito
	 */
	public void decrementaLibriInPrestito() {
		libriInPrestito -= 1;
	}

	public int getRivisteInPrestito() {
		return rivisteInPrestito;
	}

/*
 *  incrementa il contatore di riviste prese in prestito
 */
	public void incrementaRivisteInPrestito() {
		rivisteInPrestito += 1;
	}
	/*
	 *  decrementa il contatore di riviste presi in prestito
	 */
	public void decrementaRivisteInPrestito() {
		rivisteInPrestito -= 1;
	}

	public int getDVDInPrestito() {
		return DVDInPrestito;
	}

	/*
	 *  incrementa il contatore di documentari presi in prestito
	 */
	public void incrementaDVDInPrestito() {
		DVDInPrestito += 1;
	}

	/*
	 *  decrementa il contatore di documentari presi in prestito
	 */
	public void decrementaDVDInPrestito() {
		DVDInPrestito -= 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataDiNascita == null) {
			if (other.dataDiNascita != null)
				return false;
		} else if (!dataDiNascita.equals(other.dataDiNascita))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}


