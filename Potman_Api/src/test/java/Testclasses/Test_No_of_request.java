package Testclasses;

import Postman_Api_Request.API_Parameter_request;

public class Test_No_of_request {
	public static void main(String[] args) throws Exception {
		for(int i=0;i<550;i++) {
		
			API_Parameter_request.post("https://e2e.cloudtesla.com:8006/api_inmemory", "{\"key\":\"OGQr1PegCcRcIWMq\",\"request_type\": \"read_collection\",\"collection_name\": \"MG2GFRP1_schedule_hour\",\"limit\": 1,\"query\": {}}",0);
			
			System.out.println("No of Request :"+i);
		}
		
	}

}
