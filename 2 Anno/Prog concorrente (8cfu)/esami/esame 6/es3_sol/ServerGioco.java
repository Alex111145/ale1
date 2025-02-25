import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGioco extends Remote {
	public void iniziaGioco(String nome, int stato) throws RemoteException;
	public void cambiaStato(String nome, int stato) throws RemoteException;
	public boolean promozione(String nome, int t) throws RemoteException;
}
