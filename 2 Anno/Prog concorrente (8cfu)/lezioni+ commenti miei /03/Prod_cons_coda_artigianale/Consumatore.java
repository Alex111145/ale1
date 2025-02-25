import java.util.concurrent.*;

public class Consumatore extends Thread {
	Coda buffer;
	int v;
	public Consumatore(String s, Coda c){
		super(s);
		this.buffer=c;
	}
	public void run(){
		for(;;){
			v=buffer.getItem();
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			} catch (InterruptedException e) {  }
		}
	}
}

