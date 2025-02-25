package soluzione;

//TODO Javadoc
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
	
	//TODO Javadoc
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
	
	//TODO Javadoc
	public String getNome() {
		return nome;
	}
	
	//TODO Javadoc
	public double getX() {
		return x;
	}
	
	//TODO Javadoc
	public double getY() {
		return y;
	}
	
	//TODO Javadoc
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
	
	//TODO Javadoc
	public String StatisticheAttivi() {
		return aPercentualeAttivi(eventiArrabbiatoAttivi) + "% A, " + aPercentualeAttivi(eventiFeliceAttivi) + "% F, " + aPercentualeAttivi(eventiSorpresoAttivi)
		+ "% S, " + aPercentualeAttivi(eventiTristeAttivi) + "% T, " + aPercentualeAttivi(eventiNeutroAttivi) + "% N"; 
	}
	
	//TODO Javadoc
	public String StatisticheTotali() {
		return aPercentualeTotale(eventiArrabbiato) + "% A, " + aPercentualeTotale(eventiFelice) + "% F, " + aPercentualeTotale(eventiSorpreso)
				+ "% S, " + aPercentualeTotale(eventiTriste) + "% T, " + aPercentualeTotale(eventiNeutro) + "% N"; 
	}
	
	//TODO Javadoc
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
	
	//TODO Javadoc
	public double calcolaDistanza( double x, double y ) {
		return Utilita.distanza( this.x, this.y, x, y );
	}
	
	//TODO Javadoc
	private int aPercentualeTotale( int valore ) {
		return (int) ((100 * valore) / eventiTotali);
	}
	
	//TODO Javadoc
	private int aPercentualeAttivi( int valore ) {
		return (int) ((100 * valore) / eventiTotaliAttivi);
	}
}
