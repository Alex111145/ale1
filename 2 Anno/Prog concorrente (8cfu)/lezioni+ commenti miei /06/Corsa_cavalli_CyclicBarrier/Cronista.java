
public class Cronista implements Runnable {
	int numGiroCorrente;
	int numGiriDaFare;
	Cronista(int n){
		numGiroCorrente=1;
		numGiriDaFare=n;
	}
	public void run() {
		System.out.print("I cavalli partono per il giro "+numGiroCorrente);
		if(numGiroCorrente==numGiriDaFare) {
			System.out.println(", ultimo giro!");
		} else {
			System.out.println();
			numGiroCorrente++;			
		}
	}
}
