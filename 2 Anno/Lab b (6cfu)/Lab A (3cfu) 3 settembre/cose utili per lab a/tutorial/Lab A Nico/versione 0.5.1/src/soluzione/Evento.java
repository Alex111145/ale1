package soluzione;

/**
 * 
 * @author Someone
 *
 * La classe Evento serve a contenere tutti i dati e le funzioni riguardanti le foto scattate con l'applicazione Facedoor
 */
public class Evento {
	
	private StatoRegistrazione registrazione;
	private StatoUtente accesso;
	private String timestamp;// (ggmmaaaa)
	private String id;
	private double x, y;
	private StatoEmotivo emozione;
	
	/**
	 * Crea un nuovo elemento di tipo Evento inizializzando tutti i campi con i valori dati in input.
	 * 
	 * @param registrazione		Stato di registrazione dell'utente alla generazione dell'evento
	 * @param accesso			Stato di accesso dell'utente alla generazione dell'evento
	 * @param timestamp			Data della generazione dell'evento
	 * @param id				ID dell'utente alla generazione dell'evento
	 * @param x					Valore della coordinata x alla generazione dell'evento
	 * @param y					Valore della coordinata y alla generazione dell'evento
	 * @param emozione			Stato emozionale dell'utente alla generazione dell'evento
	 */
	public Evento( StatoRegistrazione registrazione, StatoUtente accesso, String timestamp, String id, double x, double y, StatoEmotivo emozione ) {
		this.registrazione = registrazione;
		this.accesso = accesso;
		this.timestamp = timestamp;
		this.id = id;
		this.x = x;
		this.y =y;
		this.emozione = emozione;
	}
	
	/**
	 * Restituisce il valore dello stato di accesso registrato all'avvenire dell'evento.
	 * 
	 * @return		Lo stato di accesso registrato.
	 */
	public StatoUtente getAccesso() {
		return accesso;
	}
	
	/**
	 * Restituisce l'ID appartenente all'utente che ha generato l'evento.
	 * 
	 * @return		L'ID dell'utente.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Restituisce la coordinata x della locazione in cui e' stato generato l'evento.
	 * 
	 * @return		La coordinata x dell'evento.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Restituisce la coordinata y della locazione in cui e' stato generato l'evento.
	 * 
	 * @return		La coordinata y dell'evento.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Restituisce la data in cui e' stato generato l'evento.
	 * 
	 * @return		La data in formato String.
	 */
	public String getData() {
		return timestamp;
	}

	/**
	 * Restituisce lo stato emotivo dell'utente registrato all'avvenuta dell'evento.
	 * 
	 * @return		Lo stato emotivo dell'utente.
	 */
	public StatoEmotivo getStatoEmotivo() {
		return emozione;
	}
}
