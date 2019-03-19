import net.dv8tion.jda.core.entities.*;
import java.net.*;
import java.io.*;
import java.util.stream.*;
import org.json.*;
import java.time.*;
import java.text.*;
import net.dv8tion.jda.core.EmbedBuilder;
import java.nio.charset.*;

public class Blague{
  EmbedBuilder eb = new EmbedBuilder();
  MessageEmbed reponseBlague(){
    String result = "";
    try{
      int rand = (int)(Math.random()*115);
      URL url = new URL("https://bridge.buddyweb.fr/api/blagues/blagues/"+rand);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection ();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept","application/json");
      if(conn.getResponseCode()!=200){
        System.out.println("Failed:HTTPerrorcode:"+conn.getResponseCode());
      }
      BufferedReader br=new BufferedReader(new InputStreamReader((conn.getInputStream())));
      result=br.lines().collect(Collectors.joining());
      conn.disconnect();
      JSONObject jsBlague = new JSONObject(result);
      String blague = jsBlague.getString("blagues");
      byte [] b = blague.getBytes(StandardCharsets.UTF_8);
      String s = new String(b, StandardCharsets.UTF_8);

      eb.setTitle("Blague n°"+rand, null);
      eb.addField( "",s, false);
    }catch(Exception e){
      System.out.println(e);
    }
    return eb.build();
  }
}
