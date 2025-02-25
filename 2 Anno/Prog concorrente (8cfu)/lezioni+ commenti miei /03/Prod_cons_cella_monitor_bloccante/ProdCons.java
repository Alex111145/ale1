
public class ProdCons {
	public static void main(String[] args){
		Cella cella=new Cella();
		Thread tp=new Produttore(cella);
		Thread tc=new Consumatore(cella);
		tp.start();
		tc.start();
	}
}
