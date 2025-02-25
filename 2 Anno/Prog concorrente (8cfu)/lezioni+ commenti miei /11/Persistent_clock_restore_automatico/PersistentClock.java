import java.io.*;
import java.util.*;

public class PersistentClock extends Thread implements Serializable{
	private static final long serialVersionUID = 1;
	private long animationSpeed;
	public PersistentClock(int animSpeed) {
		this.animationSpeed = animSpeed;
		start();
	}
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject(); 
	}
	private void readObject(ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		this.start();
	}
	public void run() {
		System.out.println("persistent clock "+ this.getName()+": inizio");
		for(int i=0; i<10; i++) {
			try {
				System.out.print(new Date()+"\r");
				Thread.sleep(animationSpeed);
			} catch (InterruptedException e) { }
		}
		System.out.println("persistent clock"+this.getName()+" terminated");
	}
}

