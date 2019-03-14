
public class TestCompteur{
  public static void main(String[] args) {
    int max = Integer.parseInt(args[0]);
    for (int i=0; i<max ;i++ ) {
      Compteur compteur = new Compteur();
      compteur.start();
    }
  }
}
