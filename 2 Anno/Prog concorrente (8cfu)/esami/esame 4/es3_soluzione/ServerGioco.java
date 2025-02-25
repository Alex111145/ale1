import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGioco extends Remote {
	public void mossa(String mossa) throws RemoteException;
	public void addClient(Giocatore c) throws RemoteException;
	public void removeClient(Giocatore c) throws RemoteException;
}
