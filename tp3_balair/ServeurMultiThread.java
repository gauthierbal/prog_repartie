import java.net.*;
import java.io.*;

public class ServeurMultiThread{

  public static void main(String[] args) {
    try{
      ServerSocket s = new ServerSocket(12345);
      boolean fini = false;
      System.out.println("Lancement du serveur");
      while(fini == false){
        Socket soc = s.accept();
        Service serv = new Service(soc);
        serv.start();
      }
    }catch(Exception e){
      System.out.println("Erreur ServeruMutliThread: "+e);
    }
  }
}
