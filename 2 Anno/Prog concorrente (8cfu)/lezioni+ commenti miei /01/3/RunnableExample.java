
public class RunnableExample implements Runnable{
	public void run() {
		System.out.println("Ciao!");
	}
	public static void main(String args []) {
		RunnableExample re=new RunnableExample(); // creazione del metodo runnable con cosa fa e dopo genero l oggetto
		Thread t1=new Thread(re);
	    t1.start();
		Thread t2=new Thread(re);
		t2.start();
//	    new Thread(new RunnableExample()).start();
	}
}
// eseguo il codice e vedo che i due thread stampano ciao in modo concorrente