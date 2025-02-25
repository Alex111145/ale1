import java.net.*;
import java.io.*;

public class Server {
	static final int numResources=3;
	ResourceManager repository;
	Server(){
		repository= new ResourceManager();
		for(int i=0; i<numResources; i++) {
			repository.put(new Resource(ResourceType.A));
			repository.put(new Resource(ResourceType.B));
		}
	}
	public void exec() throws IOException {
		ServerSocket s = new ServerSocket(8999);
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepted connection");
				new SlaveThread(socket, repository).start();
			}
		} finally {
			s.close();
		}
	}
	public static void main(String[] args) throws IOException {
		new Server().exec();
	}
}


