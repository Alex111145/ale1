import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

class Giocatore  {
	String mioNome;
	int mioStato;
	Random rnd;
	
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Giocatore(){
		long now=System.currentTimeMillis();
		mioNome="Giocatore_"+now;
		rnd=new Random();
	}

	int prossimoStato(int statoCorrente) {
		int statoProssimo=statoCorrente;
		while(statoProssimo==statoCorrente) {
			statoProssimo=1+rnd.nextInt(Tavolo.MAXstati);
		}
		return statoProssimo;
	}
	
	void dormitina(int a) {
		int t=200+new Random().nextInt(a);
		try {
			Thread.sleep((long) t);
		} catch (InterruptedException e) {		}
	}

	public void exec() {
		boolean esito=false;
		int attesa=250;
		try {
			InetAddress addr = InetAddress.getByName(null);
			Socket socket=new Socket(addr, 8999);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			mioStato=prossimoStato(-1); // stato iniziale
			System.out.println(mioNome+" inizio in stato "+mioStato);
			out.writeObject("inizio");
			out.writeObject(mioNome);
			out.writeObject(mioStato);			
			for(int j=0; j<10; j++) {
				dormitina(200);
				if(rnd.nextBoolean()) { // decide cosa fare
					// cambia stato
					mioStato=prossimoStato(mioStato);
					System.out.println(mioNome+" vado in stato "+mioStato);
					out.writeObject("cambiaStato");
					out.writeObject(mioNome);
					out.writeObject(mioStato);	
					dormitina(500);
				} else {
					// promozione
					System.out.println(mioNome+" provo promozione ");
					out.writeObject("promozione");
					out.writeObject(mioNome);
					out.writeObject(attesa);
					esito=(boolean) in.readObject();
					if(esito) {
						dormitina(500);
						System.out.println(mioNome+" promozione OK!");
					} else {
						System.out.println(mioNome+" promozione fallita");
					}
				}
			}
			out.writeObject("end");
			out.close();
			in.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[])  {
		new Giocatore().exec();
		System.out.println("Client: ohibo`, termina main");
	}
}
