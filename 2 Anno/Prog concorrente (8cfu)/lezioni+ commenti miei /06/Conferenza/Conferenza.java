
public class Conferenza {
	    public static void main(String[] args)
	                            throws InterruptedException {
	      Broadcast<String> speechMessage=new Broadcast<String>();
	      System.out.println("La sala conferenze e` aperta...");
	      for(int i=0; i<10; i++) {
	        new Ascoltatore(speechMessage).start();
	      }
	      Thread.sleep(100);
	      System.out.println("Entra lo speaker...");
	     new Speaker(speechMessage).start();
	   }
}
