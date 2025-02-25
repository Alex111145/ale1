import java.util.Enumeration;
import java.util.Hashtable;

public class CasellePostali {
	private Hashtable<String, Messaggio> messaggi;
	private Hashtable<String, ClientPosta> clients;
	// NB: per ogni utente e` previsto un solo messaggio. Per essere realistici ci vorrebbe una lista di messaggi.
	public CasellePostali () {
		messaggi = new Hashtable<String, Messaggio>();
		clients=new Hashtable<String, ClientPosta>();
	}

	public synchronized ClientPosta getRemoteRef(String clientName) {
		return clients.get(clientName);
	}
	
	public synchronized String addClient(ClientPosta cli) {
		int numClients= clients.size()+1;
		String newClientName="Client_"+numClients;
		System.out.println("Posta: adding new client "+cli +" with name "+newClientName);
		clients.put(newClientName, cli);
		show();
		System.out.println("Posta: added");
		return newClientName;
	}
	
	public synchronized void removeClient(String clientName) {
		clients.remove(clientName);
		messaggi.remove(clientName);
	}

	public synchronized int numClients() {
		return clients.size();
	}

	public synchronized boolean put(Messaggio m) {
		boolean result=false;
		String dest=m.getDestinatario();
		if(clients.containsKey(dest)) {
			messaggi.put(dest, m);
			result=true;
			System.out.println("PostOffice:  memorizzato messaggio  "+ m);
		} else {
			System.out.println("PostOffice:  messaggio  per cliente inesistente "+ dest+" ignorato");			
		}
		show();
		return result;
	}

	public synchronized Messaggio read(String clientName)  {
		Messaggio msg = null;
		if(!clients.contains(clientName)) {
			return msg;
		}
		if(messaggi.containsKey(clientName)) {
			msg=messaggi.remove(clientName);
			System.out.println("PostOffice:  letto messaggio  "+ msg);
		}
		show();
		return msg;
	}

	private void show() {
		Enumeration<String> keys = messaggi.keys();
		Enumeration<String> ckeys = clients.keys();
		System.out.println("=========== caselle postali ===================");
		while(ckeys.hasMoreElements()){
			String str = ckeys.nextElement();
			System.out.print(str+"   ");
		}
		System.out.println();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			System.out.println("@"+key+": "+messaggi.get(key));
		}
		System.out.println("==========================================");
	}


}
