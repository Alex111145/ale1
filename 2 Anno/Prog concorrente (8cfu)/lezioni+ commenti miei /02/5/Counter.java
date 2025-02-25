public class Counter {
	long count = 0;
	MySemaphore semaphore;
	Counter(){
		semaphore=new MySemaphore(1);
	}

	public void add (long value) {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) { }
		this.count += value ;
		semaphore.release();
	}
}
