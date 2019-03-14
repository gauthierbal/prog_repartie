
public class Tableau{
  CalculAlea tabTh[];
  public static void alea2Thread(double[] tab){
    CalculAlea c1 = new CalculAlea(tab, 0, tab.length/2);
    CalculAlea c2 = new CalculAlea(tab, tab.length/2, tab.length);
    c1.start();
    c2.start();
    try{
      c1.join();
      c2.join();
    }catch(Exception e){
      System.out.println(e);
    }
  }

  public static void alea(double[] tab, int nbThread){
        int debut = 0;
        int taillePortion = tab.length/nbThread;
        CalculAlea stockThread[]= new CalculAlea[nbThread];
        for (int i = 0; i <nbThread; i++) {
            if (i==nbThread){
                CalculAlea c = new CalculAlea(tab, debut, tab.length);
                stockThread[i]=c;
                c.start();
            }
            else{
                CalculAlea c = new CalculAlea(tab, debut, debut + taillePortion);
                debut = debut + taillePortion;
                stockThread[i]=c;
                c.start();
            }
        }
        for(int j=0;j<nbThread;j++){
            try{
                stockThread[j].join();
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
