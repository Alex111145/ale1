import java.util.concurrent.*;

public class Cavallo extends Thread {
	private int numGiri;
	CyclicBarrier barriera;
	String name;
	public Cavallo(int id, CyclicBarrier b, int n) {
		barriera=b;
		name="Cavallo_" + id;
		numGiri=n;
	}
	public void run() {
		for(int i=0; i<numGiri; i++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
				System.out.println(name+" alla barriera ");
				barriera.await();
				System.out.println(name+ " ripartito!");
			} catch (InterruptedException | BrokenBarrierException e) {} 
		}
		System.out.println(name+" termina");
	}
}

