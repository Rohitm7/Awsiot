package Mail_Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Config_Utility.config_read;

public class Sending_Mail_html {

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message) throws Exception {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);
		// creates a new session with an authenticator
		
		byte[] decodedBytes = Base64.getDecoder().decode(password);
		String decodedString = new String(decodedBytes);
		System.out.println("Decoded String :"+decodedString);

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, decodedString);
			}
		};

		Session session = Session.getInstance(properties, auth);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// creates a new e-mail message

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);

		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message, "text/html");
		Transport.send(msg);
	}

	public static void Mail_Fun(String message1) throws Exception {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = config_read.read_configvalue("mailFrom");
		System.out.println(mailFrom);

		String password = config_read.read_configvalue("apppassword");
		
		String encryptedpassword = Base64.getEncoder().encodeToString(password.getBytes());
		System.out.println("Encrypted password: " + encryptedpassword);

		// Message info
		String mailTo = config_read.read_configvalue("mailTo");
		String ProjectName = "Infosys dashboard";
		String message = "Dashboard Status";
		message = "<h1><font color=blue><b>Test Reports<b></font></h1>";
		message += "<h2><b>Project Name :" + ProjectName + "</b></h2>";

		message += "<table cellpadding=\"0\" cellpadding=\"0\" style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;box-sizing:border-box;width:100%;margin:0;padding:35px 0\"> <tr><th>Test Name</th><th>Results</th></tr>";
		int count = 0;
		for (int i = 0; i < message1.length(); i++) {
			if (message1.charAt(i) == ',')
				count++;
		}

		String[] Testcases_Name = message1.split(",");
		for (int c = 0; c < count; c++) {
			String Testcases_Namec = Testcases_Name[c];

			if (Testcases_Namec.contains("Failed")) {
				message += "<tr>";

				Testcases_Namec = Testcases_Namec.substring(17);
				message += "<td style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;box-sizing:border-box;padding:10px 5px;color:#cc0000;line-height:18px;border-width:3px;border-style:solid;width:2em;height:1em;border-color:#fff;font-size:20px;text-align:center\">"
						+ Testcases_Namec + "</font></h4></td>";
				message += "<td bgcolor=\"#cc0000\" style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;box-sizing:border-box;padding:10px 5px;line-height:18px;border-width:3px;border-style:solid;width:2em;height:1em;border-color:#fff;font-size:20px;text-align:center;color:#cc0000\"></font></h4></td>";
				message += "</tr>";
			} else if (Testcases_Namec.contains("Passed")) {
				message += "<tr>";
				Testcases_Namec = Testcases_Namec.substring(17);
				message += "<td style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;box-sizing:border-box;padding:10px 5px;color:#009900;line-height:18px;border-width:3px;border-style:solid;width:2em;height:1em;border-color:#fff;font-size:20px;text-align:center\">"
						+ Testcases_Namec + "</font></h4></td>";
				message += "<td bgcolor=\"#009900\" style=\"font-family:'Google Sans',Roboto,RobotoDraft,Helvetica,Arial,sans-serif;box-sizing:border-box;padding:10px 5px;line-height:18px;border-width:3px;border-style:solid;width:2em;height:1em;border-color:#fff;font-size:20px;text-align:center;color:#009900\"></font></h4></td>";

				message += "</tr>";
			} else {
				message += "<h4>" + Testcases_Namec + "</h4>";
			}
		}
		message += "</table>";

		String subject = "Infosys Dashboard Ping Report :," + LocalDateTime.now() + "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmm");
		Calendar now = Calendar.getInstance();

		try {
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo, subject, message);
			// System.out.println("Email sent Succesfully..");
		} catch (Exception ex) {
			ex.printStackTrace();
		
		}

	}

}
