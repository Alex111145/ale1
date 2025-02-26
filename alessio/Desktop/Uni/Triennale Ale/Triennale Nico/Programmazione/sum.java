import prog.io.*;

public class sum{
	public static Integer sumMultipli(int[] a, int m){
	
		if(a == null)
			return null;
	
		Integer somma = 0;
		for(int i=m; i<a.length;i+=m)
			somma += a[i];
		
		return somma;
		
	}
	
	public static void main(String[] args){
		ConsoleInputManager in = new ConsoleInputManager();
		ConsoleOutputManager out = new ConsoleOutputManager();
		
	}
}