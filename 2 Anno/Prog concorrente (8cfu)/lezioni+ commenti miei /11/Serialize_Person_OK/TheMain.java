import java.io.*;

public class TheMain {
	String fileName;
	ObjectOutputStream output=null;
	ObjectInputStream ois = null;
	TheMain(String fn) throws FileNotFoundException, IOException{
		fileName=fn;
		output=new ObjectOutputStream(new FileOutputStream(fileName));
		ois = new ObjectInputStream(new FileInputStream(fileName));
	}
	void exec() {
		Employee empl=new Employee("Rossi", 100, 3000);
		empl.display();
		try {
			output.writeObject(empl);
			output.close();
		} catch (IOException e) {
			System.err.println("Problems serializing");
			return;
		}
		System.out.println("Serializzazione completata.");
		Employee newEmpl=null;
		try {
			newEmpl = (Employee) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Problems de-serializing");
			return;
		}
		newEmpl.display();
	}

	public static void main(String[] args) {
		if(args.length==1) {
			TheMain tm=null;
			try {
				tm = new TheMain(args[0]);
			} catch (IOException e) {
				System.err.println("Initialization failure");
				System.exit(0);
			}
			tm.exec();
		} else {
			System.out.println("filename missing");
		}
	}
}


