import java.io.*;

public class DemoClass implements Serializable {
  private int _dat=3;
  private static int _sdat=0;
  boolean personalizedSerialization;
  DemoClass(boolean p){
	  personalizedSerialization=p;
  }
  public static void incSdat() {
	  _sdat++;
  }
  private void writeObject(ObjectOutputStream o) throws IOException {
    o.writeInt(_dat);
    o.writeBoolean(personalizedSerialization);
    if(personalizedSerialization) {
        o.writeInt(_sdat);
    }
  }
  private void readObject(ObjectInputStream i) throws IOException, ClassNotFoundException {
    _dat=i.readInt();
    personalizedSerialization=i.readBoolean();
    if(personalizedSerialization) {
        _sdat=i.readInt();
    }
  }
  public String toString(){
     return "DemoClass ["+ _sdat+"]"+_dat;
  }
}
