package concorrente;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class CasellePostali {
    private List<String> clients;  // lista dei nomi dei clienti.
    private Hashtable<String, Messaggio> messaggi;	// NB: per ogni utente e` previsto un solo messaggio. Per essere realistici ci vorrebbe una lista di messaggi.
    public CasellePostali () {
        messaggi = new Hashtable<String, Messaggio>();
        clients=new ArrayList<String>();
    }

    // aggiunge un nuovo nome di cliente alla lista e lo restituisce come risultato
    public synchronized String newClient() {
        int numClients= clients.size()+1;
        String newClientName="Client_"+numClients;
        clients.add(newClientName);
        return newClientName;
    }

    // restituisce il numero di clienti
    public synchronized int numClients() {
        return clients.size();
    }

    // inserisce un messaggio (sovrascrivendo il messaggio eventualmente gia` presente per il destinatario
    public synchronized void put(Messaggio m) {
        String dest=m.getDestinatario();  // dest e` il destinatario del messaggio
        if(clients.contains(dest)) {
            messaggi.put(dest, m);
            System.out.println("PostOffice:  memorizzato messaggio  "+ m);
            notifyAll();
            show();
        } else {
            // se il destinatario non compare nella lista dei clienti il mesaggio viene ignorato
            System.out.println("PostOffice:  messaggio  per cliente inesistente "+ dest+" ignorato");
        }
    }

    // legge l'eventuale messaggio per il cliente indicato come parametro
    public synchronized Messaggio read(String clientName, long tempo) {
        long now;
        long inizioAttesa = System.currentTimeMillis();
        Messaggio msg = null;
        // se il nome dato non compare nella lista dei clienti si restituisce null
        if (!clients.contains(clientName)) {
            return msg;
        }
        while (true) {
            // se il c'e` un messaggio per il cliente, viene estratto e restituito come risultato
            if (messaggi.containsKey(clientName)) {
                msg = messaggi.remove(clientName);
                System.out.println("PostOffice:  letto messaggio  " + msg);
                break;
            }
            now = System.currentTimeMillis();
            if (now >=  inizioAttesa + tempo){
                return msg;
            } else {
                try {
                    long tot=tempo-(now-inizioAttesa);
                    System.out.println("PostOffice:  "+clientName+" va in attesa per "+tot +" ms. **************************  ");
                    wait(tot);
                }catch (InterruptedException e) {		}
            }
        }
        if(msg!=null) {
            System.out.println("PostOffice:  letto messaggio  "+ msg);
        }
        show();  // mostra il contenuto delle caselle postali
        return msg;
    }

    // mostra il contenuto delle caselle postali
    private synchronized void show() {
        Enumeration<String> keys = messaggi.keys();
        System.out.println("=========== caselle postali ===================");
        // mostra l'elenco dei clienti
        for(String s: clients) {
            System.out.print(s+"   ");
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------");
        // mostra il contenuto delle caselle postali
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println("@"+key+": "+messaggi.get(key));
        }
        System.out.println("==========================================");
    }
}
