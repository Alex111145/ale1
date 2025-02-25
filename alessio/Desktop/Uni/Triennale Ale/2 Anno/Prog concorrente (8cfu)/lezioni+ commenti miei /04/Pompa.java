import java.util.concurrent.ThreadLocalRandom;

class Pompa{
	private static final int LIBERA = 0 ;
	private static final int OCCUPATA = 1 ;
	private int state = LIBERA;
	public Pompa(){
		state=LIBERA;
	}
	synchronized public void occupa() {
		try {
			while(state != LIBERA)
				wait();
			state = OCCUPATA;
//			notifyAll();
		} catch(InterruptedException e) {}
	}
	synchronized public void eroga() {
		try {
			while(state != OCCUPATA)
				wait();
			Thread.sleep(ThreadLocalRandom.current().nextInt(300, 500));
		} catch(InterruptedException e) { }
	}
	synchronized public void lascia() {
		try {
			while(state != OCCUPATA)
				wait();
			state = LIBERA;
			notifyAll();
		} catch(InterruptedException e) {}
	}
}


