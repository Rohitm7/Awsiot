package excel_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_write {

	public static void excelWrite_overwrite(String Data, int rown, int cellno, String filename, int sheetno) {
		try {
			String Working_Directory = System.getProperty("user.dir");
			String FilePath = Working_Directory + File.separator + "src" + File.separator + "main" + File.separator
					+ "resources" + File.separator + filename;
			FileInputStream fis = new FileInputStream(FilePath);

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(sheetno);
			int row = sh.getLastRowNum();
			// System.out.println("Lastrow : "+row);
			if (row < rown) {
				sh.createRow(rown).createCell(cellno).setCellValue(Data);
			} else {
				sh.getRow(rown).createCell(cellno).setCellValue(Data);
			}
			FileOutputStream fos = new FileOutputStream(FilePath);
			wb.write(fos);
			// wb.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
