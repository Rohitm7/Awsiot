package Config_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class config_read {

	public static String read_configvalue(String Key) throws Exception {
		Properties prop = new Properties();
		String Filename="config.properties";
        String Working_Directory=System.getProperty("user.dir");
       // System.out.println("Working_Directory :"+Working_Directory); 
        
        String FilePath=Working_Directory +File.separator+"src"+File.separator+"main"+
        File.separator+"resources"+File.separator+Filename;
        
      //  System.out.println("File path by using file separator :"+FilePath);	
		FileInputStream ip = new FileInputStream(FilePath);
		prop.load(ip);
		String value = prop.getProperty(Key);
		return value;
	}
	
	

}
