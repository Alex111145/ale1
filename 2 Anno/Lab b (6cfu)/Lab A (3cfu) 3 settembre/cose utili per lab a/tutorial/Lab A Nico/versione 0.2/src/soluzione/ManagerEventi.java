package soluzione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagerEventi {
	private HashMap<String, ArrayList<Evento>> listaEventi;
	
	public ManagerEventi() {
		listaEventi = new HashMap<String, ArrayList<Evento>>();
	}
	
	/**
	 * Aggiunge gli eventi contenuti nel file nomefile alla lista totale degli eventi.
	 * 
	 * @param nomefile	Nome del file contenente gli eventi da inserire.
	 */
	public void aggiungiEventi( String nomefile ) {
		try {
			ArrayList<Evento> listaCorrente = null;
			String dataListaCorrente = null;
			
			for( Evento e : converti( Utilita.caricaStringhe( nomefile ) ) ) {
				if( dataListaCorrente == null || !dataListaCorrente.equals( e.getData() ) ) {
					dataListaCorrente = new String( e.getData() );
					if( ( listaCorrente = listaEventi.get( e.getData() ) ) == null ) {
						listaCorrente = new ArrayList<Evento>();
						listaEventi.put(dataListaCorrente, listaCorrente);
					}
					
					listaCorrente.add( e );
				}
			}
		} catch ( IOException e ) {
			System.out.println( "[AVVERTIMENTO] Non e' stato possibile aggiungere eventi alla lista" );
		}
	}
	
	public void resetEventi() {
		listaEventi = new HashMap<String, ArrayList<Evento>>();
	}
	
	/**
	 * Converte gli eventi dal formato String a Evento.
	 * <p>
	 * Nel caso dei dati fossero sbagliati o mancassero, l'evento non viene convertito e
	 * il processo non viene fermato.
	 * 
	 * @param datiGrezzi	Array di String contenente i dati da convertire.
	 * @return				Array di Evento contenente i dati convertiti.
	 */
	private Evento[] converti( String[] datiGrezzi ) {

		ArrayList<Evento> listaConvertiti = new ArrayList<Evento>();
		String[][] datiSuddivisi = Utilita.suddividiStringhe(datiGrezzi);
		int indice = 1;
		
		for( String[] dato : datiSuddivisi ) {
			boolean corretto = true;
			
			StatoRegistrazione registrazione = null;
			StatoUtente login = null;
			StatoEmotivo emozione = null;
			
			if( dato[0].contentEquals("IN") ) {
				registrazione = StatoRegistrazione.IN;
			} else if( dato[0].equals("OUT")) {
				registrazione = StatoRegistrazione.OUT;
			} else {
				corretto = false;
			}
			
			if( dato[1].equals("LOGIN") ) {
				login = StatoUtente.LOGIN;
			} else if( dato[1].equals("LOGOUT") ) {
				login = StatoUtente.LOGOUT;
			} else {
				corretto = false;
			}
			
			if( !isData(dato[2]) ) {
				corretto = false;
			}
			
			if( contieneEventoId( dato[3], listaConvertiti) != -1) {
				corretto = false;
			}
			
			switch( dato[6] ) {
				case "N": emozione = StatoEmotivo.NEUTRO; break;
				case "F": emozione = StatoEmotivo.FELICE; break;
				case "T": emozione = StatoEmotivo.TRISTE; break;
				case "A": emozione = StatoEmotivo.ARRABBIATO; break;
				case "S": emozione = StatoEmotivo.SORPRESO; break;
				default: corretto = false;
			}
			
			if( corretto ) {
				listaConvertiti.add( new Evento( registrazione, login, dato[2], dato[3], Double.parseDouble(dato[4]), Double.parseDouble(dato[5]), emozione ) );
			} else {
				System.out.println("[AVVERTIMENTO] Non e' stato possibile convertire l'evento a righa " + indice );
			}
			indice++;
		}
		
		if( listaConvertiti.isEmpty() ) {
			return null;//potrebbe dare errore in fase di runtime (null pointer exception se si usa direttamente il risultato con un foreach)
		}
		return (Evento[])listaConvertiti.toArray( new Evento[0] );
	}
	
	/**
	 * Restituisce tutti gli eventi avvenuti nei giorni tra data1 e data2 compresi gli estremi.
	 * 
	 * @param data1		data da cui iniziare la ricerca
	 * @param data2		data in cui finisce la ricerca
	 * @return			Array di Evento richiesti, null se non sono stati trovati eventi
	 */
	public Evento[] ricerca( String data1, String data2 ) {
		//Controllo se le date sono nel formato corretto
		//Controllo se data1 e' minore di data2
		ArrayList<Evento> eventi = new ArrayList<Evento>();
		//recupero gli eventi
		if( eventi.isEmpty() ) {
			return null;
		}
		return (Evento[]) eventi.toArray( new Evento[eventi.size()] );
	}
	
	private int contieneEventoId( String id, List<Evento> lista) {
		int indice = 0;
		for( Evento e : lista ) {
			if( e.getId().equals( id ) ) {
				return indice;
			}
			indice++;
		}
		return -1;
	}
	
	private boolean isData( String data ) {
		//TODO
		return false;
	}
}
