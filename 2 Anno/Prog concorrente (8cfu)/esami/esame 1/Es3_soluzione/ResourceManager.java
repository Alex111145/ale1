import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ResourceManager  extends UnicastRemoteObject implements ResManagerInterface{
	private static final long serialVersionUID = 1L;
	LinkedList<Resource> listA;
	LinkedList<Resource> listB;
	ResourceManager() throws RemoteException{
		super();
		listA = new LinkedList<>();  
		listB = new LinkedList<>();  
	}
	public synchronized Resource getA() {
		while(listA.size()==0) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		return listA.remove(0);
	}
	public synchronized Resource getB() {
		while(listB.size()==0) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		return listB.remove(0);
	}
	public synchronized void put(Resource r) {
		if(r.getType()==ResourceType.A) {
			listA.add(r);
		}
		if(r.getType()==ResourceType.B) {
			listB.add(r);
		}
		notifyAll();
	}
}
