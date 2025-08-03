package seleniumAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_TIME_OUT = 10;
	public static final int PAGE_LOAD_TIME = 5;

	public static String getEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		String emailAddress = "testam" + timeStamp + "@gmail.com";
		return emailAddress;

	}

	public static Object[][] getDataFromExcel(String sheetName) {
		XSSFWorkbook workBook = null;// initialization of the workbook
		// Fetch the location where the excel sheet is stored
		File excelFilePath = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\seleniumAutomation\\testData\\TutorialsNinja Test Data.xlsx");
		try {
			// store the excel sheet location path to input stream
			FileInputStream excelFis = new FileInputStream(excelFilePath);
			// provide the input stream variable to workbook
			workBook = new XSSFWorkbook(excelFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// code to get the sheetname
		XSSFSheet sheet = workBook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();// count of the number of rows
		int cols = sheet.getRow(0).getLastCellNum();// count of the number of columns

		Object[][] data = new Object[rows][cols]; // created object to fetch the test data and store
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);// will ignore the 1st row in the sheet as those are headings
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				switch (celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BLANK:
					break;
				case BOOLEAN:
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				default:
					System.out.println("Cell type is not matching with the provided cases");
					break;

				}
			}

		}
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver,String testName)
	{
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destination));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return destination;
		
	}
}
