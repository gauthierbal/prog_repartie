
public class Impression implements Runnable{
  String nom;
  int nbPage;

  public Impression(String name, int page){
    this.nom = name;
    this.nbPage = page;
  }

  @Override
  public void run(){
    for (int i=0; i<this.nbPage ;i++ ) {
      synchronized(System.out){
        System.out.println("Document : "+this.nom+" Page n°"+i);
      }

    }
  }

}
