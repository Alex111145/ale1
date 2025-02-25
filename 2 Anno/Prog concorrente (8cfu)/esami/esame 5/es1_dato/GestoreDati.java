
// classe passiva che contiene i metodi per accedere ai dati

import java.util.*;

public class GestoreDati {
	Random rnd;
	List<Partita> lePartite;
	List<Scommessa> leScommesse;

	GestoreDati(){
		lePartite = new ArrayList<Partita>();
		leScommesse=new ArrayList<Scommessa>();
		rnd=new Random();
	}
	
	public void inserisciPartita(String s, Date d) {
		// bisognerebbe controllare che la partita non esista gia`, ma per semplicita` non lo facciamo
		lePartite.add(new Partita(s, d));
	}
	
	public Partita getPartitaByPosition(int i) {
		if(lePartite.size()>i) {
			return lePartite.get(i);
		} else {
			return null;
		}
	}
	
	public void inserisciRisultato(String s, Date d, String r) {
		// cerca la partita in questione e ne inserisce il risultato
		for(Partita p: lePartite) {
			if(p.getNome().equals(s) && p.getData().equals(d)) {
				p.setRisultato(r);
				break;
			}
		}
		// aggiorna scommesse che riguardano il risultato appena inserito
		for(Scommessa sc: leScommesse) {
			Partita ps=sc.getLaPartita();
			if(ps.getNome().equals(s) && ps.getData().equals(d)) {
				// l'esito della scommessa e` deciso a caso, per semplicita`
				if(rnd.nextBoolean()){
					sc.setEsito(EsitoScommessa.Vinta);
				} else {
					sc.setEsito(EsitoScommessa.Persa);
				}
			}
		}
	}
	
	public void inserisciQuotazione(String s, Date d, String q) {
		for(Partita p: lePartite) {
			if(p.getNome().equals(s) && p.getData().equals(d)) {
				p.setQuotazione(q);
				break;
			}
		}
	}

	public String leggiQuotazione(String s, Date d) {
		for(Partita p: lePartite) {
			if(p.getNome().equals(s) && p.getData().equals(d)) {
				return p.getQuotazione();
			}
		}
		return null;
	}

	public boolean piazzaScommessa(String nomePartita, Date dataPartita, String nomeScommettitore, String descrizioneScommessa, int importo) {
		for(Partita p: lePartite) {
			if(p.getNome().equals(nomePartita) && p.getData().equals(dataPartita)) {
				leScommesse.add(new Scommessa(p, nomeScommettitore, importo, descrizioneScommessa));
				return true;
			}
		}
		return false;
	}

	private Scommessa trovaScommessa(String nomePartita, Date dataPartita, String nomeScommettitore) {
		for(Scommessa sc: leScommesse) {
			if(sc.getNomeScommettitore().equals(nomeScommettitore)) {
					return sc;
			}
		}
		return null;
	}
	
	public EsitoScommessa leggiEsito(String nomePartita, Date dataPartita, String nomeScommettitore) {
		// per semplicita` si suppone che uno scommettitore piazzi una sola scommessa su una data partita
		Scommessa sc=trovaScommessa(nomePartita, dataPartita, nomeScommettitore);
		if(sc!=null) {
			Partita pt=sc.getLaPartita();
			if(pt.getNome().equals(nomePartita) && pt.getData().equals(dataPartita)) {
				return sc.getEsito();
			}
		}
		return null;
	}
	
}
