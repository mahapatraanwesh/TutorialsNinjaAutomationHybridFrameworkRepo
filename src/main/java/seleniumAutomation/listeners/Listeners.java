package seleniumAutomation.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumAutomation.utils.ExtentReportGenerator;
import seleniumAutomation.utils.Utilities;

public class Listeners implements ITestListener{

	ExtentReports extentreport;
	ExtentTest extenttest;
	
	@Override
	public void onStart(ITestContext context) {
		extentreport = ExtentReportGenerator.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		extenttest = extentreport.createTest(result.getName());
		extenttest.log(Status.INFO, result.getName()+" is started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extenttest = extentreport.createTest(result.getName());
		extenttest.log(Status.PASS, result.getName()+" is executed successfully");

	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			//This line of code will provide the driver from the test to here for implementation.
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String destination = Utilities.captureScreenshot(driver, result.getName());
		//attach the screenshot to extent report
		extenttest.addScreenCaptureFromPath(destination);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, result.getName()+" is failed");
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.SKIP, result.getName()+" is skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution is completed");
		extentreport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\Extentreports\\extentreport.html";
		File extentReportPath = new File(pathOfExtentReport);
		try {
			//The below line of code will automatically open the extent report post test completion.
			Desktop.getDesktop().browse(extentReportPath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	

}
