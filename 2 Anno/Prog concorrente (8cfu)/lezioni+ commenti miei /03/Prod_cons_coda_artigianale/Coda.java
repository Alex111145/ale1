
public class Coda {
	final int BUFFERSIZE;
	private int numItems = 0;
	private int[] valori;
	private int first; // index of the first item to read
	private int last; // index of the most recently inserted item
	Coda(int size){
		BUFFERSIZE=size;
		first=0; last=0;
		valori=new int[BUFFERSIZE];
	}
	synchronized void printWithName(String s, int v) {
		System.out.println(Thread.currentThread().getName()+s+v+"["+numItems+"]");		
	}
	synchronized public int getItem(){
		int tmp;
		while (numItems==0){
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		printWithName(" letto ", tmp);
		notifyAll();
		return tmp;
	}
	synchronized public void setItem(int v) {
		while (numItems==BUFFERSIZE){
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		printWithName(" scritto ", v);
		notifyAll();
	}
	synchronized public int getCurrentSize(){
		return numItems;
	}
}

