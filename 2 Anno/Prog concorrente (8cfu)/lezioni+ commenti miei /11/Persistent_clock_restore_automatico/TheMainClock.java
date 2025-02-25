import java.io.*;

public class TheMainClock {
	private void exec() throws IOException, ClassNotFoundException  {
		PersistentClock pc = new PersistentClock(1000);
		try { Thread.sleep(3000); } catch (InterruptedException e) { }
		FileOutputStream  fos = new FileOutputStream("pippo.ser");
		ObjectOutput os = new ObjectOutputStream(fos);
		os.writeObject(pc);
		os.flush();  
		os.close();
		System.out.println("Main: persistent clock saved");
		try { pc.join(); } catch (InterruptedException e) {	}
		System.out.println("Main: persistent clock terminated, restoring");
		ObjectInput is= new ObjectInputStream(new FileInputStream("pippo.ser"));
		pc = (PersistentClock) is.readObject();
		System.out.println("Main: persistent clock restored");
		is.close();
	}
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
		new TheMainClock().exec();
	}
}
