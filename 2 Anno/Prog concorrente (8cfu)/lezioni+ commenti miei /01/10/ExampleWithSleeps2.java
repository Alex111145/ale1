public class ExampleWithSleeps2 extends Thread{
	public ExampleWithSleeps2(String s) {
		super(s);
	}
	public void run() {
		int a=0;
		boolean interrupted=false;
		while(true) {
			if(a%2==0) {
				a++;
			} else {
				a--;
			}
			if (Thread.interrupted()){
				interrupted=true;
				System.err.println(getName() + ": interrotto");
				break;
			}
		}
		System.out.println("thread "+getName()+": finito.");
	}
}
