package excel_Utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_count_row {

	public static int Last_rowNumber(String FileName, int sheetno) {
		int row = 0;
		try {
			DataFormatter df = new DataFormatter();

			String Working_Directory = System.getProperty("user.dir");
			String FilePath = Working_Directory + File.separator + "src" + File.separator + "main" + File.separator
					+ "resources" + File.separator + FileName;
			FileInputStream fis = new FileInputStream(FilePath);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheetAt(sheetno);
			row = sh.getLastRowNum();
			System.out.println("No of row : " + row);
		} catch (Exception e) {
			System.out.println(e);
		}
		return row;
	}

}
