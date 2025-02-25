import java.io.*;
import java.util.*;

public class PersistentClock extends Thread {
	private long animationSpeed;
	public PersistentClock(int animSpeed) {
		this.animationSpeed = animSpeed;
		start();
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

