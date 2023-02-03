package excel_Utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Read {

	public static String Excelread(String filename, int row, int cell, int sheetno) {
		String s = null;
		try {
			DataFormatter df = new DataFormatter();
			
			String Working_Directory = System.getProperty("user.dir");
			String FilePath = Working_Directory + File.separator + "src" + File.separator + "main" + File.separator
					+ "resources" + File.separator + filename;
			FileInputStream fis = new FileInputStream(FilePath);

			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheetAt(sheetno);
			int col = sh.getRow(row).getLastCellNum();
			Cell c = sh.getRow(row).getCell(cell);
			s = df.formatCellValue(c);
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;

	}

}
