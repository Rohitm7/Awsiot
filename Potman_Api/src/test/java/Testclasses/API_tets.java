package Testclasses;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import Postman_Api_Request.API_Parameter_request;
import Telegram_Send_Msg.Telegram_Connect;
import excel_Utility.Excel_write;

@Listeners(itestListener_Utility.Isuitelistener_test.class)

public class API_tets {

	@Test(priority = 1)
	public void E2E_DB() throws Exception {
		try {
			int row = API_Parameter_request.count_row();
			API_Parameter_request.Postman_Api_DB_login(2, row);
			API_Parameter_request.Postman_Api_DB_read_Parameter_Collection(2, row);

			String DT = API_Parameter_request.DT_DB;
			System.out.println("DT:" + DT);

			long diff = API_Parameter_request.Calculate_Difference(DT);
			String Diff = String.valueOf(diff);
			Excel_write.excelWrite_overwrite(Diff, row, 4, "API_Parameter.xlsx", 0);

			if (diff < 16) {
				System.out.println("Difference for Schedule hour data : " + diff);
				Excel_write.excelWrite_overwrite("PASS", row, 5, "API_Parameter.xlsx", 0);
			//	Telegram_Connect.Telegram_request("API DB Request is working in E2E .");
				Assert.assertEquals(true, true);
			} else {
				System.out.println("Latest DT is showing wrong in Schedule hour Request : " + diff);
				Excel_write.excelWrite_overwrite("FAIL", row, 5, "API_Parameter.xlsx", 0);
				Telegram_Connect.Telegram_request("API DB Request is not working in E2E .");
				Assert.assertEquals(true, false);
			}
		} catch (Exception e) {
			System.out.println("Exception in E2E DB Request :" + e);
			Telegram_Connect.Telegram_request("API DB Request is not working in E2E .");
			Assert.assertEquals(true, false);
		}
	}

	@Test(priority = 2)
	public void E2E_Inmemory() throws Exception {
		try {
			int row = API_Parameter_request.count_row();
			API_Parameter_request.Postman_Api_Inmem_login(0, row);
			API_Parameter_request.Postman_Api_Inmem_read_Parameter_Collection(0, row);

			String DT = API_Parameter_request.DT_DB;
			System.out.println("DT:" + DT);

			long diff = API_Parameter_request.Calculate_Difference(DT);
			String Diff = String.valueOf(diff);

			Excel_write.excelWrite_overwrite(Diff, row, 4, "API_Parameter.xlsx", 0);

			if (diff < 16) {
				System.out.println("Difference for Schedule hour data : " + diff);
				Excel_write.excelWrite_overwrite("PASS", row, 5, "API_Parameter.xlsx", 0);
				//Telegram_Connect.Telegram_request("API In-Memory Request is working in E2E.");
				Assert.assertEquals(true, true);

			} else {
				System.out.println("Latest DT is showing wrong in Schedule hour Request : " + diff);
				Excel_write.excelWrite_overwrite("FAIL", row, 5, "API_Parameter.xlsx", 0);
				Telegram_Connect.Telegram_request("API In-Memory Request is not working in E2E .");
				Assert.assertEquals(true, false);
			}
		} catch (Exception e) {
			System.out.println("Exception in E2E In-Memory Request :" + e);
			Telegram_Connect.Telegram_request("API DB Request is not working in E2E .");

			Assert.assertEquals(true, false);
		}

	}

	//@Test(priority = 3)
	public static void WWW_DB() throws Exception {
		try {
			int row = API_Parameter_request.count_row();

			API_Parameter_request.Postman_Api_DB_login(3, row);
			API_Parameter_request.Postman_Api_DB_read_Parameter_Collection(3, row);

			String DT = API_Parameter_request.DT_DB;
			System.out.println("DT:" + DT);

			long diff = API_Parameter_request.Calculate_Difference(DT);
			String Diff = String.valueOf(diff);
			Excel_write.excelWrite_overwrite(Diff, row, 4, "API_Parameter.xlsx", 0);

			if (diff < 16) {
				System.out.println("Difference for Schedule hour data : " + diff);
				Excel_write.excelWrite_overwrite("PASS", row, 5, "API_Parameter.xlsx", 0);
				//Telegram_Connect.Telegram_request("API DB Request is working in www .");
				Assert.assertEquals(true, true);

			} else {
				System.out.println("Latest DT is showing wrong in Schedule hour Request : " + diff);
				Telegram_Connect.Telegram_request("API DB Request is not working in www .");
				Assert.assertEquals(true, false);
			}
		} catch (Exception e) {
			System.out.println("Exception in WWW DB Request :" + e);
			Assert.assertEquals(true, false);
		}

	}

	@Test(priority = 3)
	public void WWW_Inmemory() throws Exception {
		try {
			int row = API_Parameter_request.count_row();

			API_Parameter_request.Postman_Api_Inmem_login(1, row);
			API_Parameter_request.Postman_Api_Inmem_read_Parameter_Collection(1, row);

			String DT = API_Parameter_request.DT_DB;
			System.out.println("DT:" + DT);

			long diff = API_Parameter_request.Calculate_Difference(DT);
			String Diff = String.valueOf(diff);
			Excel_write.excelWrite_overwrite(Diff, row, 4, "API_Parameter.xlsx", 0);

			if (diff < 16) {
				System.out.println("Difference for Schedule hour data : " + diff);
				Excel_write.excelWrite_overwrite("PASS", row, 5, "API_Parameter.xlsx", 0);
			//	Telegram_Connect.Telegram_request("API In-Memory Request is working in www.");
				Assert.assertEquals(true, true);

			} else {
				System.out.println("Latest DT is showing wrong in Schedule hour Request : " + diff);
				Excel_write.excelWrite_overwrite("FAIL", row, 5, "API_Parameter.xlsx", 0);
				Telegram_Connect.Telegram_request("API In-Memory Request is not working in www.");
				Assert.assertEquals(true, false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in WWW In-Memory Request :" + e);
			Telegram_Connect.Telegram_request("API DB Request is not working in E2E .");
			Assert.assertEquals(true, false);

		}
	}

}
