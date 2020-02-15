package interfacce;

import exception.BibliotecaException;
import flusso.Utente;

public interface IGestioneUtenti {

	public void registraNuovoUtente(Utente utente) throws Exception;

	public void visualizzaUtenti();

	public void ordinaUtentiPerCognome() ;

	public void stampaUtentiConPrestitiScaduti() throws BibliotecaException;

	public void stampaUtentiPerCognome() throws BibliotecaException;

	public void verificaUtenteConPrestitoScaduto() throws BibliotecaException ;
}
