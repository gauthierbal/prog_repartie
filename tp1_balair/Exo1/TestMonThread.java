import java.lang.*;

public class TestMonThread{
  public static void main(String[] args) {
    int max = Integer.parseInt(args[0]);
    MonThread tabTh[] = new MonThread[max];
    for (int i = 0;i<max; i++ ) {
      MonThread thread = new MonThread();
      tabTh[i]=thread;
      thread.compteur = i;
      thread.start();
    }

    for (int j=0;j<tabTh.length ;j++ ) {
      try{
        tabTh[j].join();
      }catch(Exception e){
        System.out.println(e);
      }
    }
    System.out.println("|Fin de tout les thread|");

  }
}
//tabTh[i]=thread;
