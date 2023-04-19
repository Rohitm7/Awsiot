package Testclass;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Testclass {
	static AndroidDriver<MobileElement> driver;
	
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
		
//		TouchAction action = new TouchAction(driver);
//		action.press(PointOption.point(500, 1500))
//		      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//		      .moveTo(PointOption.point(500, 1000))
//		      .release()
//		      .perform();


		// Define the starting and ending coordinates of the swipe gesture
		
//		MobileElement element = driver.findElement(By.xpath("//android.widget.TextView[text()='ID: 1681445918439']"));
//
//		// Get the location and size of the element
//		Point location = element.getLocation();
//		Dimension size = element.getSize();
//
//		// Define the start and end point coordinates for the swipe gesture
//		int startX = location.getX() + (int) (0.5 * size.getWidth());
//		int startY = location.getY() + (int) (0.8 * size.getHeight());
//		int endX = location.getX() + (int) (0.5 * size.getWidth());
//		int endY = location.getY() + (int) (0.2 * size.getHeight());
//		
//		System.out.println("startX :"+startX);
//		System.out.println("startX :"+startY);
//		System.out.println("startX :"+endX);
//		System.out.println("startX :"+endY);
		
		Point startPoint = new Point(500, 1500);
		Point endPoint = new Point(0, 0);

		// Create a new PointerInput object
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create a new Sequence object
		Sequence swipe = new Sequence(finger, 2);

		// Add a pointer move action to the starting point
		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.x, startPoint.y));

		// Add a pointer down action to simulate touching the screen
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		// Add a pointer move action to the ending point
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endPoint.x, endPoint.y));

		// Add a pointer up action to simulate releasing the screen
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the swipe gesture
		driver.perform(Arrays.asList(swipe));
		
	}

}
