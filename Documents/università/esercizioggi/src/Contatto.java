import java.util.LinkedList;

public class Contatto{

    private String numero;
    private String nome;

    public Contatto(String numero, String nome){
        this.nome = nome;
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }

    public String getNome(){
        return nome;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
}
