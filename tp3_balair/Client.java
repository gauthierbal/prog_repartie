import java.net.*;
import java.io.*;
import java.util.*;

public class Client{
  public static void main(String[] args) {
    String ip = args[0];
    int port = 12345;
    //String message = args[1];
    try{
      Socket s = new Socket(ip, port);
      PrintWriter out = new PrintWriter(s.getOutputStream());
      BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
      Scanner sc = new Scanner(System.in);
/*
      out.println(message);
      out.flush();
      String data = bf.readLine();
      System.out.println(data);
*/
      boolean fini = false;
      System.out.println("Lancement du client");
      while(fini == false){
        String mess = sc.nextLine();
        if (mess.equals("fin")) {
          out.println(mess);
          out.flush();
          String data2 = bf.readLine();
          System.out.println(data2);
          data2 = null;
          fini=true;
        }else{
          out.println(mess);
          out.flush();
          String data2 = bf.readLine();
          System.out.println(data2);
          data2 = null;
        }
      }
      s.close();
    }catch(Exception e){
      System.out.println("Erreur !");
    }
  }
}
