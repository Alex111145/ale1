import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

class Giocatore {
	ServerGioco gestoreGioco;
	String mioNome;
	int mioStato;
	Random rnd;
	Giocatore() throws RemoteException {
		super();
		long now=System.currentTimeMillis();
		mioNome="Giocatore_"+now;
		rnd=new Random();
	}
	void dormitina(int a) {
		int t=200+new Random().nextInt(a);
		try {
			Thread.sleep((long) t);
		} catch (InterruptedException e) {		}
	}
	int prossimoStato(int statoCorrente) {
		int statoProssimo=statoCorrente;
		while(statoProssimo==statoCorrente) {
			statoProssimo=1+rnd.nextInt(Tavolo.MAXstati);
		}
		return statoProssimo;
	}
	public void exec() {
		Registry reg;
		boolean esito;
		try {
			reg = LocateRegistry.getRegistry();
			gestoreGioco=	(ServerGioco)	reg.lookup("ServizioGioco");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		mioStato=prossimoStato(-1); // stato iniziale
		System.out.println(mioNome+" inizio in stato "+mioStato);
		try {
			gestoreGioco.iniziaGioco(mioNome, mioStato);
			for(int j=0; j<10; j++) {
				dormitina(200);
				if(rnd.nextBoolean()) { // decide cosa fare
					// cambia stato
					mioStato=prossimoStato(mioStato);
					System.out.println(mioNome+" vado in stato "+mioStato);
					gestoreGioco.cambiaStato(mioNome,mioStato);
					dormitina(500);
				} else {
					// promozione
					System.out.println(mioNome+" provo promozione ");
					esito=gestoreGioco.promozione(mioNome, 250);
					if(esito) {
						dormitina(500);
						System.out.println(mioNome+" promozione OK!");
					} else {
						System.out.println(mioNome+" promozione fallita");
					}
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws RemoteException {
		new Giocatore().exec();
		System.out.println("Client: ohibo`, termina main");
	}
}
