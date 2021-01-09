package greenKart.assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import greenKart.utilties.ConfigReader;

public class Assertions {
	
	public static boolean validateFirstIndex (WebDriver driver2, String searchItem) throws Exception{
		boolean result = false;
		
					
				WebElement checkResult = ConfigReader.getElement("KartPage_productName", driver2);
				System.out.println(checkResult.getText());
					if(checkResult.getText().contains(searchItem))
							{
								
							result = true;
								
							}
					
			
		
			
			return result;	
	}
	
}
