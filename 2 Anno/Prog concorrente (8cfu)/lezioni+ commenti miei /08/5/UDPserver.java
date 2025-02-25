import java.io.IOException;
import java.net.*;

public class UDPserver {
	DatagramSocket serverSocket;
	byte[] receiveData;
	byte[] sendData;
	DatagramPacket receivePacket;
	UDPserver() throws SocketException{
		serverSocket = new DatagramSocket(9876);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
	}
	private void exec() throws IOException {
		while (true) {
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress,port);
			serverSocket.send(new DatagramPacket(sendData,sendData.length,IPAddress,port));
		}
	}
	public static void main(String args[]) {
		try {
			UDPserver srv = new UDPserver();
			srv.exec();
		} catch (IOException e) {
			System.out.println("Server failed");
		}
	}
}
