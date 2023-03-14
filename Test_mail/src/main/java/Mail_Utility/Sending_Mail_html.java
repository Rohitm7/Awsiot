package Mail_Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import Config_Utility.config_read;
import javax.mail.internet.MimeMultipart;

public class Sending_Mail_html {

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message) throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);
		// creates a new session with an authenticator
		
	    System.out.println("Encoded String :"+password);

		byte[] decodedBytes = Base64.getDecoder().decode(password);
		String decodedString = new String(decodedBytes);
		System.out.println("Decoded String :"+decodedString);

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, decodedString);
			}
		};

		Session session = Session.getInstance(properties, auth);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// creates a new e-mail message

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		// msg.addRecipient(RecipientType.CC, new
		// InternetAddress("divya@untangleds.com"));
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message, "text/html");
		Transport.send(msg);
	}

	public static void main(String message1) throws Exception {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";

		String mailFrom = config_read.read_configvalue("mailFrom");
		//System.out.println(mailFrom);

		String password = config_read.read_configvalue("apppassword");
		
		String encryptedpassword = Base64.getEncoder().encodeToString(password.getBytes());
		System.out.println("Encrypted password: " + encryptedpassword);

		// message info
		String mailTo = config_read.read_configvalue("mailTo");

		String ProjectName = "Test MAil With Git";
		String message = "Test-cases Reports";
		message = "<h1><font color=blue><b>Test Cases Reports<b></font></h1>";
		message += "<h2><b>Project Name :" + ProjectName + "</b></h2>";

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
				message += "</tr>";

			} else if (Testcases_Namec.contains("Passed")) {

			} else {
				message += "<h4>" + Testcases_Namec + "</h4>";
			}
		}

		String subject = "Testing mail with git," + LocalDateTime.now() + "";

		try {
			sendEmailWithAttachments(host, port, mailFrom, encryptedpassword, mailTo, subject, message);
			System.out.println("Email sent Succesfully..");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}

	}

}
