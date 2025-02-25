import java.util.ArrayList;
import java.util.concurrent.*;

public class WordCountExample {
	private void exec() {
		ArrayList<WordCounter> counters=new ArrayList<WordCounter>();
		long t0=System.currentTimeMillis();
		BarrierReachedAction reduce=new BarrierReachedAction(t0);
		int parties=8;
		CyclicBarrier cyclicBarrier=new CyclicBarrier(parties, reduce);
		int incr=400/parties;
		for(int i=0; i<parties; i++) {
			counters.add(new WordCounter(cyclicBarrier, (i)*incr, (i+1)*incr));
		}
		reduce.setCounters(counters);
		System.out.println("Runnambles created and passed to the reducer");
		for(int i=0; i<parties; i++) {
			new Thread(counters.get(i)).start();
		}
	}
	public static void main(String args[]) {
		new WordCountExample().exec();
	}
}
