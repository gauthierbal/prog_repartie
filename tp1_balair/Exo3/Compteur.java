
public class Compteur extends Thread{
  private final int MAX = 100;
  public static int compt;
  private static boolean isAlive = true;
  private static Object obj = new Object();

  public void incremente(){
    int compteurInt;
    synchronized(obj){
     compteurInt = compt;
      try{
        sleep(100);
      }catch(Exception e){
        System.err.println(e);
      }
      if (compteurInt<MAX) {
        compteurInt++;
      }
      compt = compteurInt;
    }
  }

  @Override
  public void run(){
    while(isAlive){
      incremente();
      synchronized(System.out){
        if (isAlive) {
          if (compt>= MAX) {
            System.out.println(getName()+" valeur = "+(compt));
            isAlive =false;
          }
        }else{
          System.out.println(getName()+" valeur = "+(compt));
        }
      }
    }
    System.out.println("*** Fin de "+getName()+" ***");
  }
}
