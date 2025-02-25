import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class WordCountClient {
	int numServers;
	String theArgs[];
	WordCountClient(String args[]){
		numServers=args.length/2;
		theArgs=args;
	}
	void exec() throws UnknownHostException, IOException{
		int port;
		String addr;
		ArrayList<WordCounter> counters=new ArrayList<WordCounter>();
		long t0=System.currentTimeMillis();
		BarrierReachedAction reducer=new BarrierReachedAction(t0);
		CyclicBarrier cyclicBarrier=new CyclicBarrier(numServers, reducer);
		int incr=400/numServers;
		for(int i=0; i<numServers; i++) {
			addr=theArgs[i*2];
			port=Integer.parseInt(theArgs[i*2+1]);
			counters.add(new WordCounter(addr, port, cyclicBarrier, (i)*incr, (i+1)*incr));
		}
		reducer.setCounters(counters);
		for(int i=0; i<numServers; i++) {
			counters.get(i).start();
		}
	}

	public static void main(String args[]) {
		WordCountClient wcc=new WordCountClient(args);
		try {
			wcc.exec();
		} catch (Exception e) {
			System.err.println("qualcosa storto ando`");
		}
	}
}