import net.dv8tion.jda.core.entities.*;
import java.net.*;
import java.io.*;
import java.util.stream.*;
import org.json.*;
import java.time.*;
import java.text.*;
import net.dv8tion.jda.core.EmbedBuilder;
import java.nio.charset.*;

public class Help{
  EmbedBuilder eb = new EmbedBuilder();
  MessageEmbed reponseHelp(){
    eb.setTitle("Liste des commandes de Bot_Balair", null);
    eb.addField("/b1 ping", "Renvoie pong à l'utilisateur", false);
    eb.addField("/b1 dice", "Renvoie un chiffre aléatoire compris entre 0 et 6 à l'utilisateur", false);
    eb.addField("/b1 dice <max>", "Renvoie un nombre aléatoire de 0 à max à l'utilisateur", false);
    eb.addField("/b1 cat", "Renvoie une image de chat aléatoire à l'utilisateur", false);
    eb.addField("/b1 cat <Texte>", "Renvoie une image de chat avec le texte pris en paramètre à l'utilisateur", false);
    eb.addField("/b1 meteo", "Renvoie la météo de Calais à l'utilisateur", false);
    eb.addField("/b1 blague", "Renvoie une blague à l'utilisateur", false);
    return eb.build();
  }
}
