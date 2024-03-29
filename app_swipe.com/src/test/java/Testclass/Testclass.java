package Testclass;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Testclass {
	static AndroidDriver driver;

	@Test
	public void testcases() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Testing Phone");
		cap.setCapability("udid", "10.0.1.130:5555");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "13");

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

		driver.findElement(By.xpath("(//android.view.View[@index='4'])[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//android.widget.Button[@text='SCAN']")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath("//android.widget.Button[@text='While using the app']")).click();
		Thread.sleep(5000);

		//Form Type = Test_Parameter30ID
		//Id Webelement
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText"))
				.sendKeys("Test_R_ID_01");

		// Description
		driver.findElement(By.xpath("(//android.widget.EditText[@index='0'])[2]")).sendKeys("Test_R_Des_01");
		// Parameter_2 Text
		driver.findElement(By.xpath("(//android.widget.EditText[@index='0'])[4]")).sendKeys("R_Text_01");

		// Parameter_3 Number
		driver.findElement(By.xpath("(//android.widget.EditText[@index='0'])[5]")).click();
		Thread.sleep(1000);

		KeyEvent keyEvent2 = new KeyEvent().withKey(AndroidKey.DIGIT_2);
		driver.pressKey(keyEvent2);
		Thread.sleep(500);
		
		//Press "7" key
		KeyEvent keyEvent7 = new KeyEvent().withKey(AndroidKey.DIGIT_7);
		driver.pressKey(keyEvent7);
		driver.navigate().back();
		
		Thread.sleep(500);

		// Using 500,1500 pointer will go touch to the middle of the page.
		Point startPoint = new Point(500, 1500);
		// Using 0,0 100% page is scrooling upto 7 Assigned Ticeket is scrolling...
		// Using -500,-1500 300% page is scrolling upto 21 Assigned Ticket is scrolling.
		Point endPoint = new Point(100, 1000);
		// Create a new PointerInput object
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		//Create a new Sequence object
		Sequence swipe = new Sequence(finger, 2);
		//Add a pointer move action to the starting point
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.x, startPoint.y));
		//Add a pointer down action to simulate touching the screen
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		//Add a pointer move action to the ending point
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endPoint.x, endPoint.y));
		//Add a pointer up action to simulate releasing the screen
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		//Perform the swipe gesture
		driver.perform(Arrays.asList(swipe));
		Thread.sleep(1500);

		//Parameter_4 Text
		driver.findElement(By.xpath("(//android.widget.EditText[@index='0'])[6]")).click();
	}

}
