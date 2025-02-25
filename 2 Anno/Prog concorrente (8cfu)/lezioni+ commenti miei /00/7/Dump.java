import java.io.*;

public class Dump {
	// converte intero in stringa di 4 caratteri
	String intToFilledString(int x) {
		String str = Integer.toString(x);
		while (str.length() < 4) {
			str = " " + str;
		}
		return str;
	}
	// converte intero in carattere rappresentativo (simbolo)
	char intToSymbol(int c) {
		if (c < 31)
			return '.'; // i car. di controllo diventano un punto
		else if (c < 128)
			return (char)c; // caratteri ASCII
		else
			return '*';     // caratteri > 127 diventano *
		
	}
	public void exec(String fileName, String numChar) throws IOException {
		FileInputStream in = new FileInputStream(fileName);
		int n = Integer.parseInt(numChar); // max num caratteri da leggere e visualizzare
		int c = 0; // carattere letto
		int i = 0; // contatore caratteri letti
		String str = "   0";
		System.out.print(str); // scrive nmumero riga 0
		String car = "    ";

		while (((c = in.read()) != -1) && (i < n)) {
			str = intToFilledString(c);
			System.out.print(str); // NON va a capo
			i++;
			car+=intToSymbol(c);
			if (i % 10 == 0) {    // ogni 10 caratteri un fineriga
				System.out.println(car);
				car = "    ";
				str = intToFilledString(i);
				System.out.print(str);
			}
		}
		in.close();
	}

	public static void main (String[] arg) throws IOException {
		Dump d=new Dump();
		d.exec(arg[0], arg[1]);
	}
}


