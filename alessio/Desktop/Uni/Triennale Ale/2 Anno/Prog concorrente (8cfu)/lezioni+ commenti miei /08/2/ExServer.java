import java.io.*;
import java.net.*;

public class ExServer {
	public static final int PORT=8080; // identifico la porta
	void exec() throws IOException { // scrivo il metodo esegui
		ServerSocket s = new ServerSocket(PORT); // creo il socket di ricezione
		System.out.println("Server started: "+s); // controllo se parte
		try {
			Socket mySocket=s.accept(); // accetto la connessione da parte del ricettore
			try {
				System.out.println("Server: connection accepted: "+mySocket);
				BufferedReader in=
						new BufferedReader(new InputStreamReader( // creazione di un buffer per la scrittura
								mySocket.getInputStream())); // invio info
				while(true) {
					String str=in.readLine(); // fino a che il buffer è pieno allora manda
					if(str.equals("END")) break;
					System.out.println("Server received:"+str); // controllo cosa ho ricevuto 
				}
			}
			finally { // comunque sia chiudi il socket di ricevimento
				System.out.println("Server: closing..."); // chiudi il server
				mySocket.close(); // chiusura del server ricettore
			}
		} finally { // comunque sia chiudi il socket
			s.close(); // chiusura socket invio
		}
	}
	public static void main(String[] args) throws IOException {
		new ExServer().exec();
	}
}