package org.seleniumAutomation;

import org.openqa.selenium.WebDriver;
import org.seleniumAutomation.BasePackage.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumAutomation.pages.Accountpage;
import seleniumAutomation.pages.Homepage;
import seleniumAutomation.pages.LoginPage;
import seleniumAutomation.utils.Utilities;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage loginpage;
	
	public LoginTest()
	{
		super();
	}
	@BeforeMethod
	public void startUp()
	{
		driver = launchBrowserAndInitializeURL(prop.getProperty("browserName"));
		Homepage homepage = new Homepage(driver);
		homepage.clickOnMyAccount();
		loginpage = homepage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test(priority=1,dataProvider="validTestCredentials")
	public void verifyLogInToApplicationUsingValidCredentials(String email, String password)
	{
		loginpage.enterValidEmailAddress(email);
		loginpage.enterValidPassword(password);
		Accountpage accountpage = loginpage.clickOnLogInButton();
		boolean bool = accountpage.verifyEditYourAccountInfoLinkIsDisplayed();
		
		Assert.assertTrue(bool);
		System.out.println(driver.getTitle());
		
		
	}
	//To provide data in hardcoded way using DataProvider
	@DataProvider
	public Object[][] validTestCredentials()
	{
//		Object [][] data = {{"testamFri_Aug_01_21_08_08_IST_2025@gmail.com","12345"},
//				{"testamFri_Aug_01_21_54_28_IST_2025@gmail.com","12345"}};
		
		Object[][] data = Utilities.getDataFromExcel("Login");
		return data;
		
	}
}
