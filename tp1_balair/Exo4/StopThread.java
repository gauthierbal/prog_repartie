
public class StopThread extends Thread{
  private static int compteur;
  private static long t3;

  @Override
  public void run(){
    long t1 = 0;
    while(!isInterrupted()){
      try{
        t1 = System.nanoTime();
        sleep(1000);
        long t4 = (System.nanoTime()-t1) /1000000;
        System.out.println("Boucle de : "+t4+"ms");
        compteur++;
      }catch(InterruptedException e){
        long t2 = System.nanoTime();
        this.t3 = (t2-t1)/1000000;
        Thread.currentThread().interrupt();
      }
    }
    System.out.println("Temps passé: " + compteur+" sec \nTemps de la derniere boucle: "+t3+"ms");
  }
}
