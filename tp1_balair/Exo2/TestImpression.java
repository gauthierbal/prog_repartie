
public class TestImpression{
  public static void main(String[] args) {
    Thread p1 = new Thread(new Impression("MonDoc1", 3));
    Thread p2 = new Thread(new Impression("Doc2", 4));
    p1.start();
    p2.start();
  }
}
