import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ResManagerInterface extends Remote {
	Resource getA() throws RemoteException;
	Resource getB() throws RemoteException;
	void put(Resource r) throws RemoteException;
}
