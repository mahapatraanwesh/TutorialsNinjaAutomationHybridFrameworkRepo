package seleniumAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportGenerator {

	public static ExtentReports generateExtentReport() {

		File reportfile = new File(System.getProperty("user.dir") + "\\test-output\\Extentreports\\extentreport.html");
		ExtentReports extentreport = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportfile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Automation Test Results");
		sparkReporter.config().setDocumentTitle("Test Automation Run Results");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentreport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\seleniumAutomation\\config\\config.properties");
		try {
		FileInputStream fisConfigProp = new FileInputStream(configFile);
		configProp.load(fisConfigProp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		extentreport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentreport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentreport.setSystemInfo("Username", System.getProperty("user.name"));
		
		return extentreport;
		
		
	}

}
