import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.time.*;

public class MonHorlogeBase extends JPanel implements Runnable{


  Image imgTmp;
  Graphics gTmp;
  Thread tr;

  public MonHorlogeBase(){
    tr = new Thread(this);
    tr.start();
  }


  /* Méthode paint qui dessine l'horloge */
  public void paint(Graphics gsp) {

    super.paint(gsp);

    setBackground(Color.white);

    int haut = getHeight();
    int larg = getWidth();

    imgTmp = createImage(larg,haut);
    gTmp = imgTmp.getGraphics();
    LocalDateTime time = LocalDateTime.now();
    DessinHorloge.dessineHorloge(gTmp, haut, larg, time.getHour(), time.getMinute()+((double)time.getSecond()/60), time.getSecond()+((double)time.getNano()/1_000_000_000));

    gsp.drawImage(imgTmp,0,0,this);
  }

  @Override
  public void run(){
    while(true){
      try{
        Thread.sleep(40);
        this.repaint();
      }catch(Exception e){
        System.out.println(e);
      }
    }

  }
  static public void main(String[] args){

    JFrame frame = new JFrame();
    frame.getContentPane().add(new MonHorlogeBase());

    frame.setBounds(100,100,800,600);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
