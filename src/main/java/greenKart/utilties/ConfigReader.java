package greenKart.utilties;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfigReader {
public static Properties prop;
public static WebElement Locator1;

public static String readProjectConfiguration(String key) {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(Paths.get(System.getProperty("user.dir"), "ConfigFiles", "ProjectConfiguration.properties").toFile());
			prop.load(fis);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
public static String[] readElementLocator(String key) {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(Paths.get(System.getProperty("user.dir"), "ConfigFiles", "ElementLocators.properties").toFile());
			prop.load(fis);
			return prop.getProperty(key).trim().split("-");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	public static WebElement getElement (String key1, WebDriver driver1) 
		{
			
			String[] path = ConfigReader.readElementLocator(key1);
			String LocatorType = path[0].toString();
			String LocatorPath = path[1].toString();
			//Locator1 = driver1.findElement(LocatorType+(+LocatorPath));
			if(LocatorType.equalsIgnoreCase("xpath"))
			{
				try {
					Locator1 = driver1.findElement(By.xpath(LocatorPath));
					if(Locator1.isDisplayed())
					{
						return Locator1;
					}
					else
					{
						System.out.println("Locator  "+key1+"  not displayed");
						return null;
					}
					
					}
				catch(Exception e2)
				{
					e2.printStackTrace();
					return null;
				}
			
			}
			else
			{
				System.out.println("Locator  "+key1+"  not found in properties file");
				return null;
			}
			
			
		}
	
		
	
}
