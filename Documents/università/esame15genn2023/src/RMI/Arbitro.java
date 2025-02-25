package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Arbitro extends Thread {
    Arbitro(){
    }

    public void run() {
        try {
            Registry reg = LocateRegistry.getRegistry(2000);
            InterfacciaGioco interfacciaGioco = (InterfacciaGioco) reg.lookup("server");
            while(true) {
                System.out.println("concorrente.Arbitro aspetta");
                interfacciaGioco.attesaMossa(); // chiamata sospensiva: si rientra dalla chiamata quando c'e` una mossa da valutare
                System.out.println("concorrente.Arbitro legge mossa corrente");
                Mossa m = interfacciaGioco.letturaMossaCorrente();
                boolean mossaOK=m.getValore()<60;
                System.out.println("concorrente.Arbitro decide "+(mossaOK?" OK":"KO"));
                interfacciaGioco.approva(mossaOK);
            }
        } catch (RemoteException | NotBoundException e) {   }
    }
    public static void main(String args[]) {
        new Arbitro().start();
    }
}
