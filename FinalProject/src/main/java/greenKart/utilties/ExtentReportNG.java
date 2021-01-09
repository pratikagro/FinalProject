package greenKart.utilties;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import net.bytebuddy.utility.RandomString;

public class ExtentReportNG 
{

static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		
		String path =".\\ExtentReport\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pratik More");
		return extent;
		
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String random = UUID.randomUUID().toString().substring(0, 2);
		
		String destinationFile = ".\\"+testCaseName+random+".png";
		String destinationFile1 = ".\\ExtentReport\\"+testCaseName+random+".png";
		FileUtils.copyFile(source,new File(destinationFile1));
		return destinationFile;
	}
	
}
