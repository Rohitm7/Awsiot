package Telegram_Send_Msg;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import javax.ws.rs.core.UriBuilder;


public class Telegram_Connect {
	
	  private static final String CHAT_ID ="-1001628615745";
	  //1628615745
	  private static final String TOKEN = "5214529801:AAGNeoC_z_mpY0Byn82YkSja5ulJZwhA0jE";
	 
	    public static void Telegram_request (String message) throws IOException, InterruptedException {
	 
	       // String message = "Tested";
	 
	        HttpClient client = HttpClient.newBuilder()
	                .connectTimeout(Duration.ofSeconds(5))
	                .version(HttpClient.Version.HTTP_2)
	                .build();
	 
	        UriBuilder builder = UriBuilder
	                .fromUri("https://api.telegram.org")
	                .path("/{token}/sendMessage")
	                .queryParam("chat_id", CHAT_ID)
	                .queryParam("text", message);
	     
	 
	        HttpRequest request = HttpRequest.newBuilder()
	                .GET()
	                .uri(builder.build("bot" + TOKEN))
	                .timeout(Duration.ofSeconds(5))
	                .build();
	 
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	 
	        System.out.println(response.statusCode());
	       // System.out.println(response.body());
	    }

}
