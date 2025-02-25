package soluzione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione dei comandi di EmotionalMaps.
 * 
 * @author Simone 	Brenna 		Mat. 732520
 * @author Nicolo 	Ferrari		Mat. 732707
 * @author Luigi 	Mucciarone	Mat. 732714
 * @author Luca 	Alberti		Mat. 733096
 *
 */
public class ManagerComandi {
	private ManagerPDI mpdi;
	private ManagerEventi me;
	private Comando[] listaComandi;
	
	/**
	* Crea un nuovo ManagerComandi.
	*/
	public ManagerComandi() {
		me = new ManagerEventi();
		mpdi = new ManagerPDI();
		listaComandi = null;
	}
	
	/**
	 * Crea un nuovo ManagerComandi inizializzando il proprio ManagerEventi con uno gia' esistente.
	 */
	public ManagerComandi( ManagerEventi me ) {
		this.me = me;
		mpdi = new ManagerPDI();
		listaComandi = null;
	}
	
	/**
	 *  Carica i comandi contenuti nel file <code>nomefile</code>.
	 *  <p>
	 *  La funzione prevede come input una stringa contenente il path al file da cui
	 *  caricare i comandi per l'esecuzione del programma.
	 *  Nel caso in cui avvenga un errore irrecuperabile durante la lettura del file,
	 *  viene stampato a schermo l'errore e successivamente viene chiuso il programma.
	 *  
	 *  @param nomefile	Stringa contenente il path al file
	 */
	public void caricaComandi( String nomefile ) {
		try {
		listaComandi = converti( Utilita.caricaStringhe( nomefile ) );
		} catch ( IOException e ) {
			e.printStackTrace();
			System.exit(0);//Errore esistenziale
		}
	}
	
	/**
	 * Esegue i comandi contenuti nella lista <code>listaComandi</code>.
	 * <p>
	 * La funzione esegue i comandi contenuti in <code>listaComandi</code>. Nel caso in cui non riconoscesse
	 * un comando, stampa nella console un avvertimento con l'indice del comando non riconosciuto.
	 * 
	 */
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
	 * @param comando	Stringa contenente il comando da controllare.
	 * @return			Restituisce true se ha superato il controllo.
	 */
	private boolean formatoComandoCorretto( String comando ) {
		
		//Questo RegEx controlla che il formato del comando sia corretto
		if( !comando.matches( "[^\\W\\d\\s]*[(][a-zA-Z\\d\\.\\-]*[)]" ) ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Genera una mappa emozionale degli eventi che sono stati caricati e che sono avvenuti nell'intervallo di tempo contenuto in argomento.
	 * 
	 * @param Argomento	stringa contenente l'intervallo su cui generare la mappa emozionale.
	 */
	private void creaMappa( String argomento ) {
		mpdi.resetStatistiche();
		
		String[] intervallo = argomento.split( "-" );
		
		Evento[] listaEventi = me.ricerca( intervallo[0], intervallo[1] );
		
		if( listaEventi == null) {
			System.out.println( "[AVVERTIMENTO] non esistono eventi nell'intervallo " + argomento + "." );
		} else {
			for( Evento e : listaEventi ) {
				mpdi.elabora( e );
			}
			
			mpdi.stampaStatisticheAttivi();
			mpdi.stampaStatisticheTotali();
		}
	}
}
