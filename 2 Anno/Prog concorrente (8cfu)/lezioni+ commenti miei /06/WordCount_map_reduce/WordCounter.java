import java.util.concurrent.*;
import java.io.*;

public class WordCounter implements Runnable {
	CyclicBarrier cyclicBarrier;
	int wordCount=0;
	int startFile, endFile;
	WordCounter(CyclicBarrier c, int startFile, int endFile) {
		cyclicBarrier=c;
		this.startFile=startFile;
		this.endFile=endFile;
	}
	private int lineCount(BufferedReader br) {
		String line;
		int lineWordCount=0;
		try {
			while((line=br.readLine())!=null) {
				String[] wordArray=line.split("\\ s+");
				lineWordCount+=wordArray.length;
			}
		} catch (IOException e) { return 0; }
		return lineWordCount;
	}
	public void run() {
		BufferedReader br = null;
		System.out.println("Counter started for "+startFile+" to "+endFile);
		try {
			for(int i=startFile; i<endFile; i++) {
				br=null;
				String fileName="file_" + i + ".txt";
				System.out.println("leggo "+fileName);
				br=new BufferedReader(new FileReader("/home/gigi/Documents/Didattica/Prog_CD/Borse/"+fileName));
				wordCount+=lineCount(br);
			}
			System.out.println("GOING TO WAIT");
			cyclicBarrier.await();
		} catch(Exception exc) {
			System.out.println(exc);
		}
		try { br.close(); } catch (IOException e) {}
	}
}


