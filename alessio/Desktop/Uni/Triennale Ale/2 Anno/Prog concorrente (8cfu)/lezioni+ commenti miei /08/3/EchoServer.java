import java.io.*;
import java.net.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		new EchoServer().exec();
	}
	public static final int PORT=8080;
	private void exec() throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server started: "+s);
		try {
			Socket mySocket=s.accept();
			try {
				System.out.println("Server: connection accepted: "+mySocket);
				BufferedReader in=
						new BufferedReader(new InputStreamReader(
								mySocket.getInputStream()));
				PrintWriter out=new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(mySocket.getOutputStream())),
						true);
				while(true) {
					String str=in.readLine();
					if(str.equals("END")) break;
					System.out.println("Server echoing:"+str);
					out.println(str);
				}
			}
			finally {
				System.out.println("Seever closing...");
				mySocket.close();
			}
		} finally {
			s.close();
		}
	}
}