package greenKart.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import greenKart.utilties.ConfigReader;

public class base {

	public WebDriver driver = null;
	public Properties prop;
	public int d = 0;
	public static Logger log = LogManager.getLogger(base.class.getName());
	//This is gitlab test
	@BeforeClass(alwaysRun = true)
	@BeforeMethod(alwaysRun = true)
	public void initializeDriver(ITestContext context) {
		try {
			if (driver == null) 
			{
				String browserName = ConfigReader.readProjectConfiguration("browser");
				System.out.println(browserName);

				if (browserName.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					// execute in chrome driver
					log.info("ChromeDriver is initialised");

				} else if (browserName.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					// firefox code
				} else if (browserName.equalsIgnoreCase("IE")) {
//	IE code
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();

				} else if (browserName.trim().equalsIgnoreCase("chromeHeadless")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					driver = new ChromeDriver(options);
				}

				driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				context.setAttribute("WebDriver", driver);
				
				
			}

			else 
			{
				log.info("ChromeDriver already is initialised");
			}
			
			if (d == 0)
			{
				d = 2;
				try {
					FileUtils.forceMkdir(new File("./ExtentReport"));
					FileUtils.forceMkdir(new File("./logs"));
					FileUtils.cleanDirectory(new File("./ExtentReport"));
					FileUtils.cleanDirectory(new File("./logs"));
					log.info("cleaned directories");
				} catch (IOException cleanupException) {
					log.warn("Could not clean report/log directories", cleanupException);
				}
			}
			else
			{
				log.info("No need to clean directories");
			}
			
		} catch (Exception e) {
			log.error("Error initializing WebDriver", e);
			throw new RuntimeException(e);
		}
	}

	@AfterClass
	public void closeeDriver() {
		try {
			
			driver.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
