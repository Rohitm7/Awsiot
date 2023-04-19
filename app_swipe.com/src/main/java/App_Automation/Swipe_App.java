package App_Automation;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class Swipe_App {
	static AndroidDriver driver;

	public static void main(String[] args) throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Samsung Testing Phone");
		cap.setCapability("udid", "10.0.1.130:5555");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "13");

		cap.setCapability("appPackage", "com.sec.android.gallery3d");
		cap.setCapability("appActivity", "com.samsung.android.gallary.app.activity.GalleryActivity");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, cap);

		System.out.println("Application Launched");
		

		Thread.sleep(3000);
	}

}
