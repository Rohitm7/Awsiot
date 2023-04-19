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
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Testclass {
	static AndroidDriver  driver;
	
	@Test
	public void testcases() throws Exception{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "POCO M4 Pro");
		cap.setCapability("udid", "10.0.1.3:5555");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "12");

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
		
		driver.findElement(By.xpath("(//android.view.View[@index='4'])[2]")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.Button[@text='SCAN']")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("//android.widget.Button[@text='While using the app']")).click();
		Thread.sleep(500);


		
/*
 * Touch Action class is deprecated so in upcoming version it will be removed.
 */
				
//		TouchAction action = new TouchAction(driver);
//		action.press(PointOption.point(500, 1500))
//		      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//		      .moveTo(PointOption.point(500, 1000))
//		      .release()
//		      .perform();

/*
 * For this one Java Client API should 7.6.0 but in that also throwing some error.
 */
		
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

		
		/*
		 * This will support in new version w3c scroll 
		 */
		//Using 500,1500 pointer will go touch to the middle of the page.
		Point startPoint = new Point(500, 1500);
		//Using 0,0  100% page is scrooling upto 7 Assigned Ticeket is scrolling...
		//Using -500,-1500 300% page is scrolling upto 21 Assigned Ticket is scrolling.
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
