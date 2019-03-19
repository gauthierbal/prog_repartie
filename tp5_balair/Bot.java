import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.entities.*;

public class Bot extends ListenerAdapter{
  String BOT_PREFIX = "/b1";
  Ping ping = new Ping();
  Dice dice = new Dice();
  Cat cat = new Cat();
  Meteo meteo = new Meteo();
  Blague blague = new Blague();

  public static void main(String[] args) throws
  LoginException{
    System.out.println("démarrage du bot");
    String token = args[0];
    JDA jda = new JDABuilder(token).build();
    try{
      jda.addEventListener(new Bot());
    }catch(Exception e){
      System.err.println(e);
    }

  }


  @Override
  public void onMessageReceived(MessageReceivedEvent event){
    Message message = event.getMessage();
    String msg = message.getContentDisplay();
    String[] parts = msg.split(" ");
    MessageChannel channel = event.getChannel();
    if (parts[0].equals(BOT_PREFIX)) {
      if (parts[1].equals("ping")) {
        channel.sendMessage(ping.reponsePing()).queue();
      }
      if (parts[1].equals("dice")) {
        if (parts.length == 2) {
          channel.sendMessage(dice.reponseDiceSix()).queue();
        }else if(parts.length == 3){
          int n = Integer.parseInt(parts[2]);
          channel.sendMessage(dice.reponseDice(n)).queue();
        }
      }
      if (parts[1].equals("cat")){
        if (parts.length == 3) {
          channel.sendMessage(cat.catReponseText(parts[2])).queue();
        }else{
          channel.sendMessage(cat.catReponse()).queue();
        }
      }
      if (parts[1].equals("meteo")) {
          channel.sendMessage(meteo.reponseMeteo()).queue();
      }
      if (parts[1].equals("blague")) {
          channel.sendMessage(blague.reponseBlague()).queue();
      }
    }
  }
}
