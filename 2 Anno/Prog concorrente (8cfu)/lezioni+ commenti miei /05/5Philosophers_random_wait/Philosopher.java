import java.util.concurrent.ThreadLocalRandom;
public class Philosopher extends Thread {
	private Chopstick right, left;
	private int mealsNumber=0;
	public Philosopher(String n, Chopstick left, Chopstick right) {
		super(n);
		this.left=left;
		this.right=right;
	}
	private void writeState(String action, String stickName) {
		System.out.println("Phil "+this.getName()+action+stickName);
	}
	private void doActivity(String act, long minTime, long maxTime) throws InterruptedException {
		writeState(act, "");
		Thread.sleep(ThreadLocalRandom.current().nextLong(minTime, maxTime));
	}
	public void run() {
		boolean gotSticks;
		while(true) {
			try {
				doActivity(" thinking", 80, 120);
				writeState(": hungry", "");
				gotSticks=false;
				while(!gotSticks){
					left.take();
					gotSticks=right.take(15);
					if(!gotSticks) {
						writeState(": leaving ", left.getName());
						left.leave();
						Thread.sleep(ThreadLocalRandom.current().nextInt(20, 34));
					} 						
				}
				doActivity(": eating ["+(++mealsNumber)+"]", 180, 220);
				left.leave(); right.leave();
			} catch (InterruptedException e) {return ; }
		}
	}
}
