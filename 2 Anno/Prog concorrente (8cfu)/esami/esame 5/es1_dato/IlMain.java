
public class IlMain {
	final int numScommettitori=3;
	final int numAllibratori=2;
	GestoreDati gestore;

	private void exec() {
		gestore=new GestoreDati();
		for(int i=0; i<numAllibratori; i++) {
			new Allibratore(i+1, gestore).start();
		}
		for(int i=0; i<numScommettitori; i++) {
			new Scommettitore(i+1, gestore).start();
		}
	}
	public static void main(String[] args) {
		new IlMain().exec();
	}

}
