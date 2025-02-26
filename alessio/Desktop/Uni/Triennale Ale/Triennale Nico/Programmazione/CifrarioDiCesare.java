import java.util.*;

public class CifrarioDiCesare {

    static int indice;
    static char z;
    static String parolacifrata = "";
    static String paroladecifrata = "";
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String alfabeto = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("***** CIFRARIO *****");
        System.out.println("Si vuole CIFRARE o DECIFRARE? ");
        String risposta = in.nextLine();
            risposta = risposta.toUpperCase();
            if(risposta.equals("CIFRARE")){
                System.out.println("Inserisci la stringa da cifrare: ");
                String cifrare = in.nextLine();
                cifrare = cifrare.toUpperCase();
                for(int i=0; i<cifrare.length(); i++){
                    z = cifrare.charAt(i);
                    indice = alfabeto.indexOf(z) + 3;
                    if(indice > 21){
                        z = alfabeto.charAt(indice - 21);
                        parolacifrata = parolacifrata + z;
                    }else{
                        z = alfabeto.charAt(indice);
                        parolacifrata = parolacifrata + z;
                    }
                }
                System.out.println("La frase cifrata e': " + parolacifrata);
            }else{
                if(risposta.equals("DECIFRARE")){
                    System.out.println("Inserisci la stringa da decifrare: ");
                    String decifrare = in.nextLine();
                    decifrare = decifrare.toUpperCase();
                    for(int j=0; j<decifrare.length(); j++){
                        z = decifrare.charAt(j);
                        indice = alfabeto.indexOf(z) -3;
                        if(indice < 21){
                            z = alfabeto.charAt(indice + 21);
                            paroladecifrata = paroladecifrata + z;
                        }else{
                            z= alfabeto.charAt(indice);
                            paroladecifrata = paroladecifrata + z;
                        }
                        System.out.println("La frase decifrata e': " + paroladecifrata);
                    }
                }
            }
    }
}