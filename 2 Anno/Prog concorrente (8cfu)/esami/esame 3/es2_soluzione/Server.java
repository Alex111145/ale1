import java.net.*;
import java.io.*;

public class Server {
	static int clientCount=1;
	CasellePostali laPosta;
	Server(){
		laPosta=new CasellePostali();	
	}
	public void exec() throws IOException {
		ServerSocket s = new ServerSocket(8999);
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepted connection");
				new SlaveThread(socket, laPosta).start();
			}
		} finally {
			s.close();
		}
	}
	public static void main(String[] args) throws IOException {
		new Server().exec();
	}
}


