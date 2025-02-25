package soluzione;

//TODO Javadoc
public class Comando {
	private TipoComando tipo;
	private String argomento;
	
	//TODO Javadoc
	public Comando( TipoComando tipo, String argomento ) {
		this.tipo = tipo;
		this.argomento = argomento;
	}
	
	//TODO Check javadoc
	/**
	 * Restituisce il tipo del comando.
	 * 
	 * @return il tipo del comando.
	 */
	public TipoComando getTipo() {
		return tipo;
	}
	
	//TODO Check javadoc
	/**
	 * Restituisce l'argomento del comando nel formato string.
	 * 
	 * @return l'argomento del comando.
	 */
	public String getArgomento() {
		return argomento;
	}
}
