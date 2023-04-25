package api_request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;
import Config_Utility.config_read;
import Telegram_Send_Msg.Telegram_Connect;

public class Inmemory_request {

	public static String DT = null;
	
	static String[] Instances= new String[]{"e2e","www","e2e","www"};


	public static void Postman_Api_Inmem_login(int i) throws Exception {
		String api_Inmem_url = config_read.read_configvalue("Api_Url"+i);
		String username = config_read.read_configvalue("username"+i);
		String password = config_read.read_configvalue("password"+i);
		post(api_Inmem_url, "{\"request_type\":\"login_request\",\"username\":\"" + username + "\",\"password\":\""
				+ password + "\",\"MI\":1234567890}");
	}

	
	public static void Postman_Api_Inmem_read_Sche_Day_Collection(int i) throws Exception {
		String api_Inmem_url = config_read.read_configvalue("Api_Url"+i);
		String Key = config_read.read_configvalue("key"+i);
		post(api_Inmem_url, "{\"key\":\"" + Key
				+ "\",\"request_type\": \"read_collection\",\"collection_name\": \"Automation_Device2_schedule_day\",\"limit\": 1,\"query\": {}}");
	}


	public static String post(String url, String json) throws Exception {
		// connection for postman
		String charset = "UTF-8";
		// System.out.println("URL:" + url);
		int flag = 0;

		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

		// Sending json format
		try (OutputStream output = connection.getOutputStream()) {
			output.write(json.getBytes(charset));
			// System.out.println("Last Row no: "+output);
		}

		// Reading request response
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}

			// String repsponse_data = response.toString();
			String repsponse_data = String.valueOf(response);
			// System.out.println(repsponse_data.length());
			String findStr = "created_time";
			int count = StringUtils.countMatches(repsponse_data, findStr);
			// System.out.println("No Of Packet In Response : " + count);

			if (count > 0) {
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(repsponse_data); // json path
				JSONObject jsonObject = (JSONObject) obj;
				// System.out.println("Data " + jsonObject.get("data"));

				JSONArray Data_Array = (JSONArray) jsonObject.get("data");
				// System.out.println("Data_Array "+Data_Array);

				for (Object o : Data_Array) {
					JSONObject jsonLine = (JSONObject) o;

					Object MP = jsonLine.get("DT");
					// System.out.println("MP :" + MP);

					DT = String.valueOf(MP);
					// System.out.println("DT :" + DT);
				}
			}

		} catch (Exception e) {
			System.out.println("e" + e);
			Telegram_Connect.Telegram_request("API Hourly Request is not working for :" + url);
			return "false";

		}
		return DT;
	}

	public static String End_date_conv() {
		LocalDateTime ldt = LocalDateTime.now();
		// System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		// System.out.println("End date and Time :"+formattedString);
		return formattedString;
	}

	public static long Calculate_Difference(String DT) throws Exception {
		String Current_DT = End_date_conv();
		// System.out.println("Current Date :" + Current_DT);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		Date firstDate = sdf.parse(DT);

		System.out.println("DT From Packet :" + firstDate);
		Date secondDate = sdf.parse(Current_DT);

		// System.out.println("Current Date :" + secondDate);

		long diff = secondDate.getTime() - firstDate.getTime();
		// System.out.println(diff);
		long diffInDays = TimeUnit.MILLISECONDS.toDays(diff);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(diff);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);

		// System.out.println("diffInDays :" + diffInDays);
		// System.out.println("diffInHours :" + diffInDays);
		// System.out.println("diffInMinutes :" + diffInDays);
		// System.out.println("diffInSeconds :" + diffInDays);

		if (diffInDays > 0) {
			diffInHours = diffInDays * 24;
			System.out.println("Diifernce In Days : " + diffInDays);
			return diffInHours;
		} else if (diffInDays == 0 && diffInHours > 2) {
			System.out.println("Difference In Hours : " + diffInHours);
			// System.out.println("Difference In Minutes : " + diffInMinutes);
			return diffInHours;
		} else if (diffInHours < 2 && diffInMinutes >= 0) {
			diffInHours = diffInMinutes /60;
			System.out.println("Difference In Minutes : " + diffInMinutes);
			return diffInHours;
		} else {
			diffInHours = diffInSeconds /3600;
			System.out.println("Diifernce In Seconds : " + diffInSeconds);
			return diffInHours;
		}
	}
}
