package soluzione;

import java.util.ArrayList;
import java.util.List;

//TODO Javadoc
public class ManagerPDI {
	List<PuntoDiInteresse> listaPuntiDiInteresse;
	
	//TODO Javadoc
	public ManagerPDI() {
		listaPuntiDiInteresse = new ArrayList<PuntoDiInteresse>();
	}
	
	//TODO Javadoc
	public void aggiungiPDI( PuntoDiInteresse pdi ) {
		listaPuntiDiInteresse.add( pdi );
	}
	
	//TODO Javadoc
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
		}
		
		if( indicePDI != -1 ) {
			listaPuntiDiInteresse.get(indicePDI).aggiungiEvento(e);
		} else {
			//TODO Migliorare il testo dell'avvertimento in caso di mancata elaborazione.
			System.out.println("[AVVERTIMENTO] errore nell'elaborazione di un evento");
		}
	}
	
	//TODO Javadoc
	public void resetStatistiche() {
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			pdi.resetStatistiche();
		}
	}
	
	//TODO Javadoc
	public void stampaStatisticheTotali() {
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			pdi.StatisticheTotali();
		}
	}
	
	//TODO Javadoc
	public void stampaStatisticheAttivi() {
		for( PuntoDiInteresse pdi : listaPuntiDiInteresse ) {
			pdi.StatisticheAttivi();
		}
	}
}
