import java.util.concurrent.*;
public class Producer implements Runnable {
	private BlockingQueue<Message> queue;
	public Producer(BlockingQueue<Message> q) {
		this.queue=q; 
	}
	public void run(){
		String mioNome=Thread.currentThread().getName();
		int i=0;
		while(true){
			Message msg = new Message (mioNome+"_dato_" + i);
			try {
				Thread.sleep(10);
				queue.put(msg);   // produce messages
				System.out.println(mioNome+" produced " + msg.getMsg()+"["+queue.size()+"]");
			} catch (InterruptedException e) { }
		}
	}
}
