package soluzione;

/**
 * Classe per la rappresentazione dei punti di interesse.
 * 
 * @author Simone 	Brenna 		Mat. 732520
 * @author Nicolo 	Ferrari		Mat. 732707
 * @author Luigi 	Mucciarone	Mat. 732714
 * @author Luca 	Alberti		Mat. 733096
 *
 */
public class PuntoDiInteresse {
	private String nome;
	private double x = 0;
	private double y = 0;
	
	private int eventiTotali;
	private int eventiArrabbiato;
	private int eventiFelice;
	private int eventiSorpreso;
	private int eventiTriste;
	private int eventiNeutro;
	
	private int eventiTotaliAttivi;
	private int eventiArrabbiatoAttivi;
	private int eventiFeliceAttivi;
	private int eventiSorpresoAttivi;
	private int eventiTristeAttivi;
	private int eventiNeutroAttivi;
	
	/**
	 * Genera un nuovo punto di interesse.
	 * 
	 * @param nome	Il nome del punto di interesse
	 * @param x		Coordinata dell'asse X
	 * @param y		Coordinata dell'asse Y
	 */
	public PuntoDiInteresse( String nome, double x, double y ) {
		this.nome = nome;
		this.x = x;
		this.y = y;
		
		eventiTotali = 0;
		eventiArrabbiato = 0;
		eventiFelice = 0;
		eventiSorpreso = 0;
		eventiTriste = 0;
		eventiNeutro = 0;
		
		eventiTotaliAttivi = 0;
		eventiArrabbiatoAttivi = 0;
		eventiFeliceAttivi = 0;
		eventiSorpresoAttivi = 0;
		eventiTristeAttivi = 0;
		eventiNeutroAttivi = 0;
	}
	
	/**
	 * Restituisce il nome del punto di interesse.
	 * 
	 * @return		Restituisce la stringa contenente il nome.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Restituisce la coordinata x del punto di interesse
	 * 
	 * @return		Restituisce il valore della coordinata x.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Restituisce la coordinata y del punto di interesse.
	 * 
	 * @return		Restituisce il valore della coordinata y.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Vengono resettate le statistiche dei punti di interesse.
	 * 
	 */
	public void resetStatistiche() {
		eventiTotali = 0;
		eventiArrabbiato = 0;
		eventiFelice = 0;
		eventiSorpreso = 0;
		eventiTriste = 0;
		eventiNeutro = 0;
		
		eventiTotaliAttivi = 0;
		eventiArrabbiatoAttivi = 0;
		eventiFeliceAttivi = 0;
		eventiSorpresoAttivi = 0;
		eventiTristeAttivi = 0;
		eventiNeutroAttivi = 0;
	}
	
	/**
	 * Formatta le statistiche degli eventi attivi in una stringa leggibile dall'utente.
	 * 
	 * @return	Stringa contenente le statistiche formattate per la lettura umana
	 */
	public String StatisticheAttivi() {
		return aPercentualeAttivi(eventiArrabbiatoAttivi) + "% A, " + aPercentualeAttivi(eventiFeliceAttivi) + "% F, " + aPercentualeAttivi(eventiSorpresoAttivi)
		+ "% S, " + aPercentualeAttivi(eventiTristeAttivi) + "% T, " + aPercentualeAttivi(eventiNeutroAttivi) + "% N"; 
	}
	
	/**
	 * Formatta le statistiche totali in una stringa leggibile dall'utente.
	 * 
	 * @return	Stringa contenente le statistiche formattate per la lettura umana
	 */
	public String StatisticheTotali() {
		return aPercentualeTotale(eventiArrabbiato) + "% A, " + aPercentualeTotale(eventiFelice) + "% F, " + aPercentualeTotale(eventiSorpreso)
				+ "% S, " + aPercentualeTotale(eventiTriste) + "% T, " + aPercentualeTotale(eventiNeutro) + "% N"; 
	}
	
	/**
	 * Aggiorna le statistiche del punto di interesse usando i dati dell'evento <code>e</code>.
	 * 
	 * @param e		Evento aggiunto alle statistiche
	 */
	public void aggiungiEvento( Evento e ) {
		eventiTotali++;
		if( e.getAccesso() == StatoUtente.LOGIN ) {
			eventiTotaliAttivi++;
		}
		
		switch( e.getStatoEmotivo() ) {
		case NEUTRO: {
			eventiNeutro++;
			if ( e.getAccesso().equals( StatoUtente.LOGIN ) ) {
				eventiNeutroAttivi++;
			}
		}
			break;
		case FELICE: {
			eventiFelice++;
			if ( e.getAccesso().equals( StatoUtente.LOGIN ) ) {
				eventiFeliceAttivi++;
			}
		}
			break;
		case TRISTE: {
			eventiTriste++;
			if ( e.getAccesso().equals( StatoUtente.LOGIN ) ) {
				eventiTristeAttivi++;
			}
		}
			break;
		case ARRABBIATO: {
			eventiArrabbiato++;
			if ( e.getAccesso().equals( StatoUtente.LOGIN ) ) {
				eventiArrabbiatoAttivi++;
			}
		}
			break;
		case SORPRESO: {
			eventiSorpreso++;
			if ( e.getAccesso().equals( StatoUtente.LOGIN ) ) {
				eventiSorpresoAttivi++;
			}
		}
			break;
			
		default: System.out.println( "[AVVERTIMENTO] Non e' stato possibile riconoscere lo stato emotivo dell'evento: " + e.getId() );
		}
	}
	
	/**
	 * Calcola la distanza assoluta tra il punto di interesse e le coordinate passate in input.
	 * 
	 * @param x		Coordinata x
	 * @param y		Coordinata y
	 * @return		Restituisce la distanza assoluta
	 */
	public double calcolaDistanza( double x, double y ) {
		return Utilita.distanza( this.x, this.y, x, y );
	}
	
	/**
	 * Calcola la percentuale del valore delle emozioni in relazione agli eventi totali
	 * 
	 * @param valore	
	 * @return			percentuale
	 */
	private int aPercentualeTotale( int valore ) {
		if( eventiTotali != 0 ) {
		return (int) Math.round( ((100 * valore) / (double) eventiTotali) );
		} else {
			return 0;
		}
	}
	
	/**
	 * Calcola la percentuale del valore delle emozioni in relazione agli eventi attivi
	 * 
	 * @param valore	
	 * @return			percentuale
	 */
	private int aPercentualeAttivi( int valore ) {
		if( eventiTotaliAttivi != 0 ) {
			return (int) Math.round( ((100 * valore) / (double) eventiTotaliAttivi) );
		} else {
			return 0;
		}
	}
}
