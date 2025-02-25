import java.util.Date;

public class Partita {
	// nome e data identificano univocamente una partita
	String nome; // ad es. "Milan-Inter"
	Date data;
	String risultato;  // null se non ancora disponibile
	String quotazione; // null se non ancora disponibile, altrimenti descrive la quotazione, ad es., "Milan vincente 0.33"
	
	Partita(String s, Date d){
		nome=s;
		data=d;
		risultato=null;
		quotazione=null;
	}
	public String toString() {
		String output=nome+"("+data+")";
		if(quotazione!=null) {
			output=output+" "+quotazione;
		}
		if(risultato!=null) {
			output=output+" "+risultato;
		}
		return output;
	}
	public String getNome() {
		return nome;
	}
	public Date getData() {
		return data;
	}
	public String getRisultato() {
		return risultato;
	}
	public void setRisultato(String risultato) {
		this.risultato = risultato;
	}
	public String getQuotazione() {
		return quotazione;
	}
	public void setQuotazione(String q) {
		this.quotazione = q;
	}
}
