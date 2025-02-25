import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class User  {
	String mioNome;
	int mioStato;
	Random rnd;

	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Resource rA, rB;
	User(){
		long now=System.currentTimeMillis();
		mioNome="Utilizzatore_"+now;
		rnd=new Random();
		rA=rB=null;
	}

	void dormitina(int a) {
		int t=200+new Random().nextInt(a);
		try {
			Thread.sleep((long) t);
		} catch (InterruptedException e) {		}
	}

	public void acquisizioneA() throws IOException, ClassNotFoundException {
		System.out.println(mioNome+" acquisisco risorsa A");
		out.writeObject("getA");
		rA=(Resource)in.readObject();
		System.out.println(mioNome+" acquisito risorsa "+rA.getType()+rA.getNum());
	}
	public void acquisizioneB() throws IOException, ClassNotFoundException {
		System.out.println(mioNome+" acquisisco risorsa B");
		out.writeObject("getB");
		rB=(Resource)in.readObject();
		System.out.println(mioNome+" acquisito risorsa "+rB.getType()+rB.getNum());
	}
	public void rilascio(Resource r) throws IOException{
		System.out.println(mioNome+" rilascio risorsa "+r.getType()+r.getNum());
		out.writeObject("put");
		out.writeObject(r);
	}

	public void exec() throws IOException, ClassNotFoundException {
		InetAddress addr = InetAddress.getByName(null);
		Socket socket=new Socket(addr, 8999);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		try {
			for(int i=0; i<100; i++){
				System.out.println(mioNome+" iterazione "+i);
				rA=rB=null;
				if(ThreadLocalRandom.current().nextBoolean()) {
					// ho bisogno di una risorsa
					if(ThreadLocalRandom.current().nextBoolean()) {
						System.out.println(mioNome+" acquisisco risorsa A");
						acquisizioneA();
					} else {
						acquisizioneB();
					}
					// vediamo se c'e` bisogno di una seconda risorsa
					if(ThreadLocalRandom.current().nextBoolean()) {
						// ho bisogno della seconda risorsa
						// che deve essere di tipo diverso dalla prima
						if(rA!=null) {
							acquisizioneB();
						} else {
							rilascio(rB);
							rB=null;
							acquisizioneA();
							acquisizioneB();
						}
					} 
				} 
				// elaborazione
				System.out.println(mioNome+" inizio elab. ");
				Thread.sleep(500);
				System.out.println(mioNome+" fine elab. ");
				//rilascio eventuali risorse
				if(rA!=null) {
					rilascio(rA);
				}
				if(rB!=null) {
					rilascio(rB);
				}
				Thread.sleep(200);
			}
			out.writeObject("end"+mioNome);
			out.close();
			in.close();
			socket.close();
		} catch (InterruptedException e) { }
	}


	public static void main(String args[]) throws ClassNotFoundException, IOException  {
		new User().exec();
		System.out.println("Client: ohibo`, termina main");
	}
}
