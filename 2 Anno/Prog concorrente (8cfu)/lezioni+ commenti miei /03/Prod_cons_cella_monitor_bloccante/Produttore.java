import java.util.concurrent.ThreadLocalRandom;

class Produttore extends Thread{
	Cella cellaCondivisa;
	public Produttore(Cella c){
		this.cellaCondivisa=c;
	}
	public void run(){
		for(int i=1; i<=10; ++i){
//			try {
//				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
//			} catch (InterruptedException e) {	}
			int v=(int)(100*Math.random());
			cellaCondivisa.setValore(v);
		}
	}
}
