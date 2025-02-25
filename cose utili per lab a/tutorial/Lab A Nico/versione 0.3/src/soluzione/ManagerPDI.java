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
		//TODO
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
