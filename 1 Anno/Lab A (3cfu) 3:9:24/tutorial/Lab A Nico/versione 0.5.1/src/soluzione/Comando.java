package soluzione;

//TODO Javadoc
public class Comando {
	private TipoComando tipo;
	private String argomento;
	
	/**
	 * Crea un nuovo elemento Comando inizializzando i campi di tipo e argomento 
	 * utilizzando <code>tipo</code> e <code>argomento</code>.
	 * 
	 * @param tipo 			Tipo del comando
	 * @param argomento		Argomento del comando
	 */
	public Comando( TipoComando tipo, String argomento ) {
		this.tipo = tipo;
		this.argomento = argomento;
	}
	
	/**
	 * Restituisce il tipo del comando.
	 * 
	 * @return		Il tipo del comando
	 */
	public TipoComando getTipo() {
		return tipo;
	}
	
	/**
	 * Restituisce l'argomento del comando nel formato String.
	 * 
	 * @return		L'argomento del comando.
	 */
	public String getArgomento() {
		return argomento;
	}
}
