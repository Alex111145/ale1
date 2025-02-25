package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaDeposito extends Remote {

    public void write(String message) throws RemoteException;

    public Informazione read(long lastTime) throws RemoteException;

}
