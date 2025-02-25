package soluzione;

public class Comando {
	private TipoComando tipo;
	private Object argomento;
	
	public Comando( TipoComando tipo, Object argomento ) {
		this.tipo = tipo;
		this.argomento = argomento;
	}
	
	public TipoComando getTipo() {
		return tipo;
	}
	
	public Object getArgomento() {
		return argomento;
	}
}
