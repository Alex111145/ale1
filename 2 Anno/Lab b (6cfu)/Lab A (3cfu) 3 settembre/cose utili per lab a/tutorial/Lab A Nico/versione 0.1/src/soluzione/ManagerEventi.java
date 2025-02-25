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
	
	public Evento[] ricerca() {
		//TODO
		return null;
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
