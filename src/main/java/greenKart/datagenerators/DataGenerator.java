/*package greenKart.datagenerators;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class DataGenerator 
{
	
	@Factory(dataProvider="KartPage")
	public Object[] createInstances(String KartItem) 
	{
		
		return new Object[] { new TC01_BuyItems(KartItem) };
		
	}
	
	
	@DataProvider(name="KartPage")
	public static Object[][] testDataGenerator () throws Exception
	{
		
		FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet currentSheet = workbook.getSheet("KartPage");
		int numberOfData = currentSheet.getPhysicalNumberOfRows();
		XSSFRow row = currentSheet.getRow(0);
		int numberOfAttributes = row.getPhysicalNumberOfCells();
		Object testData[][] = new Object[numberOfData][numberOfAttributes];
		
		for (int i=0;i<=numberOfData;i++)
		{
			XSSFRow row1 = currentSheet.getRow(i);
			for (int j=1;j<=numberOfAttributes;j++)
			{
				testData[i][j] = row1.getCell(j).getStringCellValue();
			}
		}
		
		return testData;
		
	}
	
}
*/