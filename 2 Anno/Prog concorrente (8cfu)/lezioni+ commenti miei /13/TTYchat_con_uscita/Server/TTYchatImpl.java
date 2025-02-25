import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;

public class TTYchatImpl extends UnicastRemoteObject implements TTYchat {
	private static final long serialVersionUID = 1L;
	List<TTYchatClient> occupants;
	public TTYchatImpl() throws RemoteException {
		super();
		occupants = new ArrayList<TTYchatClient>();
	}
	public synchronized void enterRoom(TTYchatClient c) throws RemoteException {
		occupants.add(c);
	}
	public synchronized void exitRoom(TTYchatClient cc) throws RemoteException {
		System.out.println("Server: "+cc.name()+" is being removed");
		occupants.remove(cc);
	}
	public synchronized void  saySomething(String s, TTYchatClient cc) throws RemoteException {
		String message = cc.name()+": "+s;
		System.out.println(Thread.currentThread() + ":Server: got " +message);
		for(TTYchatClient ttyc: occupants) {
			try {
				if(!ttyc.equals(cc)) {
					ttyc.somethingSaid(message);
				}
			} catch (Exception x) {
				System.out.println("Someone left");
				occupants.remove(ttyc);
			}
		}
	}
}

