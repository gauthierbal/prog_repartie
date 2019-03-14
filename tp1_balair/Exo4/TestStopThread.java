import java.util.Scanner;

public class TestStopThread{
  public static void main(String[] args) {
    StopThread stop = new StopThread();
    stop.start();
    System.out.println("Saisie : ");
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    sc.close();
    if (str != null) {
      stop.interrupt();
    }
  }
}
