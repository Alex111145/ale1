
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Auto implements Runnable {
	private static int tempoTotale = 0,	numAuto = 0;
	private int numCliente;
	private Pompa pompaUsata;
	public Auto(int n, Pompa p) {
		this. pompaUsata = p;
		this. numCliente = n;
	}
	public static float calcMedia () {
		return tempoTotale/numAuto;
	}
	private void avvicinamento() {
		final int MAXdurataAvvicinamento= 3000;
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1, MAXdurataAvvicinamento));
		} catch (InterruptedException e) { }
	}
	private void preparazione() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) { }
	}
	private void allontanamento() {
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) { }
	}
	public void run () {
		long tempoInizio, tempoFine;
		avvicinamento();
		System.out.println("Auto " + numCliente+ " arriva a pompa");
		tempoInizio = (new Date()).getTime();
		pompaUsata.occupa();
		System.out.println("Auto "+ numCliente+ " in rifornimento");
		preparazione();
		pompaUsata.eroga();
		System.out.println ("Auto "+ numCliente+ " lascia pompa");
		allontanamento();
		pompaUsata.lascia();
		tempoFine = (new Date()).getTime();
		System.out.println("Tempo auto "+ numCliente+ " = "+ (tempoFine-tempoInizio));
		synchronized(Auto.class) {
			tempoTotale+=(tempoFine-tempoInizio);
			numAuto++;
		}
	}
}


