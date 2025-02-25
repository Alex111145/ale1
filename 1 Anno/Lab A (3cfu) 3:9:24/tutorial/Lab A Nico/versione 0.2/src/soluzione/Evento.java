package soluzione;

public class Evento {
	
	private StatoRegistrazione registrazione;
	private StatoUtente accesso;
	private String timestamp;// (ggmmaaaa)
	private String id;
	private double x, y;
	private StatoEmotivo emozione;
	
	public Evento( StatoRegistrazione registrazione, StatoUtente accesso, String timestamp, String id, double x, double y, StatoEmotivo emozione ) {
		this.registrazione = registrazione;
		this.accesso = accesso;
		this.timestamp = timestamp;
		this.id = id;
		this.x = x;
		this.y =y;
		this.emozione = emozione;
	}
	
	public StatoUtente getAccesso() {
		return accesso;
	}
	
	public String getId() {
		return id;
	}
	
	public String getData() {
		return timestamp;
	}
	
	public StatoEmotivo getStatoEmotivo() {
		return emozione;
	}
}
