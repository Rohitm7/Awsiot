package Screenshot_Utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Takescrenshot {


	public static void robo() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmm");
		Calendar now = Calendar.getInstance();
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenShot, "JPG", new File("/Users/aneesh/Desktop/Test_Screnshot/Screenshot" + formatter.format(now.getTime()) + ".jpg"));
		System.out.println(formatter.format(now.getTime()));
	}

}
