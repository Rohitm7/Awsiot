package Postman_Api_Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import Config_Utility.config_read;
import Date_Utility.Date_time_conv_inmemory;
import Telegram_Send_Msg.Telegram_Connect;
import excel_Utility.Excel_count_row;
import excel_Utility.Excel_write;

public class API_Parameter_request {

	public static String DT_DB = null;
	public static String DT_Inmemory = null;
	
	static String[] Instances= new String[]{"e2e","www","e2e","www"};

	public static int count_row() {
		int lastrow = Excel_count_row.Last_rowNumber("API_Parameter.xlsx", 0);
		lastrow = lastrow + 1;
		return lastrow;
	}
	
	// Request for API-DB Login
	public static void Postman_Api_DB_login(int i,int row) throws Exception {
		//row = count_row();
		String api_DB_url = config_read.read_configvalue("Api_Url" + i);
		Excel_write.excelWrite_overwrite(Instances[i],row , 0, "API_Parameter.xlsx", 0);
		Excel_write.excelWrite_overwrite(api_DB_url,row , 1, "API_Parameter.xlsx", 0);
		
		String username = config_read.read_configvalue("username" + i);
		String password = config_read.read_configvalue("password" + i);
		post(api_DB_url, "{\"packet_identifier\":\"login_request\",\"username\":\"" + username + "\",\"password\":\""
				+ password + "\",\"MI\":1234567890}",row);
	}

	// Request for DB parameter Data
	public static void Postman_Api_DB_read_Parameter_Collection(int i,int row) throws Exception {
		String Start_date_Time = Date_time_conv_inmemory.Start_date_Time_Parameter();
		String End_date_time = Date_time_conv_inmemory.End_date_conv();
		String Key = config_read.read_configvalue("key" + i);
		String api_DB_url = config_read.read_configvalue("Api_Url" + i);
		
	    Excel_write.excelWrite_overwrite(Instances[i],row , 0, "API_Parameter.xlsx", 0);
		Excel_write.excelWrite_overwrite(api_DB_url,row , 1, "API_Parameter.xlsx", 0);
		
		post(api_DB_url, "{\"key\":\"" + Key
				+ "\",\"packet_identifier\": \"historical_data\",\"message_id\": 1234567890,\"MN\":[\"Automation_Device2\"],\"limit\": 1,"
				+ "\"start_date_time\":\"" + Start_date_Time + "\",\"end_date_time\":\"" + End_date_time + "\"}",row);
	}

	// Request for In-memory Login
	public static void Postman_Api_Inmem_login(int i,int row) throws Exception {
		String api_Inmem_url = config_read.read_configvalue("Api_Url" + i);		
		String username = config_read.read_configvalue("username" + i);
		String password = config_read.read_configvalue("password" + i);
		post(api_Inmem_url, "{\"request_type\":\"login_request\",\"username\":\"" + username + "\",\"password\":\""
				+ password + "\",\"MI\":1234567890}",row);
	}

	// Request for In-memory parameter Data
	public static void Postman_Api_Inmem_read_Parameter_Collection(int i,int row) throws Exception {
		String api_Inmem_url = config_read.read_configvalue("Api_Url" + i);
		
		Excel_write.excelWrite_overwrite(Instances[i],row , 0, "API_Parameter.xlsx", 0);
		Excel_write.excelWrite_overwrite(api_Inmem_url,row , 1, "API_Parameter.xlsx", 0);
		
		String Key = config_read.read_configvalue("key" + i);

		post(api_Inmem_url, "{\"key\":\"" + Key
				+ "\",\"request_type\": \"read_collection\",\"collection_name\": \"Automation_Device2\",\"limit\": 1,\"query\": {}}",row);
	}

	public static String post(String url, String json,int row) throws Exception {
		// connection for postman
		String charset = "UTF-8";
		System.out.println("URL:" + url);

		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

		// Sending json format
		try (OutputStream output = connection.getOutputStream()) {
			output.write(json.getBytes(charset));
			 System.out.println("Last Row no: "+output);

			 Excel_write.excelWrite_overwrite(json, row, 2, "API_Parameter.xlsx", 0);
		}

		// Reading request response
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			String repsponse_data = response.toString();
			 Excel_write.excelWrite_overwrite(repsponse_data, row, 3, "API_Parameter.xlsx", 0);
		
			// string split using comma

			String[] Formatted_response = response.toString().split(",");
			System.out.println("Api Request Response :"+Formatted_response);
			for (String string : Formatted_response) {
				System.out.println(string);
				if (string.contains("DT")) {
					// int DT_Index = string.indexOf("DT");
					// System.out.println("DT_Index :" + DT_Index);
					DT_DB = string.substring(6, 25);
					System.out.println("DT From response :" + DT_DB);
				}
			}
		} catch (Exception e) {
			Excel_write.excelWrite_overwrite("FAIL",row , 5, "API_Parameter.xlsx", 0);
			Telegram_Connect.Telegram_request("API Request is not working in"+url);
			System.out.println("e"+e);
			return "false";
		}
		return DT_DB;
	}

	public static String End_date_conv() {
		LocalDateTime ldt = LocalDateTime.now();
		// System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		// System.out.println("End date and Time :"+formattedString);
		return formattedString;
	}

	public static String Start_date_Time_Schedule_hour() {
		LocalDateTime ldt = LocalDateTime.now();
		// System.out.println(ldt);
		ldt = LocalDateTime.now().minusMinutes(60);
		// System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		System.out.println("Start Date and Time :" + formattedString);
		return formattedString;
	}

	public static long Calculate_Difference(String DT) throws Exception {
		String Current_DT = Date_time_conv_inmemory.End_date_conv();
		System.out.println("Current Date :" + Current_DT);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		Date firstDate = sdf.parse(DT);
		// Excel_write.excelWrite_overwrite(DT, 1, 2, "Automation_Sch_Hour.xlsx", 2);

		System.out.println("DT From Packet :" + firstDate);
		Date secondDate = sdf.parse(Current_DT);
		// Excel_write.excelWrite_overwrite(Current_DT, 1, 3,
		// "Automation_Sch_Hour.xlsx", 2);

		System.out.println("Current Date :" + secondDate);

		long diff = secondDate.getTime() - firstDate.getTime();
		// System.out.println(diff);
		long diffInDays = TimeUnit.MILLISECONDS.toDays(diff);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(diff);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);

		if (diffInDays == 0 && diffInHours > 2) {
			System.out.println("Difference In Hours : " + diffInHours);
			// System.out.println("Difference In Minutes : " + diffInMinutes);
			return diffInHours;
		} else if (diffInHours < 2 && diffInMinutes >= 0) {
			// diffInMinutes = diffInMinutes / 60;
			System.out.println("Difference In Minutes : " + diffInMinutes);
			return diffInMinutes;
		} else if (diffInMinutes == 0) {
			// diffInSeconds = diffInSeconds / 60;
			System.out.println("Diifernce In Seconds : " + diffInSeconds);
			return diffInSeconds;
		} else {
			System.out.println("Diifference In Days : " + diffInDays);
			return diffInDays;
		}
	}

}
