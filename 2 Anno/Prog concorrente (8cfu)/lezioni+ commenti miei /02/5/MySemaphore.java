
public class MySemaphore {
	private int value;
	MySemaphore (int init) {
		value = init;
	}
	synchronized public void release() {
		value++;
		notify();
	}
	synchronized public void acquire() throws InterruptedException {
		if (value == 0)  // NB: while, non if!
			wait();
		value--;
	}
}
