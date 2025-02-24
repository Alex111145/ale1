

class ThreadUno extends Thread{   
  private String threadName; 
  ThreadUno(String name){  
    threadName = name; 
  } 
  public void run() {
      for(;;) { 
        System.out.println(threadName+" "+MainThread.x); 
            
      }
  }
} 
class MainThread {     
    protected static int x = 123; 
    public static void main(String[] args) { 
    ThreadUno t= new ThreadUno("EsempioThread"); 
      
    t.run(); 
      
    } 
} 

