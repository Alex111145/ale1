public class BarrierCorsa extends Barrier {
	private int numCycles; // how many times the barrier was closed
	public BarrierCorsa(int number) {
		super(number);
		numCycles=0;
	}
	public synchronized boolean waitB() {
		if(super.waitB()) {
			numCycles++;
		}
		return true;
	}
	public synchronized int waitCycle(int cycleNum) {
		if(cycleNum!=numCycles)
			try {
				wait();
			} catch (InterruptedException e) { }
		return numCycles;
	}
}