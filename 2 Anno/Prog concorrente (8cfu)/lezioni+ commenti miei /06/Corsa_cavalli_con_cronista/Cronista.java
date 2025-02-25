public class Cronista extends Thread {
	BarrierCorsa barriera ;
	private int numGiri ;
	public Cronista(BarrierCorsa b, int n) {
		barriera=b;
		numGiri=n;
	}
	public void run() {
		int giriFatti=0;
		for(int i=0; i<numGiri; i++) {
			// System.out.println("Cronista: aspetto completamento del giro num."+(i+1));
			giriFatti=barriera.waitCycle(i+1);
			System.out.println("Cronista: completato il giro num."+giriFatti);
		}
		System.out.println("Cronista: ultimo giro!");
	}
}
