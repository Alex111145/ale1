
public class Cavallo extends Thread {
	private int numGiri;
	Barrier barriera;
	String name;
	public Cavallo(int id, Barrier b, int n) {
		barriera=b;
		name="Cavallo " + id;
		numGiri=n;
	}
	public void run() {
		System.out.println(name+ " partito!");
		for(int i=0; i<numGiri; i++) {
			try {
				Thread.sleep((long)(Math.random()*3000));
			} catch (InterruptedException ex ) {}
			System.out.println(name+" alla barriera ");
			barriera.waitB();
			System.out.println(name+ " ripartito!");

		}
		System.out.println(name+ " termina la corsa!");
	}
}

