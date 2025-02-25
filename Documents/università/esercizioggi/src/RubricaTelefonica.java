import java.util.LinkedList;

public class RubricaTelefonica{

    private LinkedList<Contatto> contatti;

    public RubricaTelefonica(){
        this.contatti = new LinkedList<Contatto>();
    }

    //AGGIUNGI CONTATTO
    public void aggiungiContatto(String nome, String numero){
        Contatto nuovoContatto = new Contatto(nome, numero);
        contatti.add(nuovoContatto);
        System.out.println("contatto aggiunto con successo");
    }

    //ELIMINA CONTATTTO
    public void eliminaContatto(String nome){
        Contatto contattoDaEliminare = null;
        for (Contatto contatto : contatti)
            if (contatto.getNome().equalsIgnoreCase(nome)) {
                contattoDaEliminare = contatto;
                break;
            }
        if (contattoDaEliminare != null){
            contatti.remove(contattoDaEliminare);
            System.out.println("contatto eliminato con successo");
        } else {
            System.out.println("contatto non trovato");
        }
    }

    //CERCA CONTATTO
    public void cercaContatto(String nome){
        for(Contatto contatto : contatti) {
            if (contatto.getNome().equalsIgnoreCase(nome)) {
                System.out.println("numero di telefono di: " + nome + " é " + contatto.getNumero());
                return;
            }
        }
        System.out.println("contatto non trovato");
    }

    //VISUALIZZA CONTATTO
    public void visualizzaContatto(){
        if (contatti.isEmpty()){
            System.out.println("non sono presenti contatti");
        }else {
            System.out.println("rubrica telefonica:");
            for (Contatto contatto : contatti)
                System.out.println(contatti);
        }
    }
}
