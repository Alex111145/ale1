public class Waiter {
	public synchronized void takeTwo(Chopstick i, Chopstick j) {
		System.out.println("Waiter: richiesta da "+Thread.currentThread().getName());
		while(!(i.isAvailable() && j.isAvailable())) {
			try {
				System.out.println("Waiter: "+Thread.currentThread().getName()+" aspetta");
				wait() ;
			} catch (InterruptedException e) { }
		}
		System.out.println("Waiter: "+Thread.currentThread().getName()+" servito");
		i.take();
		j.take();
	}
	public synchronized void leaveTwo(Chopstick i, Chopstick j) {
		System.out.println("Waiter: "+Thread.currentThread().getName()+" ha finito");
		i.leave();
		j.leave();
		notifyAll();
	}
}

