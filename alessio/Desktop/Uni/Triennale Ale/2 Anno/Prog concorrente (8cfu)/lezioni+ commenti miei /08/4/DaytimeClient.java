import java.net.*;
import java.io.*;

public class DaytimeClient {
	InetAddress addr;
	Socket connection;
	BufferedReader in;
	DaytimeClient(String servName) throws IOException{
		addr = InetAddress.getByName(servName);
		System.out.println("addr = " + addr);
		connection = new Socket(addr, DaytimeServer.DayTime_PORT);
		System.out.println("socket = " + connection);
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	}
	private void exec() {
		String str;
		try {
			str = in.readLine();
			System.out.println(str);
		} catch (IOException e) {
			System.out.println("Daytime reading failed");
		}
		finally {
			try { connection.close();
			} catch (IOException e) {}
		}
	}
	public static void main(String[] args) {
		DaytimeClient cli;
		try {
			cli = new DaytimeClient("localhost");
			cli.exec();
		} catch (IOException e) {
			System.out.println("Client: connection failed");
		} 
	}
}