import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
	static final int numResources=3;
	public static void main(String[] args) {
		ResourceManager rm;
		try {
			rm = new ResourceManager();
			for(int i=0; i<numResources; i++) {
				rm.put(new Resource(ResourceType.A));
				rm.put(new Resource(ResourceType.B));
			}
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("GestoreRisorse", rm);
		} catch (RemoteException e) {
			System.out.println("Problemi col server.");
			System.exit(1);
		}
	}
}
