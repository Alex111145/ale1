import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerGiocoImpl  extends UnicastRemoteObject implements ServerGioco {
	private static final long serialVersionUID = 1L;
	Tavolo ilTavolo;
	Gestore ilGestore;
	List<Giocatore> listaClienti;
	// NB: per ogni utente e` previsto un solo messaggio. Per essere realistici ci vorrebbe una lista di messaggi.
	public ServerGiocoImpl  () throws RemoteException {
		super();
		ilTavolo = new Tavolo();
		ilGestore=new Gestore(ilTavolo);
		listaClienti=new ArrayList<Giocatore>();
	}

	public void mossa(String m) throws RemoteException {
		String nuovaSituazione=ilGestore.mossa(m);
		// manda notifica a tutti, anche a chi ha fatto la mossa
		for(Giocatore cli: listaClienti) {
			cli.notificaSituazione(nuovaSituazione);
		}
	}

	public void addClient(Giocatore c) throws RemoteException {
		listaClienti.add(c);
	}

	public void removeClient(Giocatore c) throws RemoteException {
		listaClienti.remove(c);
	}	

	public static void main(String args[]) {
		try {
			ServerGiocoImpl sp = new ServerGiocoImpl();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("ServizioGioco", sp);
			System.out.println("Server running");
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
}
