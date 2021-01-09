package greenKart.userActivities;

import static org.testng.Assert.assertThrows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Assert.ThrowingRunnable;

import greenKart.utilties.ConfigReader;

public class KartPage {

	public static boolean searchItems(String itemName, WebDriver driver3) throws Exception
	{
		
			ConfigReader.getElement("KartPage_search", driver3).clear();
			ConfigReader.getElement("KartPage_search", driver3).sendKeys(itemName);
			
		return true;
	}
		
	public static boolean clickSearch(WebDriver driver3) throws Exception
	{
		
		ConfigReader.getElement("KartPage_SearchButton", driver3).click();
		return true;
		
	}
	
	public static boolean addToKart(WebDriver driver3, String kartItem) throws Exception
	{
		
			WebElement limit;
			WebElement limitText;
			WebElement limitClick;
		//WebElement w = ConfigReader.getElement("KartPage_AddToKartButton", driver3);
			List<WebElement> wl = driver3.findElements(By.className("product"));
			int j = wl.size();
			if(j>=2) 
			{
			for(int i=0;i<=j-2;i++) 
			{	limit = wl.get(i);
				limitText = limit.findElement(By.xpath(".//h4"));
				//System.out.println(limitText.getText());
				if(limitText.getText().contains(kartItem))
				{
					limitClick = limit.findElement(By.xpath(".//div[3]/button"));
					limitClick.click();
				}
					
			}
			
			}
			else
			{
				limit = wl.get(0);
				limitText = limit.findElement(By.xpath(".//h4"));
				limitClick = limit.findElement(By.xpath(".//div[3]/button"));
				limitClick.click();
				
			}
			return true;
	}
		
	}

