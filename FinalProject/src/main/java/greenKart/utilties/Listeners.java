package greenKart.utilties;

import java.io.IOException;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class Listeners extends ExtentReportNG implements ITestListener
{

	ExtentTest test;
	ExtentReports extent=ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS,"test passed");
		
		WebDriver driver =null;
		String testMethodName =result.getMethod().getMethodName();
		
		try {
			//driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			ITestContext context = result.getTestContext();
			driver=(WebDriver) context.getAttribute("WebDriver");
		} catch(Exception e)
		{
			
		}
		try {
			String random = getScreenShotPath(testMethodName,driver);
			extentTest.get().addScreenCaptureFromPath(random, result.getMethod().getMethodName()+random.substring(15));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("null ponter exception");
		}
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		WebDriver driver =null;
		String testMethodName =result.getMethod().getMethodName();
		
		try {
			//driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			ITestContext context = result.getTestContext();
			driver=(WebDriver) context.getAttribute("WebDriver");
		} catch(Exception e)
		{
			
		}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), result.getMethod().getMethodName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("null ponter exception");
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	
	
}
