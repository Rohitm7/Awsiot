package itestListener_Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestResult;

import Mail_Utility.Sending_Mail_html;

public class Isuitelistener_test implements ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("After Excecution Sending mail through ISuiteListener");
		List<String> list=new ArrayList<String>();  
		String MailData = null ;
 

		Map<String, ISuiteResult> results = suite.getResults();
		for (String key : results.keySet()) {

			ISuiteResult con = results.get(key);

			int totaltestcases = con.getTestContext().getAllTestMethods().length;
			System.out.println("totaltestcases :" + totaltestcases);

			int passtestcases = con.getTestContext().getPassedTests().size();
			System.out.println("passtestcases :" + passtestcases);

			int failedtestcases = con.getTestContext().getFailedTests().size();
			System.out.println("failedtestcases :" + failedtestcases);

			int skippedtestcases = con.getTestContext().getSkippedTests().size();
			System.out.println("skippedtestcases :" + skippedtestcases);

			int percentage = (passtestcases * 100) / totaltestcases;
			System.out.println("PASS PERCENTAGE : " + percentage + "%");
			
			Set<ITestResult> failedTestResults = con.getTestContext().getFailedTests().getAllResults();
			for (ITestResult failedTestResult : failedTestResults) {
				// To get failed test parameters
				Object[] failedTestParameters = failedTestResult.getParameters();
				// To get method name (Not sure on this, getMethod() also may work)
				String FailedMethodName = failedTestResult.getName(); // (There is also //.getTestName())
				// To get exception (Not sure about this)
				Throwable exception = failedTestResult.getThrowable();
				// You can do whatever you want with these
				//System.out.println("Failed TestName :" + FailedMethodName);
				list.add("Failed TestName :" + FailedMethodName);

			}
			
			Set<ITestResult> passedtestcases = con.getTestContext().getPassedTests().getAllResults();
			for (ITestResult passedTestResult : passedtestcases) {
				// To get failed test parameters
				Object[] passedTestParameters = passedTestResult.getParameters();
				// To get method name (Not sure on this, getMethod() also may work)
				String passedTestName = passedTestResult.getName(); // (There is also //.getTestName())
				// To get exception (Not sure about this)
				Throwable exception = passedTestResult.getThrowable();
				// You can do whatever you want with these
				//System.out.println("Passed TestName :" + passedTestName);
				list.add("Passed TestName :" + passedTestName);
			}
			
			Set<ITestResult> skipedtestcases = con.getTestContext().getSkippedTests().getAllResults();
			for (ITestResult skipTestResult : skipedtestcases) {
				// To get failed test parameters
				Object[] skipTestParameters = skipTestResult.getParameters();
				// To get method name (Not sure on this, getMethod() also may work)
				String skipTestName = skipTestResult.getName(); // (There is also //.getTestName())
				// To get exception (Not sure about this)
				Throwable exception = skipTestResult.getThrowable();
				// You can do whatever you want with these
				//System.out.println("Skipped TestName :" + skipTestName);
				list.add("Skipped TestName :" + skipTestName);
			}
		}
		for (String TestResult : list) {
			System.out.println("TestResults :"+TestResult+",");
			MailData +=TestResult+",";
		}
		MailData=MailData.substring(4);
		Sending_Mail_html.main(MailData);
	}


}
