package Testclass;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Testclass {
	static AndroidDriver driver;
	@Test
	public void testcases() throws Exception{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Samsung Testing Phone");
		cap.setCapability("udid", "10.0.1.130:5555");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "13");

	//	cap.setCapability("appPackage", "com.sec.android.gallery3d");
	//	cap.setCapability("appActivity", "com.samsung.android.gallary.app.activity.GalleryActivity");
		
		cap.setCapability("appPackage", "io.ionic.starter.untangled_ams_task");
		cap.setCapability("appActivity", "io.ionic.starter.untangled_ams_task.MainActivity");
			
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, cap);
		System.out.println("Application Launched");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//*[@index='0'])[21]")).sendKeys("ams_automation");
		driver.findElement(By.xpath("(//*[@index='0'])[25]")).sendKeys("Ams_automati0n");
		driver.findElement(By.xpath("//android.widget.Button[@text='SIGN IN']")).click();

		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Assigned Tickets']")).click();

		Thread.sleep(2000);
		
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(500, 1500))
		      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		      .moveTo(PointOption.point(500, 1000))
		      .release()
		      .perform();

		
	}

}
