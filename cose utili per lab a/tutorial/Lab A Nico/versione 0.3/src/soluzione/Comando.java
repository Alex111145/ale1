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
	
	//TODO Javadoc
	public TipoComando getTipo() {
		return tipo;
	}
	
	//TODO Javadoc
	public String getArgomento() {
		return argomento;
	}
}
