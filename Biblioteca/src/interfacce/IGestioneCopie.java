package interfacce;

import flusso.OggettoBibliotecario;

public interface IGestioneCopie {

	public void ordinaCopiePerNome() throws Exception;

	public void ordinaCopiePerData() throws Exception;

	public void stampaCopiePerCategoria(String categoria) throws Exception;

	public void stampaCopiePerNome() throws Exception;

	public void stampaCopiePerData() throws Exception;

	public void inserisciInBiblioteca(OggettoBibliotecario... oggettoBibliotecario) throws Exception;

}
