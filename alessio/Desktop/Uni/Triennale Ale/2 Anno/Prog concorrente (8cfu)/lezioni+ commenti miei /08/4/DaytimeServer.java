import java.io.*;
import java.net.*;
import java.util.*;

public class DaytimeServer {
	public final static int DayTime_PORT= 1333;
	ServerSocket server;
	Socket connection = null;
	DaytimeServer() throws IOException{
		server = new ServerSocket(DayTime_PORT);
	}
	private void exec() {
		while (true) {
			try {
				connection = server.accept();
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString() +"\r\n");
				out.flush();
				connection.close();
			}
			catch (IOException ex) {}
			finally {
				try {
					if (connection != null) connection.close();
				} catch (IOException ex) {}
			}
		}
	}
	public static void main(String[] args) {
		DaytimeServer srv;
		try {
			srv = new DaytimeServer();
			srv.exec();
		} catch (IOException e) {
			System.out.println("Server start failed.");
		}
	}
}

