import java.io.*;
public class CopyChar {
  public static void main (String[] arg) throws IOException {
    String str = "";
    FileReader frd = new FileReader(arg[0]); 
    BufferedReader bfr = new BufferedReader(frd);
    FileWriter fwr = new FileWriter(arg[1]);
    PrintWriter bwr = new PrintWriter(fwr); // uguale a bufferreader ma con writer
    while ((str = bfr.readLine()) != null)
      bwr.println(str);
    bfr.close();
    frd.close();
    bwr.close();
    fwr.close();
  }
  //fa scrivere tutto su un file invece che su terminale
}

