import java.io.*;
import java.net.*;

public class ClienteAstaThread extends Thread{
	private int maxTries = 3+(int)(Math.random()*4);
	private int numTries=0;
	private Offerta currentOffer;
	private final double myIncrease=1.06;
	private String myName;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public ClienteAstaThread(InetAddress addr, int port, String cliName) throws IOException {
		this.myName=cliName;
		currentOffer=new Offerta(0, myName);
		Socket s=new Socket(addr, port);
		this.out = new ObjectOutputStream(s.getOutputStream());
		this.in = new ObjectInputStream(s.getInputStream());
		start();
	}
	public void run(){
		Offerta newOff=null;
		while(numTries<maxTries){			
			try {
				out.writeObject("read");
				currentOffer=(Offerta)in.readObject();
				System.out.println(myName+" read offer of "+currentOffer.getAmount()+
						" from "+currentOffer.getWho());				
				if(!currentOffer.getWho().equals(myName)){	// rilancia
					out.writeObject("offer");
					newOff=new Offerta((int) (currentOffer.getAmount()*myIncrease), myName);
					System.out.println(myName+" rilancia con "+newOff);
					out.writeObject(newOff);			
					if((boolean) in.readObject()){
						numTries++;
						System.out.println(myName+" offer accepted :-)");
						Thread.sleep(1000);
					} else {
						System.out.println(myName+" offer declined :-(");				
						Thread.sleep(1000);						
					}
				} else {
					numTries++;
					Thread.sleep(5000);
				}
			} catch (IOException | InterruptedException | ClassNotFoundException e1) { break; }
		}  // fine ciclo
		try {
			out.writeObject("END");
		} catch (IOException e) { }
	}
}
