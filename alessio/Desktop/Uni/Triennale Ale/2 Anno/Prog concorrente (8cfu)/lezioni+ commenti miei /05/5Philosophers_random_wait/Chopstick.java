public class Chopstick {
	public enum State {AVAILABLE, BUSY}
	private State state;
	private int id;
	public Chopstick(int id) {
		this.id = id;
		this.state=Chopstick.State.AVAILABLE;
	}
	public synchronized void leave() {
		this.state=Chopstick.State.AVAILABLE;
		notify();
	}
	public String getName() {
		return "f"+id;
	}
	public int getId() {
		return this.id;
	}
	public synchronized void take()
			throws InterruptedException {
		while(state==Chopstick.State.BUSY) {
			wait();
		}
		this.state=Chopstick.State.BUSY;
	}
	public synchronized boolean take(long t) throws InterruptedException {
		long startWaitingTime, toWait=t;
		while(state==Chopstick.State.BUSY) {
			System.out.println(Thread.currentThread().getName()+" going to wait for "+toWait);
			startWaitingTime=System.currentTimeMillis();
     		wait(toWait);
			toWait-=System.currentTimeMillis()-startWaitingTime;
			if(state!=Chopstick.State.BUSY){
				break;	
			} else {
				System.out.println(Thread.currentThread().getName()+
						" waked up with chopstick busy and "+toWait+" to wait");
				if(toWait<=0) {
					return (false);
				}
			}
		}
		this.state=Chopstick.State.BUSY;
		return (true);
	}
}



