public class Lista {

    public static void main(String[] args){
        
        Nodo lista = null;
        Nodo corrente = null;

        for( int i = 0; i < 10; i++) {
            if( i == 0)
                corrente = lista = new Nodo( i );
            else {
                corrente.setProssimoNodo( new Nodo( i ));
                corrente = corrente.getProssimoNodo();
            }
        }

        corrente = lista;

        while( corrente != null ) {
            System.out.println( corrente.getValore() );
            corrente = corrente.getProssimoNodo();
        }
    }
}