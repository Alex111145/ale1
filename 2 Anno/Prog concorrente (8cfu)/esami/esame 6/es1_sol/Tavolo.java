
// classe passiva che contiene i metodi per accedere ai dati

import java.util.*;

public class Tavolo {
	static final int MAXstati=3;
	Random rnd;
	Hashtable<String, Integer> iGiocatori;

	Tavolo(){
		iGiocatori=new Hashtable<String, Integer>();
	}

	public synchronized void iniziaGioco(String nome, int stato) {	
		// bisognerebbe controllare che non sia gia` presente, ma lasciamo stare
		iGiocatori.put(nome, stato);
	}

	public synchronized void cambiaStato(String nome, int stato) {
		if(iGiocatori.containsKey(nome)) {
			iGiocatori.put(nome, stato);
		}
		notifyAll();
	}
	
	private int quantiInStato(int stato) {
		List<Integer> gliStati=new ArrayList<>(iGiocatori.values());
		int count=0;
		for(int n : gliStati) {
			if(n==stato) {
				count++;
			}
		}
		return count;
	}
	
	private boolean condizioneProgressioneOK(int count, int stato) {
		// la condizione specifica e` irrilevante
		return count==stato;
	}
	
	public synchronized boolean promozione(String nome, int t) {
		if(iGiocatori.containsKey(nome)) {
			int stato=iGiocatori.get(nome);
			int count;
			long start=System.currentTimeMillis();
			long toWait=t;
			for(;;) {
				count=quantiInStato(stato);
				System.out.println("Tavolo: trovati "+count+" giocatori in stato "+stato+" per "+nome);
				if(condizioneProgressioneOK(count, stato)) {
					return true;
				}
				toWait=start+t-System.currentTimeMillis();
				if(toWait>0) {
					try { wait(t); } catch (InterruptedException e) { }
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}
