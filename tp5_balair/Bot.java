import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import java.util.List;
import java.util.Random;

import java.net.*;
import java.io.*;
import java.util.stream.*;

import org.json.*;
import java.time.*;
import java.text.*;

import net.dv8tion.jda.core.EmbedBuilder;
import java.awt.Color;

public class Bot extends ListenerAdapter{
  String BOT_PREFIX = "/b1";

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
        channel.sendMessage("pong").queue();
      }
      if (parts[1].equals("dice")) {
        int max;
        if (parts.length < 2) {
          max = Integer.parseInt(parts[2]);
        }else{
          max = 6;
        }
        int rand = (int)(Math.random() * (max));
        channel.sendMessage("Chiffre random : " + rand).queue();
      }
      if (parts[1].equals("cat")){
        if (parts.length < 2) {
          channel.sendMessage("https://cataas.com/cat").queue();
        }else{
          channel.sendMessage("https://cataas.com/cat/cute/says/"+parts[2]).queue();
        }
      }
      if (parts[1].equals("meteo")) {
        String result= "" ;
        String APIKey = "2b689cc9a8af023d443fd8164d5e8ba3";
        String serv = "http://api.openweathermap.org/data/2.5/weather";
        String param = "q=Calais&fr&appid=";
        result = serv+"?"+param+APIKey;
        try{
          URL url = new URL(serv+"?"+param+APIKey);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection ();
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Accept","application/json");
          if(conn.getResponseCode()!=200){
            System.out.println("Failed:HTTPerrorcode:"+conn.getResponseCode());
          }
          BufferedReader br=new BufferedReader(new InputStreamReader((conn.getInputStream())));
          result=br.lines().collect(Collectors.joining());
          conn.disconnect();
          JSONObject jsMeteo = new JSONObject(result);
          String ville = jsMeteo.getString("name");

          NumberFormat nf = new DecimalFormat("00");
          DecimalFormat df = new DecimalFormat(".##");
          double temp = jsMeteo.getJSONObject("main").getInt("temp") - 273.15;

          long leverTime = jsMeteo.getJSONObject("sys").getInt("sunset");
          LocalDateTime timeLever = LocalDateTime.ofEpochSecond(leverTime, 0, ZoneOffset.of("+1"));
          int leverHour = timeLever.getHour();
          int leverMinute = timeLever.getMinute();
          String lever = nf.format(leverHour)+":"+nf.format(leverMinute);

          long coucherTime = jsMeteo.getJSONObject("sys").getInt("sunrise");
          LocalDateTime timeCoucher = LocalDateTime.ofEpochSecond(coucherTime, 0, ZoneOffset.of("+1"));
          int coucherHour = timeCoucher.getHour();
          int coucherMinutes = timeCoucher.getMinute();
          String coucher = nf.format(coucherHour)+":"+nf.format(coucherMinutes);

          String villeString = " "+ville;
          String tempString = " "+ df.format(temp)+"°C";
          String leverString = " "+lever;
          String coucherString = " "+coucher;

          EmbedBuilder eb = new EmbedBuilder();
          eb.setTitle("Météo", null);
          eb.addField("Ville", villeString, false);
          eb.addField("Température", tempString, false);
          eb.addField("Lever du Soleil: ", leverString, false);
          eb.addField("Coucher du Soleil: ", coucherString, false);

          channel.sendMessage(eb.build()).queue();
        }catch(Exception e){
          System.out.println(e);
        }

      }
    }
  }
}
