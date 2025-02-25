package soluzione;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO autore
 * Classe per la gestione dei punti di interesse.
 * 
 * @author 
 *
 */
public class ManagerPDI {
	List<PuntoDiInteresse> listaPuntiDiInteresse;
	
	/**
	 * Crea un nuovo elemento di tipo ManagerPDI.
	 */
	public ManagerPDI() {
		listaPuntiDiInteresse = new ArrayList<PuntoDiInteresse>();
		
		//Si aggiungono i tre POI definiti nelle specifiche date.
		aggiungiPDI( new PuntoDiInteresse( "POI1", 45.464, 9.190 ) );
		aggiungiPDI( new PuntoDiInteresse( "POI2", 45.473, 9.173 ) );
		aggiungiPDI( new PuntoDiInteresse( "POI3", 45.458, 9.181 ) );
	}
	
	/**
	 * Viene aggiunto il punto di interesse <code>pdi</code> alla lista dei punti di interesse.
	 *
	 * @param pdi	Punto di interesse da aggiungere.
	 */

	public void aggiungiPDI( PuntoDiInteresse pdi ) {
		listaPuntiDiInteresse.add( pdi );
	}
	
	/**
	 * Assegna l'elemento <code>e</code> al punto di interesse piu' vicino.
	 * <p>
	 * Calcola le distanze dal punto e ai vari punti di interesse e lo assegna
	 * al punto di interesse piu' vicino.
	 * 
	 * @param e		Evento da assegnare
	 */
	public void elabora( Evento e ) {
		int indice = 0;
		int indicePDI = -1;
		double distanzaMinore = (double) -1;
		double distanzaCalcolata = 0;
		
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			if( distanzaMinore != (double) -1 ) {
				distanzaCalcolata = pdi.calcolaDistanza( e.getX(), e.getY() );
				if( distanzaCalcolata < distanzaMinore ) {
					indicePDI = indice;
					distanzaMinore = distanzaCalcolata;
				}
			} else {
				indicePDI = indice;
				distanzaMinore = pdi.calcolaDistanza( e.getX(), e.getY() );
			}
			indice++;
		}
		
		if( indicePDI != -1 ) {
			listaPuntiDiInteresse.get(indicePDI).aggiungiEvento(e);
		} else {
			System.out.println("[AVVERTIMENTO] errore nell'elaborazione di un evento");
		}
	}
	
	/**
	 * Resetta le statistiche di tutti i punti di interesse.
	 */
	public void resetStatistiche() {
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			pdi.resetStatistiche();
		}
	}
	
	/**
	 * Stampa le statistiche di tutti i punti di interesse usando tutti gli eventi.
	 */
	public void stampaStatisticheTotali() {
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			System.out.println( pdi.getNome() + " - " + pdi.StatisticheTotali() );
		}
		System.out.println();
	}
	
	/**
	 * Stampa le statistiche di tutti i punti di interesse usando solo gli eventi attivi.
	 */
	public void stampaStatisticheAttivi() {
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			System.out.println( pdi.getNome() + " - " + pdi.StatisticheAttivi() );
		}
		System.out.println();
	}
}
