package soluzione;

/**
 * Classe per la rappresentazione dei comandi di EmotionalMaps.
 * 
 * @author Simone 	Brenna 		Mat. 732520
 * @author Nicolo 	Ferrari		Mat. 732707
 * @author Luigi 	Mucciarone	Mat. 732714
 * @author Luca 	Alberti		Mat. 733096
 * 
 */
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
