package greenKart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.sl.draw.geom.Path;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import greenKart.utilties.ConfigReader;

public class base {

	public WebDriver driver = null;
	public Properties prop;
	public int d = 0;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeClass
	public void initializeDriver(ITestContext context) {
		try {
			if (driver == null) 
			{
				String browserName = ConfigReader.readProjectConfiguration("browser");
				System.out.println(browserName);

				if (browserName.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
					driver = new ChromeDriver();
					// execute in chrome driver
					log.info("ChromeDriver is initialised");

				} else if (browserName.equals("firefox")) {
					System.setProperty("webdriver.geckoDriver.driver", ".\\driver\\firefoxDriver.exe");
					driver = new FirefoxDriver();
					// firefox code
				} else if (browserName.equals("IE")) {
//		IE code
					System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();

				} else if (browserName.trim().equalsIgnoreCase("chromeHeadless")) {
					System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("headless");
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
				FileUtils.cleanDirectory(new File("./ExtentReport"));
				FileUtils.cleanDirectory(new File("./logs"));
				log.info("cleaned directories");
			}
			else
			{
				log.info("No need to clean directories");
			}
			
		} catch (Exception e) {

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
