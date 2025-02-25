import java.util.concurrent.*;
public class Giocatore extends Thread{
	Gioco ilGioco;
	int mioId;
	String mioNome;
	public Giocatore (int id, Gioco g) {
		ilGioco=g;
		mioId=id;
		mioNome="giocatore_"+id;
	}

	private void dormitina() {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
		} catch (InterruptedException e) {	}
	}

	public void run() {
		for(int it=0;it<10;it++){
			while(!Gioco.possoGiocare(mioId)) {
				dormitina();
			}
			System.out.println(mioNome+": "+"gioco");
			ilGioco.giocata(mioId);
			while(!ilGioco.esitoDisponibile()) {
				dormitina();
			}
			System.out.println(mioNome+"["+it+"]: "+"leggo");
			ilGioco.letturaEsito(mioId);
		}
	}
}
