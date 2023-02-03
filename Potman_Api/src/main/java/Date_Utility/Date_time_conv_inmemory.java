package Date_Utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import net.bytebuddy.asm.Advice.Return;

public class Date_time_conv_inmemory {
	
	//For current date
	public static String End_date_conv() {
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		//System.out.println("End date and Time :"+formattedString);
		return formattedString;
	}
	
	//For Reading in-memory parameter collection
	public static String Start_date_Time_Parameter() {
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println(ldt);
		ldt = LocalDateTime.now().minusMinutes(16);
		//System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		System.out.println("Start Date and Time :"+formattedString);
		return formattedString;		
	}
	
	//For Reading in-memory Schedule hour collection
	public static String Start_date_Time_Schedule_hour() {
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println(ldt);
		ldt = LocalDateTime.now().minusMinutes(60);
		//System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		System.out.println("Start Date and Time :"+formattedString);
		return formattedString;		
	}
	
	//For Reading in-memory Schedule day collection 
	public static String Start_date_Time_Schedule_Day() {
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println(ldt);
		ldt = LocalDateTime.now().minusDays(1);
		//System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		System.out.println("Start Date and Time :"+formattedString);
		return formattedString;		
	}
	
	public static String Start_date_Time_Schedule_Month() {
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println(ldt);
		ldt = LocalDateTime.now().minusMonths(1);
		//System.out.println(ldt);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		String formattedString = ldt.format(formatter);
		System.out.println("Start Date and Time :"+formattedString);
		return formattedString;		
	}
	


}
