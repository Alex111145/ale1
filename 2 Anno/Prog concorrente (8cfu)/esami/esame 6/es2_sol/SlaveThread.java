import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	Tavolo ilGioco;
	protected SlaveThread(Socket s, Tavolo tavolo) throws IOException  {
		socket = s;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		ilGioco=tavolo;
	}

	public void run() {
		String command;
		String nome;
		int stato, attesa;
		boolean esito;
		try {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				command = (String) in.readObject();
				if(command.equals("end")) {
					break;
				}
				if(command.equals("inizio")) {
					nome=(String)in.readObject();
					stato=(int)in.readObject();
					ilGioco.iniziaGioco(nome, stato);
				}
				if(command.equals("cambiaStato")) {
					nome=(String)in.readObject();
					stato=(int)in.readObject();
					ilGioco.cambiaStato(nome, stato);
				}
				if(command.equals("promozione")) {
					nome=(String)in.readObject();
					attesa=(int)in.readObject();
					esito = ilGioco.promozione(nome, attesa);
					out.writeObject(esito);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("IO Exception ");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}
}
