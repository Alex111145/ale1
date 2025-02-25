package RMI;

import java.io.Serializable;

public class Mossa implements Serializable {
    private static final long serialVersionUID = 1L;
    int valore=-99;
    int idGiocatore;
    public Mossa(int id, int n) {
        valore=n;
        idGiocatore=id;
    }
    int getValore() {
        return valore;
    }
    int getIdGiocatore() {
        return idGiocatore;
    }
}