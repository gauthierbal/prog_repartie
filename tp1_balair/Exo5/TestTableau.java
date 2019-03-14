
public class TestTableau{
  private final static int MAX = 100_000_000;
  public static void main(String[] args) {

    int nbThread = Integer.parseInt(args[0]);

    double[] tab1 = new double[MAX];
    double[] tab2 = new double[MAX];

    long t1Deb = System.nanoTime();
    Tableau.alea2Thread(tab1);
    long t1Fin = System.nanoTime();
    long t1 = (t1Fin - t1Deb)/1000;

    long t2Deb = System.nanoTime();
    Tableau.alea(tab2, nbThread);
    long t2Fin = System.nanoTime();
    long t2 = (t2Fin - t2Deb)/1000;

    /*System.out.println("Tableau n°1");
    for (int i=0; i<tab1.length ;i++ ) {
      System.out.print(tab1[i] + " ");
    }
    System.out.println("\nTableau n°2");
    for (int j=0; j<tab1.length ;j++ ) {
      System.out.print(tab2[j] + " ");
    }*/
    System.out.println("\nTemps d'exec pour alea2Thread :"+t1+"µs \nTemps d'exec pour "+nbThread+" thread avec la methode alea : "+t2+"µs");
  }
}
