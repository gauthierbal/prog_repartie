import java.net.*;
import java.io.*;

public class Service extends Thread{
  private Socket soc;

  Service(Socket s){
    try{
      soc = s;
    }catch(Exception e){
      System.out.println(e);
    }
  }

@Override
  public void run(){
    try{
      boolean fini = false;
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
      soc.close();

    }catch(Exception e){
      System.out.println("Erreur Service: "+e);
    }
  }
}
