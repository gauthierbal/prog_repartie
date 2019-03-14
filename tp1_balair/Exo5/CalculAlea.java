import java.util.concurrent.ThreadLocalRandom;

public class CalculAlea extends Thread {
  // tableau à traiter
  private double[] t;
  // attribut qui indique sur quelle partie du tableau on travaille
  private int debut, fin;
  // constructeur
  public CalculAlea(double[] t, int debut, int fin){
    this.t = t; this.debut = debut; this.fin = fin;
  }
  // méthode run
  public void run(){
    for (int i=debut; i<fin; i++){
      t[i] = Math.sqrt(Math.pow(ThreadLocalRandom.current().nextDouble(0,1),2.0)+Math.pow(ThreadLocalRandom.current().nextDouble(0,1),2.0));
      //t[i] = ThreadLocalRandom.current().nextDouble(2);
    }
  }
}
