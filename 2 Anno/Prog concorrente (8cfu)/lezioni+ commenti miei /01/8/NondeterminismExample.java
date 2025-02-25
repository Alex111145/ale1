public class NondeterminismExample extends Thread{
	final static int numIterations=10;
	public void run() {
		for(int i=0; i<numIterations; i++) {
			System.out.println("Nuovo thread");
		}
	}
	public static void main(String args []) {
		NondeterminismExample t=new NondeterminismExample();
		t.start();
		for(int i=0; i<numIterations; i++) {
			System.out.println("Main");
		}
	}
}
// non so se prima viene eseguito 
//o start e poi il t o il t e poi lo start 
// inprevedibile perche concorrente 

/*nuovo thread
nuovo thread
Thread
thread


Thread
thread
nuovo thread
nuovo thread
*/

