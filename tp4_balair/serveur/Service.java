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
        //Ecriture
        PrintWriter out = new PrintWriter(soc.getOutputStream());
        if (message.equals("quit")) {
          fini = true;
          out.println("Fin de la communication");
          out.flush();
        }else{
          out.println("Message : "+message);
          out.flush();
        }
      }
      soc.close();

    }catch(Exception e){
      System.out.println("Erreur Service: "+e);
    }
  }
}
