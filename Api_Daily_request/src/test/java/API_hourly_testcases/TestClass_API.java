package API_hourly_testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import Telegram_Send_Msg.Telegram_Connect;
import api_request.Inmemory_request;

@Listeners(itestListener_Utility.Isuitelistener_test.class)

public class TestClass_API {

	@Test(priority = 1)
	public void API_InMemory_E2E_Sch_Day() throws Exception {
		Inmemory_request.Postman_Api_Inmem_login(0);
		Inmemory_request.Postman_Api_Inmem_read_Sche_Day_Collection(0);

		String DT = Inmemory_request.DT;
		System.out.println("DT from Schedule Day Packet :" + DT);

		long diff = Inmemory_request.Calculate_Difference(DT);
		String Diff = String.valueOf(diff);
		System.out.println("Difference Between current DT and Packet DT is :" + Diff);

		if (diff < 11) {
			System.out.println("Last Schedule Day Data is generated on : " + diff);
			Telegram_Connect.Telegram_request("API In_memory Schedule Day Request is working in E2E .");
			Assert.assertEquals(true, true);
		} else {
			System.out.println("Last Schedule Day Data is generated on : " + diff);
			Telegram_Connect.Telegram_request("API In-memory Schedule Day Request is not working in E2E .");
			Assert.assertEquals(true, false);
		}
	}
	
	@Test(priority = 2)
	public void API_InMemory_WWW_Sch_Day() throws Exception {
		Inmemory_request.Postman_Api_Inmem_login(1);
		Inmemory_request.Postman_Api_Inmem_read_Sche_Day_Collection(1);

		String DT = Inmemory_request.DT;
		System.out.println("DT from Schedule Day Packet :" + DT);

		long diff = Inmemory_request.Calculate_Difference(DT);
		String Diff = String.valueOf(diff);
		System.out.println("Difference Between current DT and Packet DT is :" + Diff);

		if (diff < 11) {
			System.out.println("Last Schedule Day Data is generated on : " + diff);
			Telegram_Connect.Telegram_request("API In_memory Schedule Day Request is working in WWW .");
			Assert.assertEquals(true, true);
		} else {
			System.out.println("Last Schedule Day Data is generated on : " + diff);
			Telegram_Connect.Telegram_request("API In-memory Schedule Day Request is not working in WWW .");
			Assert.assertEquals(true, false);
		}
	}

}
