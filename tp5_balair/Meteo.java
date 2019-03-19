import net.dv8tion.jda.core.entities.*;
import java.net.*;
import java.io.*;
import java.util.stream.*;
import org.json.*;
import java.time.*;
import java.text.*;
import net.dv8tion.jda.core.EmbedBuilder;
import java.nio.charset.*;

public class Meteo{
  EmbedBuilder eb;
  MessageEmbed reponseMeteoVille(String villeAPI){
    eb = new EmbedBuilder();
    String result= "" ;
    String APIKey = "2b689cc9a8af023d443fd8164d5e8ba3";
    String serv = "http://api.openweathermap.org/data/2.5/weather";
    String param = "q="+villeAPI+"&fr&appid=";
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

      long leverTime = jsMeteo.getJSONObject("sys").getInt("sunrise");
      LocalDateTime timeLever = LocalDateTime.ofEpochSecond(leverTime, 0, ZoneOffset.of("+1"));
      int leverHour = timeLever.getHour();
      int leverMinute = timeLever.getMinute();
      String lever = nf.format(leverHour)+":"+nf.format(leverMinute);

      long coucherTime = jsMeteo.getJSONObject("sys").getInt("sunset");
      LocalDateTime timeCoucher = LocalDateTime.ofEpochSecond(coucherTime, 0, ZoneOffset.of("+1"));
      int coucherHour = timeCoucher.getHour();
      int coucherMinutes = timeCoucher.getMinute();
      String coucher = nf.format(coucherHour)+":"+nf.format(coucherMinutes);

      String villeString = " "+ville;
      String tempString = " "+ df.format(temp)+"°C";
      String leverString = " "+lever;
      String coucherString = " "+coucher;

      String imgURL = "http://openweathermap.org/img/w/";
      JSONArray arrWeather = jsMeteo.getJSONArray("weather");
      JSONObject element = arrWeather.getJSONObject(0);
      String img = element.getString("icon");
      imgURL = imgURL + img + ".png";
      MessageEmbed.ImageInfo image = new MessageEmbed.ImageInfo(imgURL, null, 0, 0);

      eb.setTitle("Météo", null);
      eb.addField("Ville", villeString, false);
      eb.addField("Température", tempString, false);
      eb.addField("Lever du Soleil: ", leverString, false);
      eb.addField("Coucher du Soleil: ", coucherString, false);
      eb.setImage(imgURL);

    }catch(Exception e){
      System.out.println(e);
    }
    return eb.build();
  }

  MessageEmbed reponseMeteo(){
    return reponseMeteoVille("Calais");
  }

}
