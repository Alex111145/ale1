import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Client extends Thread {
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	private int id;
	private static int counter = 0;      // client totali
	private static int threadcount = 0;  // client correnti
	public static int threadCount() {
		return threadcount;
	}
	public static int threadTotalCount() {
		return counter;
	}
	static synchronized void addToThreadCount(int d) {
		threadcount+=d;
	}
	Client(InetAddress addr) {
		synchronized(Client.class) { id = counter++; }
		System.out.println("Making client " + id);
		addToThreadCount(1);
		try {
			socket = new Socket(addr, MultiServer.PORT);
		} catch (IOException e){System.err.println("Socket failed");}
		try {
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			start();
		} catch (IOException e) {
			System.err.println("Client thread "+id+" failure");
			try {
				socket.close();
			} catch (IOException e2) {
				System.err.println("Socket not closed");
			}
		}
	}
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				out.println("Client " + id + ": " + i);
				try { Thread.sleep(
						ThreadLocalRandom.current().nextInt(200,800));
				} catch (InterruptedException e) {  }
				String str = in.readLine();
				System.out.println(str);
			}
			out.println("END");
		} catch (IOException e) {
			System.err.println("IO Exception");
		} finally { // Always close it:
			try {  socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
			addToThreadCount(-1);
		}
	} 
}
