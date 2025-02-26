import prog.io.*;

public class Calcolatrice {

    static ConsoleInputManager in = new ConsoleInputManager();
    
    static float risultato = 0;
    static char soluzione = '*';
    static char selezione = '*';

    public static void intestazione() {
        System.out.println("BENVENUTO NELLA TUA CALCOLATRICE");
    }

    public static void Scrivimenu() {

        System.out.println("1 Somma");
        System.out.println("2 Sottrazione");
        System.out.println("3 Moltiplicazione");
        System.out.println("4 Divisione");
        System.out.println("5 Esci dal programma");
    }

    public static float Eseguiop(float a, float b){
        switch(selezione){
            case '1': return a + b;
            case '2': return a - b;
            case '3': return a * b;
            case '4': return a / b;
            case '5': System.exit(0);
            default: System.out.println("ERRORE!");
            selezione = '*';
            return -1;
        }
    }

    public static void main(String[] args){
        char selezione = '*';
            while(selezione != '5'){
                Scrivimenu();
                selezione = in.getChar();
                risultato =  Eseguiop(in.nextFloat(), in.nextFloat());
                    if(selezione != '*' && selezione != '5')
                        System.out.println("Il risultato e' " +risultato);

            }

    }

}