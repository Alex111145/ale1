package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Giocatore extends Thread {
    int miaId;
    Random rnd;

    public Giocatore(int id) {
        rnd=new Random();
        miaId=id;
    }

    public void run() {
        try{
            Registry reg = LocateRegistry.getRegistry(2000);
            InterfacciaGioco interfacciaGioco = (InterfacciaGioco) reg.lookup("server");
            while(true) {  // la partita non ha fine
                int n=-99;
                boolean approvata=false;
                System.out.println("Giocatore "+miaId+" aspetta turno");
                interfacciaGioco.aspettaTurno(miaId);  // chiamata sospensiva: si rientre dalla chiamata quando e` il proprio turno
                while(!approvata) {
                    n=rnd.nextInt(100);
                    Mossa laMossa=new Mossa(miaId, n);
                    System.out.println("Giocatore "+miaId+" fa mossa "+n);
                    interfacciaGioco.mossa(miaId, laMossa);
                    System.out.println("Giocatore "+miaId+" legge esito");
                    approvata=interfacciaGioco.letturaEsito(); // chiamata sospensiva: si rientre dalla chiamata quando l'arbitro ha valutato la mossa
                }
            }
        }catch (RemoteException | NotBoundException e) {   }
    }

    public static void main(String[] args) {
        new Giocatore(1).start();
        new Giocatore(2).start();
    }
}

