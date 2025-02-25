package soluzione;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contiene diversi metodi usati in piu parti del programma dove richiedono delle elaborazioni parziali.
 * 
 * @author Simone Brenna
 *
 */
public class Utilita {

	/**
	 * Carica le stringhe contenute nel file in un array di oggetti String.
	 * 
	 * @param nomefile		file path da cui estrarre le stringhe
	 * @return				un array di oggetti String contenente le stringhe del file
	 * @throws IOException
	 */
	public static String[] caricaStringhe( String nomefile ) throws IOException {
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader( nomefile );
		} catch ( FileNotFoundException ex ) {
			System.out.println( "[AVVERTIMENTO] non e' stato possibile accedere a \"" + nomefile + "\"" );
			return null;
		}
			
		BufferedReader bufferedReader = new BufferedReader( fileReader );
		ArrayList<String> listaStringhe = new ArrayList<String>();
		String buffer = null;
		
		while( ( buffer = bufferedReader.readLine() ) != null ) {
			listaStringhe.add(buffer);
		}
		
		bufferedReader.close();
		
		if( listaStringhe.isEmpty() ) {
			return null;
		}
		
		return (String[])listaStringhe.toArray( new String[0] );
	}
	
	/**
	 * Esegue il metodo <code>split</code> su tutte le stringhe date in input.
	 * <p>
	 * I marcatori per la suddivisione sono "," (virgola) e " " (spazio).
	 * 
	 * @param lista		e' l'array di stringhe su cui verra eseguito split
	 * @return			un array di array di stringhe
	 */
	public static String[][] suddividiStringhe( String[] lista ) {
		if( !controlloArrayStringhe( lista ) ) {
			return null;
		}
		
		String[][] risultato = new String[lista.length][];
		for( int i = 0; i < lista.length; i++ ) {
			risultato[i] = lista[i].split(" |,");
		}
		
		return risultato;
	}
	
	/**
	 * Controlla che alcun puntatore a String contenuto nell'array sia <code>null</code> oppure una stringa vuota.
	 * 
	 * @param lista		array su cui verranno eseguiti i controlli
	 * @return			true se vengono superati i controlli, altrimenti false
	 */
	public static boolean controlloArrayStringhe( String[] lista ) {
		if( lista == null || lista.length == 0 ) {
			return false;
		}
		
		for( String s : lista ) {
			if( s == null || s.isEmpty() ) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Aggiunge l'estensione indicata alla stringa passata solo nel caso non fosse gia presente.
	 * 
	 * @param nomefile		stringa a cui verra aggiunta l'estensione
	 * @param estensione	stringa contenete l'estensione
	 * @return				restituisce l'oggetto String originale con aggiunta l'estensione
	 */
	public static String aggiungiEstensione( String nomefile, String estensione ) {
		if( !nomefile.endsWith(estensione) ) {
			return nomefile + estensione;
		}
		return nomefile;
	}
	
	/**
	 * Calcola la distanza tra due punti.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return	la distanza tra i due punti.
	 */
	public static double distanza( double x1, double y1, double x2, double y2 ) {
		//TODO
		return (double)0;
	}
	
}
