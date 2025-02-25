package soluzione;

//TODO Check javadoc
/**
 * 
 * @author 
 *
 * La classe Evento serve a contenere tutti i dati e le funzioni riguardanti le foto scattate con l'applicazione facedoor attiva(?)
 */
public class Evento {
	
	private StatoRegistrazione registrazione;
	private StatoUtente accesso;
	private String timestamp;// (ggmmaaaa)
	private String id;
	private double x, y;
	private StatoEmotivo emozione;
	
	//TODO Javadoc
	public Evento( StatoRegistrazione registrazione, StatoUtente accesso, String timestamp, String id, double x, double y, StatoEmotivo emozione ) {
		this.registrazione = registrazione;
		this.accesso = accesso;
		this.timestamp = timestamp;
		this.id = id;
		this.x = x;
		this.y =y;
		this.emozione = emozione;
	}
	
	//TODO Check javadoc
	/**
	 * restituisce il valore dello stato di accesso registrato all'avvenire dell'evento.
	 * 
	 * @return lo stato di accesso registrato.
	 */
	public StatoUtente getAccesso() {
		return accesso;
	}
	
	//TODO Check javadoc
	/**
	 * Restituisce l'id appartenente all'utente che ha generato l'evento.
	 * 
	 * @return l'id dell'utente.
	 */
	public String getId() {
		return id;
	}
	
	//TODO Check javadoc
	/**
	 * restituisce la coordinata X della locazione in cui e' stato generato l'evento.
	 * 
	 * @return la coordinata X dell'evento.
	 */
	public double getX() {
		return x;
	}
	
	//TODO Check javadoc
	/**
	 * restituisce la coordinata Y della locazione in cui e' stato generato l'evento.
	 * 
	 * @return la coordinata Y dell'evento.
	 */
	public double getY() {
		return y;
	}
	
	//TODO Check javadoc
	/**
	 * Restituisce la data in cui e' stato generato l'evento.
	 * 
	 * @return la data in formato string.
	 */
	public String getData() {
		return timestamp;
	}
	
	//TODO Check javadoc
	/**
	 * Restituisce lo stato emotivo dell'utente registrato all'avvenuta dell'evento.
	 * 
	 * @return lo stato emotivo dell'utente.
	 */
	public StatoEmotivo getStatoEmotivo() {
		return emozione;
	}
}
