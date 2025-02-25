import java.io.*;
public class FileWriterWriteIntExample {
  public static void main(String[] args) {
    FileWriter fileWriter = null; // istanzio uno scrittore di file
    try {
      fileWriter = new FileWriter("file.txt"); // imposto file di scrittura output
      fileWriter.write('C');
      fileWriter.write('i');
      fileWriter.write('a');
      fileWriter.write('o');
    } catch (Exception e) { e.printStackTrace(); }
      finally {
        try {
          if(fileWriter != null) {
            fileWriter.flush(); // forza la scrittura su file
            fileWriter.close();					
          }
        } catch (IOException e) { e.printStackTrace(); }
}}}

// fa scrivere un carattere alla volta, scomodo 