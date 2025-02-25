import java.util.concurrent.Semaphore;
public class ProdCons {
	final int BUFFSIZE=4;
	final Semaphore full = new Semaphore(0);
	final Semaphore empty = new Semaphore(BUFFSIZE);
	void exec(){
		Coda cella=new Coda(4);
		new Produttore("Prod1", cella, full, empty).start();
		new Consumatore("Cons1", cella, full, empty).start();
		new Produttore("Prod2", cella, full, empty).start();
		new Consumatore("Cons2", cella, full, empty).start();
	}
	public static void main(String[] args) {
		new ProdCons().exec();
	}
}
