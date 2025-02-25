package RMI;

import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Client {
    String name ;
    Random rndRandom;
    Informazione lettura;

    public Client() {
        rndRandom = new Random();
        this.name="Client" + rndRandom.nextInt(100);
    }

    public void exec()  throws IOException,NotBoundException{
        Registry registry = LocateRegistry.getRegistry( 1099);
        InterfacciaDeposito interfacciaDeposito = (InterfacciaDeposito) registry.lookup("server");

        long lastTime=System.currentTimeMillis()-1000000;
        for(int j=0; j<10; j++) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(300));
            } catch (InterruptedException e) { }


            lettura = interfacciaDeposito.read(lastTime);
            System.out.println(name+" leggo "+lettura);
            lastTime=lettura.getInfoTime();
        }
    }

    public static void main(String[] args) throws NotBoundException{
        try {
            new Client().exec();
        } catch (NotBoundException | IOException e) {
            System.err.println("Client KO");
        }
    }
}
