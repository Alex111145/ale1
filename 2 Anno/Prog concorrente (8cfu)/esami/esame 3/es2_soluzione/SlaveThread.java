import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	CasellePostali laPosta;
	protected SlaveThread(Socket s, CasellePostali p) throws IOException  {
		socket = s;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		laPosta=p;
	}

	public void run() {
		String command;
		Messaggio msg;
		String clientName;
		long timeout;
		try {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				command = (String) in.readObject();
				if(command.equals("end")) {
					break;
				}
				if(command.equals("newClient")) {
					out.writeObject(laPosta.newClient());
				}
				if(command.equals("numClients")) {
					out.writeObject(laPosta.numClients());
				}
				if(command.equals("put")) {
					msg=(Messaggio)in.readObject();
					laPosta.put(msg);
				}
				if(command.equals("read")) {
					clientName=(String) in.readObject();
					timeout=(long) in.readObject();
					msg=laPosta.read(clientName, timeout);
					out.writeObject(msg);
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
