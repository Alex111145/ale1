import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

class ClientPostaImpl extends UnicastRemoteObject implements ClientPosta {
	private static final long serialVersionUID = 1L;
	String name;
	ServizioPosta gestorePosta;
	ClientPostaImpl() throws RemoteException {
		super();
	}
	private void termina() {
		try {
			gestorePosta.removeClient(name);
			UnicastRemoteObject.unexportObject(this, true);
		} catch (RemoteException e1) {
			System.exit(0);
		}	
	}
	public void exec() {
		Messaggio msg;
		Registry reg;
		String dest = null;
		boolean destOK=false;
		try {
			reg = LocateRegistry.getRegistry();
			gestorePosta=	(ServizioPosta)	reg.lookup("ServizioPosta");
			name=gestorePosta.newClient(this);
			for(int j=0; j<10; j++) {
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(200,300));
				} catch (InterruptedException e) { }
//				System.out.println(name+" iterazione "+j);	
				// manda messaggio
				destOK=false;
				while(!destOK) {
					dest="Client_"+ThreadLocalRandom.current().nextInt(1,1+gestorePosta.numClients());
					destOK=!name.equals(dest);
				}
				msg=new Messaggio(name, dest, "msg da "+name+" #"+j);
				System.out.println(name+" scrivo "+msg);	
				gestorePosta.put(msg);
				System.out.println(name+" scrittura completata");	
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			termina();
		}
		termina();
	}

	public void notificaNuovoMessaggio(Messaggio msg) throws RemoteException {
		System.out.println(name+" ricevuto "+msg);	
	}
	public static void main(String args[]) throws RemoteException {
		new ClientPostaImpl().exec();
		System.out.println("Client: ohibo`, termina main");
	}
}
