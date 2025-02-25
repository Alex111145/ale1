import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class CasellePostali {
	private Hashtable<String, Messaggio> messaggi;
	private List<String> clients;
	// NB: per ogni utente e` previsto un solo messaggio. Per essere realistici ci vorrebbe una lista di messaggi.
	public CasellePostali () {
		messaggi = new Hashtable<String, Messaggio>();
		clients=new ArrayList<String>();
	}
	
	public synchronized String newClient() {
		int numClients= clients.size()+1;
		String newClientName="Client_"+numClients;
		clients.add(newClientName);
		return newClientName;
	}
	
	public synchronized int numClients() {
		return clients.size();
	}
	
	public synchronized void put(Messaggio m) {
		String dest=m.getDestinatario();
		if(clients.contains(dest)) {
			messaggi.put(dest, m);
			System.out.println("PostOffice:  memorizzato messaggio  "+ m);
			show();
			notifyAll();
		} else {
			System.out.println("PostOffice:  messaggio  per cliente inesistente "+ dest+" ignorato");			
		}
	}
	
	public synchronized Messaggio read(String clientName, long timeout)  {
		Messaggio msg = null;
		long now;
		long inizioAttesa=System.currentTimeMillis();
		if(!clients.contains(clientName)) {
			return msg;
		}
		while(true) {
			if(messaggi.containsKey(clientName)) {
				msg=messaggi.remove(clientName);
				break;
			}
			now=System.currentTimeMillis();
			if(now>=inizioAttesa+timeout) {
				return msg;
			} else {
				try {
					long tot=timeout-(now-inizioAttesa);
					System.out.println("PostOffice:  "+clientName+" va in attesa per "+tot +" ms. **************************  ");
					wait(tot);
				} catch (InterruptedException e) {		}
			}
		}
		if(msg!=null) {
			System.out.println("PostOffice:  letto messaggio  "+ msg);
		}
		show();
		return msg;
	}

	private void show() {
		Enumeration<String> keys = messaggi.keys();
		System.out.println("=========== caselle postali ===================");
		for(String s: clients) {
			System.out.print(s+"   ");
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			System.out.println("@"+key+": "+messaggi.get(key));
		}
		System.out.println("==========================================");
	}
}
