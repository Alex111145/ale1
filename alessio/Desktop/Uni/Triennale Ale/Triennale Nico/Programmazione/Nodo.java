public class Nodo {

    private Nodo prossimoNodo = null;
    private int valore = 0;

    public Nodo( int i ) {
        prossimoNodo = null;
        valore = i;
    }  

    public void setValore( int val ) {
        valore = val;
    }

    public int getValore() {
        return valore;
    }

    public Nodo getProssimoNodo() {
        return prossimoNodo;
    }

    public void setProssimoNodo( Nodo nodo ) {
        prossimoNodo = nodo;
    }
    
}