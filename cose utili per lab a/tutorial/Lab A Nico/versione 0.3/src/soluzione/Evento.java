package soluzione;

//TODO Javadoc
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
	
	//TODO Javadoc
	public StatoUtente getAccesso() {
		return accesso;
	}
	
	//TODO Javadoc
	public String getId() {
		return id;
	}
	
	//TODO Javadoc
	public double getX() {
		return x;
	}
	
	//TODO Javadoc
	public double getY() {
		return y;
	}
	
	//TODO Javadoc
	public String getData() {
		return timestamp;
	}
	
	//TODO Javadoc
	public StatoEmotivo getStatoEmotivo() {
		return emozione;
	}
}
