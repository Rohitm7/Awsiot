package TestClass_API;

import org.testng.annotations.Test;
import Config_Utility.config_read;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Telegram_Send_Msg.Telegram_Connect;
import Webdrivermanager_Utility.WebdriverManager_Setup;

@Listeners(itestListener_Utility.Isuitelistener_test.class)

public class TestCases_API_Inmem extends WebdriverManager_Setup {

	public float[] Ph_values_arr = new float[3];

	@BeforeMethod
	public void Beforemethod() {
		// Setup for Chrome driver using webdriver Manager
		driver_Setup();
	}

	@Test
	public void Infosys_Ping() throws Exception {
		String Infosys_Url = config_read.read_configvalue("Infosys_Dash_Url");
		// launching url
		System.out.println("Url :" + Infosys_Url);
		driver.get(Infosys_Url);

		// waiting for 10 seconds to load dashboard...(Worst scenario we have to waitfor
		// 10 sec)

		Thread.sleep(10000);
		Telegram_Connect.Telegram_request("PH Values :");
		String[] Parameter = new String[] { "STP Inlet", "STP Outlet", "Lake water" };

		for (int i = 2; i < 5; i++) {
			// Using For loop reading Ph values without PH unit
			String PH_values = driver.findElement(By.xpath("//table/tbody/tr[2]/th[" + i + "]")).getText();
			
			//PH_values = PH_values.substring(0, 3);
			
			PH_values = PH_values.replace("pH", " ");

			// Converting Ph values string to Integer
			Telegram_Connect.Telegram_request(Parameter[i - 2] + " : " + PH_values);

			float Ph_val = Float.valueOf(PH_values);
			Ph_values_arr[i - 2] = Ph_val;
			// System.out.println("PH Row Values after wait :" + PH_values);
		}

		for (int i = 0; i < Ph_values_arr.length; i++) {
			if (Ph_values_arr[i] > 6.5 && Ph_values_arr[i] < 8.5) {
				System.out.println("PH Row Values from array  :" + Ph_values_arr[i]);
				Assert.assertEquals(true, true);
			} else {
				Assert.assertEquals(true, false);
			}
		}
	}

}
