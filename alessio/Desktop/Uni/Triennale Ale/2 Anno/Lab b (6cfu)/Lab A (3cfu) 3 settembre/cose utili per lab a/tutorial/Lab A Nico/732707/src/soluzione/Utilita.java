package soluzione;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contiene vari metodi di uso comune del programma dove avvengono delle elaborazioni parziali.
 * 
 * @author Simone 	Brenna 		Mat. 732520
 * @author Nicolo 	Ferrari		Mat. 732707
 * @author Luigi 	Mucciarone	Mat. 732714
 * @author Luca 	Alberti		Mat. 733096 
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
			System.out.println("[AVVERTIMENTO] il file \"" + nomefile + "\" non contiene eventi." );
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
	 * Calcola la distanza assoluta tra due punti.
	 * 
	 * @param x1 Coordinata sull'asse x del primo punto.
	 * @param y1 Coordinata sull'asse y del primo punto.
	 * @param x2 Coordinata sull'asse x del secondo punto.
	 * @param y2 Coordinata sull'asse y del secondo punto.
	 * 
	 * @return	la distanza assoluta tra i due punti.
	 */
	public static double distanza( double x1, double y1, double x2, double y2 ) {
		if( x1 > x2 ) {
			double tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		
		if( y1 > y2 ) {
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		double dx = x2 - x1;
		double dy = y2 - y1;
		
		return Math.sqrt( ( (dx * dx) + (dy * dy) ) );
	}
	
	

	/**
	 * <code>anno</code> verifica se <code>anno</code> e' bisestile
	 * @param anno 	L'anno su cui eseguire il controllo
	 * @return		Restituisce true se <code>anno</code> e' bisestile
	 */
	public static boolean bisestile( int anno ) {
		if( anno % 400 == 0 ) {
			return true;
		}
		
		if( anno % 4 == 0 && anno % 100 != 0 ) {
			return true;
		}
		
		return false;
	}
}
