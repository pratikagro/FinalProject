package greenKart.greenKart;

import static org.testng.Assert.fail;

import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import greenKart.assertions.Assertions;
import greenKart.base.base;
//import greenKart.datagenerators.TC01_BuyItems;
import greenKart.userActivities.KartPage;
import greenKart.utilties.ConfigReader;

public class TC01_BuyItems extends base {

	WebDriver driver1;

	private String KartItem = "";
	private String dummyData = "";

	@Factory(dataProvider = "KartPage")
	public TC01_BuyItems(String KartItem, String dummyData) {
		this.KartItem = KartItem;
		this.dummyData = dummyData;
		log.info("Factory is intialised");
	}

	@DataProvider(name = "KartPage")
	public static Object[][] testDataGenerator() throws Exception {

		FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet currentSheet = workbook.getSheet("KartPage");
		int numberOfData = currentSheet.getPhysicalNumberOfRows();
		XSSFRow row = currentSheet.getRow(0);
		int numberOfAttributes = row.getPhysicalNumberOfCells();
		Object testData[][] = new Object[numberOfData][numberOfAttributes];

		for (int i = 0; i <= numberOfData - 1; i++) {
			XSSFRow row1 = currentSheet.getRow(i);
			for (int j = 0; j <= numberOfAttributes - 1; j++) {
				testData[i][j] = row1.getCell(j).getStringCellValue();
			}
		}
		log.info("dataprovider is intialised");
		return testData;

	}

	@Test
	public void tc11_startGreenKart() {
		if (driver.getCurrentUrl().contains(ConfigReader.readProjectConfiguration("URL"))) {
			log.info("URL is already intialised");
			boolean alreadyPresent = true;
			Assert.assertTrue(alreadyPresent);
		} else {

			try {

				String projectURL = ConfigReader.readProjectConfiguration("URL");
				if (projectURL != null) {
					driver.get(projectURL);
					log.info("URL is intialised");
				} else {
					Assert.fail("URL not found");
					log.info("URL not found");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("URL navigation failed");

			}
		}
	}

	@Test
	public void tc12_searchItems() throws Exception {

		boolean result = KartPage.searchItems(KartItem, driver);
		if (result) {
			log.info("search operation 1 of tc12_searchItems is passed");
		} else {
			Assert.fail("search operation 1 of tc12_searchItems is failed");
		}

		boolean result1 = KartPage.clickSearch(driver);
		if (result1) {
			log.info("click operation 1 of tc12_searchItems is passed");
		} else {
			Assert.fail("click operation 1 of tc12_searchItems is  failed");
		}

		Thread.sleep(5000);
		boolean result3 = Assertions.validateFirstIndex(driver, KartItem);
		Assert.assertEquals(result3, true);

	}

	@Test
	public void tc13_addToKartItems() throws Exception {

		boolean result = KartPage.addToKart(driver, KartItem);
		if (result) {
			log.info("tc13_addToKartItems is passed");
		} else {
			Assert.fail("Add to kart operation failed");
		}

	}
}
