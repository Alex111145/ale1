import prog.io.*;

public class BattagliaNavale {
    public static int Base = 10;
    public static int Altezza = 10; 
    public static boolean valore = true;

    public static void main(String[] args) {
        ConsoleInputManager in = new ConsoleInputManager();
        char[][] CampoA = new char[Altezza][Base];

        for( int i = 0; i < Base; i++ ) {
            for( int j = 0; j < Altezza; j++ ) {
                CampoA[i][j] = '~';
            }

        }
      
            while (valore == true){ 
                
                //aggiungere il ciclo for per incrementare %
                char navegrande = '%';
                int a = in.readInt("Scegli la riga: ");
                int b = in.readInt("Scegli la colonna: ");
                String direzione = in.readLine("Scegli la direzione: N/S/E/W: ");
                direzione = direzione.toUpperCase();
                CampoA[a][b] = navegrande;
                    if(direzione.equals("N"))
                        CampoA[a-3][b] = navegrande;
                    else if(direzione.equals("S"))
                            CampoA[a+3][b] = navegrande;
                        else if(direzione.equals("E"))
                            CampoA[a][b+3] = navegrande;
                            else if(direzione.equals("W"))
                                CampoA[a][b-3] = navegrande;
                
                for( int i = 0; i < Base; i++ )
                    System.out.println(CampoA[i]);
                
                String fine = in.readLine("Vuoi continuare? Si/No: ");
                if (fine.equals("No") || fine.equals("no"))
                    valore = false;
                    
            }
    }
}