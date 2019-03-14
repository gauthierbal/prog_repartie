
package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import java.io.*;
import java.net.*;

/**
*
* @author samuel
*/
public class ClientMessagerieV0 extends JFrame implements Runnable{

  public static final int PORT = 60_001;
  Socket s;
  public String hote;

  PrintWriter canalEcriture;
  BufferedReader canalLecture;

  JTextField entree;
  JTextArea visu;
  JButton envoi, connect;
  JPanel boutons;
  String nom;

  private static final long serialVersionUID = 42L;

  public ClientMessagerieV0() {

    super("messagerie");
    this.nom = nom;

    // construction de l'interface graphique
    entree = new JTextField(20);
    visu = new JTextArea();
    visu.setEditable(false);

    // on a ajoute le texte area dans un JScrollPane
    JScrollPane scroll = new JScrollPane(visu);
    // on souhaite que le scrollbar suive les ajouts
    DefaultCaret caret = (DefaultCaret) visu.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    this.setLayout(new BorderLayout());
    this.add("Center", visu);

    entree.setText("Saisissez ici l'adresse du serveur");


    boutons = new JPanel();

    envoi = new JButton("envoi");
    connect = new JButton("connexion");

    boutons.add(entree);
    boutons.add(envoi);
    boutons.add(connect);

    this.add("South", boutons);

    this.getContentPane().setPreferredSize(new Dimension(500, 500));
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    if (connect.getText().equals("connexion")) {
      envoi.setEnabled(false);
    }



    connect.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        // à faire
        hote = entree.getText();
        if (connect.getText().equals("connexion")) {
          try{
            s = new Socket(hote, PORT);
            canalEcriture = new PrintWriter(s.getOutputStream());
            canalLecture = new BufferedReader(new InputStreamReader(s.getInputStream()));
            visu.setText("Succès de connexion \n");
            System.out.println("Connecté");
            connect.setText("déconnexion");
            envoi.setEnabled(true);
            entree.setText("");
            new Thread(ClientMessagerieV0.this).start();
          }catch(Exception error){
            visu.append("Echec de la connexion \n");
            visu.append("Erreur connexion: "+ error +"\n");
          }
        }else{
          try{
            canalLecture.close();
            canalEcriture.close();
            s.close();
            Thread.currentThread().interrupt();
            System.out.println("Déconnecté");
            connect.setText("connexion");
            envoi.setEnabled(false);
            visu.setText("Déconnexion");
          }catch(Exception error){
            visu.append("Erreur disconnect: "+ error +"\n");
          }
        }
      }
    });


    envoi.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        // a faire aussi
        try{
          System.out.println("Envoi");
          String mess = entree.getText();
          canalEcriture.println(mess);
          canalEcriture.flush();
          entree.setText("");

        }catch(Exception err){
          visu.append("Erreur envoi: "+ err +"\n");
        }
      }
    });

    // quand on édite le champs texte, touche Entrer = clic sur le bouton envoi
    entree.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (connect.getText().equals("connexion")) {
          if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            connect.doClick();
          }
        }else{
          if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            envoi.doClick();
          }
        }

      }
    });
  }

  /**
  * @param args the command line arguments
  */
  public static void main(String[] args) {
    JFrame f= new ClientMessagerieV0();
  }

  @Override
  public void run(){
    boolean fini = false;
    while(fini == false){
      try{
        String lecture = canalLecture.readLine();
        visu.append(lecture + "\n");
      }catch(Exception error){
        visu.append("Erreur run: "+ error +"\n");
      }
    }

  }
}
