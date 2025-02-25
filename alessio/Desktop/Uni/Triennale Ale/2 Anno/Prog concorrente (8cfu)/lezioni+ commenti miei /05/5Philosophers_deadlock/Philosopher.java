import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
	private Chopstick primo, secondo;
	private String name ;
	public Philosopher(String id, Chopstick left, Chopstick right) {
		this.name=id;
		this.primo=left;
		this.secondo=right;
	}
	private void writeState(String action, String stickName) {
		System.out.println("Phil "+name+action+stickName);
	}
	private void doActivity(String act, long minTime, long maxTime) throws InterruptedException {
		writeState(act, "");
		Thread.sleep(ThreadLocalRandom.current().nextLong(minTime, maxTime));
	}
	public void run() {
		while(true) {
			try {
				doActivity(" thinking", 5, 50);
				writeState(": hungry", "");
				primo.take();
				writeState(" picked up ", primo.getName());
				Thread.sleep(30);  // per facilitare deadlock!
				secondo.take();
				writeState(" picked up ", secondo.getName());
				doActivity(": eating ", 5, 50);
				primo.leave();
				writeState(" dropped ", primo.getName());
				secondo.leave();
				writeState(" dropped ", secondo.getName());
			} catch (InterruptedException e) {return ; }
		}
	}
}


