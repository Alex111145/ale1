package soluzione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 
 * @author Simone Brenna
 *
 */
public class ManagerComandi {
	//TODO inizializzare managerpdi
	private ManagerPDI mpdi;
	private ManagerEventi me;
	private Comando[] listaComandi;
	
	public ManagerComandi() {
		mpdi = null;
		me = null;
		listaComandi = null;
	}
	
	//TODO Javadoc
	public ManagerComandi( ManagerEventi me ) {
		mpdi = null;
		this.me = me;
		listaComandi = null;
	}
	
	//TODO Javadoc
	public void caricaComandi( String nomefile ) {
		try {
		listaComandi = converti( Utilita.caricaStringhe( nomefile ) );
		} catch ( IOException e ) {
			e.printStackTrace();
			System.exit(0);//Errore esistenziale
		}
	}
	
	//TODO Javadoc
	//meglio passare direttamente a mc gli eventi oppure farli gestire dal "programma principale"?
	public void esegui() {
		int indice = 1;
		for( Comando comando : listaComandi ) {
			if( comando.getTipo() == TipoComando.IMPORTA ) {
				me.aggiungiEventi( (String)comando.getArgomento() );
			} else if ( comando.getTipo() == TipoComando.CREA_MAPPA ) {
				creaMappa( comando.getArgomento() );
			} else {
				System.out.println("[AVVERTIMENTO] Il comando numero " + indice + " non e' stato riconosciuto.");
			}
		}
	}
	
	/* Da implementare solo se si vuole gestire l'esecuzione dei comandi nel programma principale
	public Comando prossimoComando() {
		//TODO
		return null;
	}
	
	public boolean finito() {
		//TODO
		return false;
	}
	
	public void reset() {
		//TODO
	}
	*/
	
	/**
	 * Restituisce un array di oggetti Comando convertiti dalle stringhe ottenute in input.
	 * <p>
	 * Nel caso una stringa non possa essere convertita, verra' ignorata e la funzione continuera' a procedere.
	 * 
	 * @param dati	array di stringhe contenente i dati da convertire
	 * @return 		array di Comando contenente i comandi convertiti, nel caso in cui non si sia riuscito a convertire nessuna stringa, restituisce null.
	 */
	private Comando[] converti( String[] dati ) {
		if( dati == null || !Utilita.controlloArrayStringhe( dati ) ) {
			System.out.println("[ERRORE] la lista di stringhe data in input non contiene alcun elemento.");
		}
		
		List<Comando> listaTemporanea = new ArrayList<Comando>();
		int indice = 1;//serve solo a sapere a quale linea siamo
		
		//TODO refractoring of the data conversion code
		//TODO migliorare il controllo della formattazione del comando
		
		for( String dato : dati ) {
			if( formatoComandoCorretto( dato ) ) {
				if( dato.startsWith( "import" ) ) {
					listaTemporanea.add( new Comando( TipoComando.IMPORTA, ottieniArgomento( dato ) ) );
				} else if( dato.startsWith( "create_map" ) ) {
					listaTemporanea.add( new Comando( TipoComando.CREA_MAPPA, ottieniArgomento( dato ) ) );//temporaneo
				} else {
					//il comando non e' stato riconosciuto
					System.out.println( "[AVVERTIMENTO] comando alla linea " + indice + " non riconosciuto." );
				}
			} else {
				System.out.println("[AVVERTIMENTO] il formato del comando alla linea " + indice + " non e' corretto.");
			}
			
			indice++;
		}
		
		if( listaTemporanea.isEmpty() ) {
			return null;
		}
		
		return (Comando[])listaTemporanea.toArray( new Comando[0] );
	}
	
	/**
	 * Restituisce l'argomento passato al comando contenuto in <code>stringa</code>.
	 * <p>
	 * La funzione prevede come input un comando conforme in quanto non esegue alcun controllo
	 * 
	 * @param stringa	comando non ancora convertita da cui recuperare l'argomento
	 * @return			l'argomento del comando in forma di String
	 */
	private String ottieniArgomento( String stringa ) {
		int inizio = stringa.indexOf("(") + 1;
		int fine = stringa.indexOf(")");
		return stringa.substring( inizio, fine );
	}
	
	/**
	 * Controlla che la stringa passata in input sia conforme alle specifiche riguardante i comandi accettati dal programma.
	 * 
	 * @param comando	stringa contenente il comando da controllare.
	 * @return			true se ha superato il controllo, altrimenti false.
	 */
	private boolean formatoComandoCorretto( String comando ) {
		
		//Questo RegEx controlla che il formato del comando sia corretto
		if( !comando.matches( "[^\\W\\d\\s]*[(][a-zA-Z\\d\\.\\-]*[)]" ) ) {
			return false;
		}
		return true;
	}
	
	//TODO Javadoc
	private void creaMappa( String argomento ) {
		mpdi.resetStatistiche();
		
		String[] intervallo = argomento.split( "-" );
		
		for( Evento e : me.ricerca( intervallo[0], intervallo[1] ) ) {
			mpdi.elabora( e );
		}
		
		mpdi.stampaStatisticheAttivi();
		mpdi.stampaStatisticheTotali();
	}
}
