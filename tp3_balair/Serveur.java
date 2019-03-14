import java.net.*;
import java.io.*;


public class Serveur{

  public static void main(String[] args) {
    try{
      ServerSocket s = new ServerSocket(12345);
      Socket soc = s.accept();
      boolean fini = false;
      System.out.println("Lancement serveur");
      while(fini == false){
        //Lecture
        BufferedReader bf = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        String message = bf.readLine();
        System.out.println(message);
        //Pause
        Thread.sleep(2000);
        //Ecriture
        PrintWriter out = new PrintWriter(soc.getOutputStream());
        if (message.equals("fin")) {
          fini = true;
          out.println("Fin de la communication");
          out.flush();
        }else{
          out.println("Bonjour");
          out.flush();
        }
      }
      s.close();
    }catch(Exception e){
      System.out.println(e);
    }

  }
}
