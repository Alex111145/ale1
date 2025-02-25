import java.util.concurrent.*;
public class User extends Thread {
	ResourceManager repository;
	Resource rA=null, rB=null;
	String mioNome;
	public User (ResourceManager r) {
		repository=r;
	}
	void acquisizioneA() {
		System.out.println(mioNome+" acquisisco risorsa A ");
		rA=repository.getA();
		System.out.println(mioNome+" acquisito risorsa "+rA.getType()+rA.getNum());
	}
	void acquisizioneB() {
		System.out.println(mioNome+" acquisisco risorsa B ");
		rB=repository.getB();
		System.out.println(mioNome+" acquisito risorsa "+rB.getType()+rB.getNum());
	}
	void rilascio(Resource r) {
		System.out.println(mioNome+" rilascio risorsa "+r.getType()+r.getNum());
		repository.put(r);
	}
	public void run() {
		mioNome=getName();
		try {
			//			for(int i=0; i<10; i++){
			for(;;){
				if(ThreadLocalRandom.current().nextBoolean()) {
					// ho bisogno di una risorsa
					if(ThreadLocalRandom.current().nextBoolean()) {
						acquisizioneA();
					} else {
						acquisizioneB();
					}
					Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
					// vediamo se c'e` bisogno di una seconda risorsa
					if(ThreadLocalRandom.current().nextBoolean()) {
						// ho bisogno della seconda risorsa
						// che deve essere di tipo diverso dalla prima
						if(rA!=null) {
							acquisizioneB();
						} else {
							// non posso acquisire A dopo B
							// rilascio B
							System.out.println(mioNome+" rilascio risorsa B ");
							repository.put(rB);
							// acquisico A
							acquisizioneA();
							// acquisisco B
							acquisizioneB();
						}
					}
				}
				// elaborazione
				Thread.sleep(ThreadLocalRandom.current().nextInt(200, 600));
				//rilascio eventuali risorse
				if(rA!=null) {
					rilascio(rA);
				}
				if(rB!=null) {
					rilascio(rB);
				}
				rA=rB=null;

			}
			//			System.out.println(mioNome+" finished");
		} catch (InterruptedException e) { }
	}
}
