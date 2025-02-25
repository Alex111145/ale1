package soluzione;

public class PuntoDiInteresse {
	private String nome;
	private double x = 0;
	private double y = 0;
	
	private double eventiTotali;
	private double eventiArrabbiato;
	private double eventiFelice;
	private double eventiSorpreso;
	private double eventiTriste;
	private double eventiNeutro;
	
	private double eventiTotaliAttivi;
	private double eventiArrabbiatoAttivi;
	private double eventiFeliceAttivi;
	private double eventiSorpresoAttivi;
	private double eventiTristeAttivi;
	private double eventiNeutroAttivi;
	
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
	
	public String getNome() {
		return nome;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
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
	
	public void stampaStatisticheAttivi() {
		//TODO
		//System.out.println( "- " + a + "% A, " + f + "% F, " + s + "% S, " + t + "% T, " + n + "% N" );
	}
	
	public void stampaStatisticheTotali() {
		//TODO
		//System.out.println( "- " + a + "% A, " + f + "% F, " + s + "% S, " + t + "% T, " + n + "% N" );
	}
	
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
}
