import prog.io.*;

public class Cifrario {

    public static void main(String[] args){
        ConsoleInputManager in = new ConsoleInputManager();
        
        System.out.println("***** CIFRARIO *****");
        String risposta = in.readLine("Vuoi CIFRARE o DECIFRARE il codice? ");
        risposta = risposta.toUpperCase();
        if (risposta.equals("CIFRARE")){
            char[] alfabeto = new char[26];
                alfabeto[0] = 'A';
                alfabeto[1] = 'B';
                alfabeto[2] = 'C';
                alfabeto[3] = 'D';
                alfabeto[4] = 'E';
                alfabeto[5] = 'F';
                alfabeto[6] = 'G';
                alfabeto[7] = 'H';
                alfabeto[8] = 'I';
                alfabeto[9] = 'J';
                alfabeto[10] = 'K';
                alfabeto[11] = 'L';
                alfabeto[12] = 'M';
                alfabeto[13] = 'N';
                alfabeto[14] = 'O';
                alfabeto[15] = 'P';
                alfabeto[16] = 'Q';
                alfabeto[17] = 'R';
                alfabeto[18] = 'S';
                alfabeto[19] = 'T';
                alfabeto[20] = 'U';
                alfabeto[21] = 'V';
                alfabeto[22] = 'W';
                alfabeto[23] = 'X';
                alfabeto[24] = 'Y';
                alfabeto[25] = 'Z'; 
            
            String stringadacifrare = in.readLine("Inserisci la frase da cifrare: ");
            stringadacifrare = stringadacifrare.toUpperCase();
            String[] parolafinale = new String[stringadacifrare.length()];
            for(int i=0; i<stringadacifrare.length(); i++){
                char lettere = ' ';
                lettere = stringadacifrare.charAt(i);
                                               
                System.out.println(parolafinale[i]);
            
            
            
            

            
        }
    
