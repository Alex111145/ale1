import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Client extends UnicastRemoteObject implements RemoteObserver {
	private static final long serialVersionUID = 1L;
	protected Client() throws RemoteException {
	}
	public void remoteUpdate(Object observable, Object arg) throws RemoteException {
		System.out.println("got message:" + arg);
	}
	public static void main(String[] args) {
		int period=0;
		try {
			Random rnd=new Random();
			Registry registry = LocateRegistry.getRegistry(1099);
			TimeTickService remoteService =(TimeTickService) registry.lookup("TimeTick");
			RemoteObserver client = new Client();
			remoteService.addRemoteObserver(client);
			period=rnd.nextInt(1, 5);
			remoteService.setPeriod(client, period);
			Thread.sleep(15000);
			remoteService.setPeriod(client, period+1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
