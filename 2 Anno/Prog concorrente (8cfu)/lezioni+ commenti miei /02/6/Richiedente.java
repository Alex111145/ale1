public class Richiedente extends Thread {
  private int numPosti;
  private InsiemePosti iPosti;
  public Richiedente(String nome, int n, InsiemePosti p){
    super(nome);
    this.numPosti=n;
    this.iPosti=p;
  }
  public void run(){
    System.out.println(getName()+" richiede "+numPosti);
    if(iPosti.assegnaPosti(getName(), numPosti)){
      System.out.println(getName()+" ottiene "+numPosti);
    } else {
      System.out.println(getName()+" NON ottiene "+
                         numPosti);
    }
  }
}