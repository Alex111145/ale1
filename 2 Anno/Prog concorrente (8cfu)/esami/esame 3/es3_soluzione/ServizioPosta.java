import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServizioPosta extends Remote {
	public void put(Messaggio m) throws RemoteException;
	public Messaggio read(String clientName) throws RemoteException;
	public String newClient(ClientPosta cli) throws RemoteException;
	public void removeClient(String clientName) throws RemoteException;
	public int numClients() throws RemoteException;
}
