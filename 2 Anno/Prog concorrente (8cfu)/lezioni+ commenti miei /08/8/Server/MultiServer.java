import java.io.*;
import java.net.*;

public class MultiServer {
	static final int PORT = 8080;
	ServerSocket theServerSocket;
	MultiServer() throws IOException{
		theServerSocket = new ServerSocket(PORT);
		System.out.println("Server Started");
	}
	private void exec() throws IOException {
		try {
			while (true) {
				Socket socket = theServerSocket.accept();
				try {
					new ServerSlave(socket);
				} catch (IOException e) {
					// If it fails, close the socket,
					// otherwise the thread will close it:
					socket.close();
				}
			}
		} finally {
			theServerSocket.close();
		}
	}
	public static void main(String[] args) {
		try {
			MultiServer multiSrv = new MultiServer();
			multiSrv.exec();
		} catch (IOException e) {
			System.out.println("Server failed");
		}

	}
}