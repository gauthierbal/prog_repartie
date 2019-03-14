import java.lang.Math;

public class MonThread extends Thread{
  int compteur;

  public void run(){
      System.out.println("Nom du Thread : "+this.getName()+"\nValeur du compteur : "+compteur);
      int rand = (int)(Math.random()*1000+1);
      try{
        sleep(rand);
      }catch(Exception e){
        System.out.println(e);
      }
      System.out.println("Fin du Thread "+this.getName());
  }

}
