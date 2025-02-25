import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	ResourceManager repository;
	protected SlaveThread(Socket s, ResourceManager r) throws IOException  {
		socket = s;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		repository=r;
	}

	private void exec(String command) {
		Resource r;
		System.out.println("Server thread: eseguo "+command);
		try {
			if(command.equals("getA")) {
				out.writeObject(repository.getA());
			}
			if(command.equals("getB")) {
				out.writeObject(repository.getB());
			}
			if(command.equals("put")) {
				r=(Resource)in.readObject();
				repository.put(r);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String command;
		try {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				command = (String) in.readObject();
				if(command.equals("end")) {
					break;
				} else {
					exec(command);
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
