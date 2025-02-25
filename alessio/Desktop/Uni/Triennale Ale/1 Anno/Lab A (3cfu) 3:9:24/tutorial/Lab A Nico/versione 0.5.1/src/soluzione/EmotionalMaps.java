package soluzione;

import java.io.File;

//TODO Javadoc
public class EmotionalMaps {
	
	private static ManagerComandi mc = null;

	//TODO Javadoc? yes
	/**
	 * Gestisce il flusso del programma
	 * <p>
	 * Inizialmente controlla la correttezza degli argomenti presenti in <code>args</code>.
	 * Successivamente carica i comandi e procede all'esecuzione degli stessi.
	 * 
	 * @param args		Array contenente gli argomenti passati dalla linea di comando
	 */
	public static void main(String[] args) {
		//PASSO 1: controllare gli argomenti passati
		if( !controllaArgomenti( args ) ) {
			System.exit(0);
		}
		
		//PASSO 2: caricare i comandi
		importa( args[0] );
		
		//PASSO 3: eseguire i comandi
		esegui();
	}
	
	/**
	 * Controlla che i parametri forniti dalla linea di comando siano corretti.
	 * <p>
	 * I controlli eseguiti sono: Numero degli argomenti, estensione del file ed esistenza del file fornito.
	 * 
	 * @param args 	array di stringhe contenente tutti i parametri passati dalla linea di comando
	 * @return 		true se vengono superati tutti i controlli, altrimenti false
	 */
	public static boolean controllaArgomenti( String[] args ) {
		
		//controllo se c'e' il numero minimo di argomenti
		if( args.length < 1 ) {
			System.out.println( "[ERRORE] Non e' stato passato alcun argomento, inserire il pth con il nome del file contenente i comndi." );
			return false;
		}
		
		//controllo se sono stati passati piu' argomenti ( viene usato solo il primo)
		if( args.length > 1 ) {
			System.out.println( "[AVVERTIMENTO] Sono stati passati piu' argomenti di quelli necessari.\n verra' usato solo: \"" + args[0] + "\"." );
		}
		
		//controllo che il nome del file contenga l'estensione
		//dovrebbe restituire un booleano e modificare direttamente la stringa?
		args[0] = Utilita.aggiungiEstensione( args[0], ".txt" );
				
		//controllo che il file esista
		File file = new File( args[0] );
		if( !file.exists() ) {
			System.out.println( "[ERRORE] Il file \"" + args[0] + "\" non esiste." );
			return false;//ERRORE IL FILE NON ESISTE
		}
		
		//sono stati passati tutti i controlli
		return true;
	}
	
	/**
	 * Carica i comandi contenuti in nomefile nel ManagerComandi
	 * <p>
	 * Tutti i comandi non conformi vengono ignorati lasciando proseguire il funzionamento del programma
	 * 
	 * @param nomefile	stringa contenente il path per il file da cui caricare i comandi
	 */
	public static void importa( String nomefile ) {
		
		if( mc == null ) {
			mc = new ManagerComandi();
		}
		
		//carico i comandi dal file "nomefile"
		mc.caricaComandi( nomefile );
	}
	
	/**
	 * Esegue serialmente i comandi caricati nel ManagerComandi
	 */
	public static void esegui() {
		mc.esegui();
	}

}
