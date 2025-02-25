
public class ProdCons {
	final int BUFFSIZE=4;
	Coda coda=new Coda(BUFFSIZE);
	void exec(){
		Coda cella=new Coda(4);
		new Produttore("Prod1", coda).start();
		new Consumatore("Cons1", coda).start();
//		new Produttore("Prod2", coda).start();
//		new Consumatore("Cons2", coda).start();
	}
	public static void main(String[] args) {
		new ProdCons().exec();
	}
}
