import java.net.*;
import java.io.*;

public class Server {
	Tavolo tavolo;
	Server(){
		tavolo= new Tavolo();
	}
	public void exec() throws IOException {
		ServerSocket s = new ServerSocket(8999);
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepted connection");
				new SlaveThread(socket, tavolo).start();
			}
		} finally {
			s.close();
		}
	}
	public static void main(String[] args) throws IOException {
		new Server().exec();
	}
}


