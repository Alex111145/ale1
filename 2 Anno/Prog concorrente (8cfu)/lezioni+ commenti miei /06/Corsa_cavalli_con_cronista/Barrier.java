public class Barrier {
	private int needed;    // number of threads to block
	private int arrived;   // number of threads that arrived at the barrier
	private boolean releasing;  // state of the barrier: when releasing,
	                            // the previously stopped threads proceed
	public Barrier(int number) {
		needed=number;
		arrived=0;
		releasing=false;
	}
	public synchronized boolean waitB() {
		boolean opens=false;
		while(releasing)
			try {
				wait(); // Wait for previous batch to depart
			} catch (InterruptedException e) { } 
		try {
			arrived++;
			while(arrived!=needed && !releasing)
				wait();  // Wait for the barrier to open
			if(arrived==needed) { // When all present
				releasing=true;
				System.out.println("Barrier: all "+needed+" parties arrived at barrier");
				notifyAll();
				opens=true;
			}
		} catch (InterruptedException e) {
			opens=false;
		} finally {
			arrived--;
			if(arrived==0) { // Last thread to leave
				releasing=false;
				notifyAll(); // Allow new batch to arrive 
			}
		}
		return (opens);
	}
	public synchronized int value () {
		return arrived ;
	}
	public int capacity() {
		return needed;
	}
}