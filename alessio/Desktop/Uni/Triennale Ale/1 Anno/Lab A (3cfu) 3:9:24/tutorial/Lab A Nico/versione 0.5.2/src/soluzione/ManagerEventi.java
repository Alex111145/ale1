package soluzione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO autore
 * Classe per la gestione degli eventi.
 * 
 * @author 
 *
 */
public class ManagerEventi {
	private HashMap<String, ArrayList<Evento>> listaEventi;
	
	/**
	 * Crea un nuovo elemento di tipo ManagerEventi.
	 */
	public ManagerEventi() {
		listaEventi = new HashMap<String, ArrayList<Evento>>();
	}
	
	/**
	 * Aggiunge gli eventi contenuti nel file <code>nomefile</code> alla lista totale degli eventi.
	 * 
	 * @param nomefile	Nome del file contenente gli eventi da inserire.
	 */
	public void aggiungiEventi( String nomefile ) {
		try {
			ArrayList<Evento> listaCorrente = null;
			String dataListaCorrente = null;
			
			String[] stringhe = Utilita.caricaStringhe( nomefile );
			
			if( stringhe != null ) {
			
				for( Evento e : converti( stringhe ) ) {
					if( dataListaCorrente == null || !dataListaCorrente.equals( e.getData() ) ) {
						dataListaCorrente = new String( e.getData() );
						if( ( listaCorrente = listaEventi.get( e.getData() ) ) == null ) {
							listaCorrente = new ArrayList<Evento>();
							listaEventi.put(dataListaCorrente, listaCorrente);
						}
						
						listaCorrente.add( e );
					} else {
						listaCorrente.add( e );
					}
				}
			}
		} catch ( IOException ioe ) {
			System.out.println( "[AVVERTIMENTO] Non e' stato possibile aggiungere eventi alla lista" );
		}
	}
	
	/**
	 * Crea un nuovo elemento di tipo resetEventi.
	 */
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
			
			if( !controllaData(dato[2]) ) {
				corretto = false;
			}
			
			//genera errori perche id non appartiene all'evento ma all'utente
			/*
			if( contieneEventoId( dato[3], listaConvertiti) != -1) {
				corretto = false;
			}
			*/
			
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
		String dataCorrente;
		ArrayList<Evento> eventi = new ArrayList<Evento>();
		//Controllo se le date sono nel formato corretto
		if( !controllaData(data1) || !controllaData(data2) ) {
			return null;
		}
		
		//Controllo se data1 e' minore di data2
		if( dopoDiData( data1, data2 ) ) {
			System.out.println( "[AVVERTIMENTO] data1 e' successiva a data2: " + data1 + " > " + data2 );
			System.out.println( "               La ricerca corrente verra' saltata." );
			return null;
		}
		
		//recupero gli eventi
		for( dataCorrente = data1; !dopoDiData( dataCorrente, data2 ); dataCorrente = creaDataSuccessiva( dataCorrente ) ) {
			ArrayList<Evento> lista = listaEventi.get( dataCorrente );
			if ( lista != null ) {
				eventi.addAll( listaEventi.get( dataCorrente ) );
			}
		}
		
		//restituisco gli eventi trovati
		if( eventi.isEmpty() ) {
			return null;
		}
		return (Evento[]) eventi.toArray( new Evento[eventi.size()] );
	}
	
	/**
	 * Verifica che nella lista sia presente l'id dell'evento specificato con <code>id</code>.
	 * 
	 * @param id		Id dell'evento da verificare
	 * @param lista		Lista in cui viene effettuata la ricerca
	 * @return			Restituisce la posizione dell'evento trovato
	 */
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
	
	/**
	 * Controlla che il formato della data sia corretto.
	 * 
	 * @param data		data su cui viene effettuato il controllo
	 * @return			Restituisce true se il formato e' corretto
	 */
	private boolean controllaData( String data ) {
		
		if( data.length() != 8 ) {
			return false;
		}
		
		for( int i = 0; i < data.length(); i++ ) {
			if( !Character.isDigit( data.charAt(i) ) ) {
				return false;
			}
		}
				
		//controllo se i numeri sono nell'intervallo corretto
		int giorno = Integer.parseInt( data.substring(0, 2) );
		int mese = Integer.parseInt( data.substring(2, 4) );
		int anno = Integer.parseInt( data.substring(4) );
		
		if( anno < 1970 ) {
			return false;
		}
		
		if( giorno < 1 || mese < 1 || anno < 1 ) {
			return false;
		}
		
		if( mese > 12 || giorno > 31 ) {
			return false;
		}
		
		if( mese == 2 && giorno > 29 ) {
			return false;
		}
		
		if( (mese == 3 || mese == 6 || mese == 9 || mese == 11 ) && giorno > 30 ) {
			return false;
		}
		
		if( mese == 2 && !Utilita.bisestile( anno ) && giorno > 28 ) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica che <code>data1</code> preceda <code>data2</code>.
	 * 
	 * @param data1		Data sulla quale viene eseguito il controllo
	 * @param data2		Data di riferimento per il controllo
	 * @return			Restituisce true nel caso data1 preceda data2
	 */
	private boolean primaDiData( String data1, String data2 ) {
		int giorno1 = Integer.parseInt( data1.substring(0, 2) );
		int mese1 = Integer.parseInt( data1.substring(2, 4) );
		int anno1 = Integer.parseInt( data1.substring(4) );
		
		int giorno2 = Integer.parseInt( data2.substring(0, 2) );
		int mese2 = Integer.parseInt( data2.substring(2, 4) );
		int anno2 = Integer.parseInt( data2.substring(4) );
		
		if ( anno1 < anno2 ) {
			return true;
		}
		
		if( anno1 == anno2 ) {
			if ( mese1 < mese2 ) {
				return true;
			}
			if( mese1 == mese2 && giorno1 < giorno2 ) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Verifica che <code>data1</code> e <code>data2</code> fanno riferimento alla stessa data.
	 * 
	 * @param data1		Prima data
	 * @param data2		Seconda data
	 * @return			Restituisce true nel caso le date coincidano
	 */
	private boolean stessaData( String data1, String data2 ) {
		int giorno1 = Integer.parseInt( data1.substring(0, 2) );
		int mese1 = Integer.parseInt( data1.substring(2, 4) );
		int anno1 = Integer.parseInt( data1.substring(4) );
		
		int giorno2 = Integer.parseInt( data2.substring(0, 2) );
		int mese2 = Integer.parseInt( data2.substring(2, 4) );
		int anno2 = Integer.parseInt( data2.substring(4) );
		
		if( giorno1 == giorno2 && mese1 == mese2 && anno1 == anno2 ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica che data1 succeda data2.
	 * 
	 * @param data1		data sulla quale viene eseguito il controllo
	 * @param data2		data di riferimento per il controllo
	 * @return			Restituisce true nel caso data1 succeda data2
	 */
	private boolean dopoDiData( String data1, String data2 ) {
		int giorno1 = Integer.parseInt( data1.substring(0, 2) );
		int mese1 = Integer.parseInt( data1.substring(2, 4) );
		int anno1 = Integer.parseInt( data1.substring(4) );
		
		int giorno2 = Integer.parseInt( data2.substring(0, 2) );
		int mese2 = Integer.parseInt( data2.substring(2, 4) );
		int anno2 = Integer.parseInt( data2.substring(4) );
		
		if( anno1 > anno2 ) {
			return true;
		}
		
		if( anno1 == anno2 ) {
			if ( mese1 > mese2 ) {
				return true;
			}
			if( mese1 == mese2 && giorno1 > giorno2 ) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Genera la data successiva a quella data in input.
	 * <P>
	 * Genera la data successiva a quella data in input, se la data di input non e' corretta
	 * restituisce null.
	 * 
	 * @param 	data	Data da cui generare la data successiva.
	 * @return	Restituisce la data successiva oppure null in caso di errore.
	 */
	private String creaDataSuccessiva( String data ) {
		
		if( !controllaData( data ) ) {
			return null;
		}
		
		String giornoStringa, meseStringa, annoStringa;
		int giorno = Integer.parseInt( data.substring(0, 2) );
		int mese = Integer.parseInt( data.substring(2, 4) );
		int anno = Integer.parseInt( data.substring(4) );
		
		//passo alla data successiva
		giorno++;
		
		if( giorno > 31 ) {
			giorno = 1;
			mese++;
		}
		
		if( ( giorno > 30) && ( mese == 11 || mese == 9 || mese == 4 || mese == 6 ) ) {
			giorno = 1;
			mese++;
		}
		
		if( mese == 2 ) {
			if( Utilita.bisestile( anno ) && giorno > 29 ) {
				giorno = 1;
				mese++;
			} else if ( giorno > 28 ) {
				giorno = 1;
				mese++;
			}
		}
		
		if( mese > 12 ) {
			mese = 1;
			anno++;
		}
		
		//formatto i giorni in stringhe
		if( giorno < 10 ) {
			giornoStringa = "0" + giorno;
		} else {
			giornoStringa = Integer.toString( giorno );
		}
		
		if( mese < 10 ) {
			meseStringa = "0" + mese;
		} else {
			meseStringa = Integer.toString( mese );
		}
		
		annoStringa = Integer.toString( anno );
		
		//restituisco la nuova data
		return giornoStringa + meseStringa + annoStringa;
	}

}
