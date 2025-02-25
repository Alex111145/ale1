package soluzione;

public class Comando {
	private TipoComando tipo;
	private String argomento;
	
	public Comando( TipoComando tipo, String argomento ) {
		this.tipo = tipo;
		this.argomento = argomento;
	}
	
	public TipoComando getTipo() {
		return tipo;
	}
	
	public String getArgomento() {
		return argomento;
	}
}
