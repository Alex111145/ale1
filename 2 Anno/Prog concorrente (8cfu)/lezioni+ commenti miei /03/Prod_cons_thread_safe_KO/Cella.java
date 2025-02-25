
public class Cella {
	int valore ;

	public synchronized int getValore() {
		System.out.println("Viene letto "+valore);
		return valore;
	}

	public synchronized void setValore(int valore) {
		System.out.println("Viene scritto "+valore);
		this.valore = valore;
	}
}
