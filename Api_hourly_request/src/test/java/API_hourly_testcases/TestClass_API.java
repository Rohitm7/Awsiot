package API_hourly_testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import Telegram_Send_Msg.Telegram_Connect;
import api_request.Inmemory_request;

public class TestClass_API {
	@Test(priority = 1)
	public void API_InMemory_E2E_Sch_Hour() throws Exception {
		Inmemory_request.Postman_Api_Inmem_login(0);
		Inmemory_request.Postman_Api_Inmem_read_Sche_hour_Collection(0);

		String DT = Inmemory_request.DT;
		// System.out.println("DT from Hourly Packet :" + DT);

		long diff = Inmemory_request.Calculate_Difference(DT);
		String Diff = String.valueOf(diff);
		System.out.println("Difference Between current DT and Packet DT is :" + Diff);

		if (diff < 36) {
			System.out.println("Last Schedule hour data generated on : " + diff);
			Telegram_Connect.Telegram_request("API In-Memory Schedule Hour Request is working in E2E .");
			Assert.assertEquals(true, true);
		} else {
			System.out.println("Last Schedule hour data generated on : " + diff);
			Telegram_Connect.Telegram_request("API In_Memory Schedule Hour Request  is not working in E2E .");
			Assert.assertEquals(true, false);
		}
	}
	
	@Test(priority = 2)
	public void API_InMemory_WWW_Sch_Hour() throws Exception {
		Inmemory_request.Postman_Api_Inmem_login(1);
		Inmemory_request.Postman_Api_Inmem_read_Sche_hour_Collection(1);

		String DT = Inmemory_request.DT;
		// System.out.println("DT from Hourly Packet :" + DT);

		long diff = Inmemory_request.Calculate_Difference(DT);
		String Diff = String.valueOf(diff);
		System.out.println("Difference Between current DT and Packet DT is :" + Diff);

		if (diff < 36) {
			System.out.println("Last Schedule hour data generated on : " + diff);
			Telegram_Connect.Telegram_request("API In-Memory Schedule Hour Request is working in www .");
			Assert.assertEquals(true, true);
		} else {
			System.out.println("Last Schedule hour data generated on : " + diff);
			Telegram_Connect.Telegram_request("API In_Memory Schedule Hour Request  is not working in www .");
			Assert.assertEquals(true, false);
		}
	}
}
