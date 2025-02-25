import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientPosta extends Remote {
	public void notificaNuovoMessaggio(Messaggio m) throws RemoteException;
}
