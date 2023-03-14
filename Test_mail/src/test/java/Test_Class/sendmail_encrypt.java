package Test_Class;

import org.testng.annotations.Test;
import java.util.Base64;

import org.testng.annotations.Test;
import Mail_Utility.Sending_Mail_html;

public class sendmail_encrypt {

	@Test
	public void Encryption_mail() throws Exception {
		Sending_Mail_html.main("Testing with passowrd encryption...!");
		
		
	}

}
