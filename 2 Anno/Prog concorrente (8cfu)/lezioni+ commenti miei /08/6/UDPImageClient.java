import java.io.*;
import java.net.*;

public class UDPImageClient {
	void exec(String fileName) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData;
		sendData = "START".getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);  // invia richiesta start
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		while (true) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);			
			String modifiedSentence = new String(receivePacket.getData());
			if (modifiedSentence.startsWith("END_FILE")) {
				System.out.println("RECEIVED FROM SERVER: " + modifiedSentence);
				fos.close();
				break;
			}
			fos.write(receivePacket.getData(), 0, receivePacket.getLength());
		}
		clientSocket.close();
		System.out.println("CLIENT: finished");

	}
	public static void main(String args[]) throws Exception {
		new UDPImageClient().exec(args[0]); // argomento e` il nome con cui salvare il file ricevuto
	}
}
