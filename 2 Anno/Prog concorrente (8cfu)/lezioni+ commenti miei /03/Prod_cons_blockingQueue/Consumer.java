import java.util.concurrent.*;
public class Consumer implements Runnable {
	private BlockingQueue<Message> queue;
	public Consumer (BlockingQueue<Message> q) {
		this.queue=q;
	}
	public void run() {
		Message msg ;
		try {
			while(true) {
				msg = queue.take();
				System.out.println(Thread.currentThread().getName()+" consumed "+
				msg.getMsg()+"["+queue.size()+"]");
				Thread.sleep(10);
			}
		} catch (InterruptedException e) { }
	}
}
