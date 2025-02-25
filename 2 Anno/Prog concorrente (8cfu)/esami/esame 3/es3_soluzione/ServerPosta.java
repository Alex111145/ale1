import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPosta  extends UnicastRemoteObject implements ServizioPosta {
	private static final long serialVersionUID = 1L;
	CasellePostali laPosta;
	// NB: per ogni utente e` previsto un solo messaggio. Per essere realistici ci vorrebbe una lista di messaggi.
	public ServerPosta  () throws RemoteException {
		super();
		laPosta=new CasellePostali();
	}

	public synchronized void put(Messaggio m) throws RemoteException {
		if(laPosta.put(m)) {
			ClientPosta cli=laPosta.getRemoteRef(m.getDestinatario());
			System.out.println("Server: notifying  "+m.getDestinatario());
			cli.notificaNuovoMessaggio(m);
		}
}

	public synchronized Messaggio read(String clientName) throws RemoteException {
		return laPosta.read(clientName);
	}

	public synchronized int numClients() throws RemoteException {
		return laPosta.numClients();
	}

	public synchronized String newClient(ClientPosta cli) throws RemoteException {
		System.out.println("Server: adding new client "+cli);
		return laPosta.addClient(cli);
	}
	
	public synchronized void removeClient(String clientName) throws RemoteException {
		laPosta.removeClient(clientName);
	}

	public static void main(String args[]) {
		try {
			ServerPosta sp = new ServerPosta();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("ServizioPosta", sp);
			System.out.println("Server running");
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}

}
