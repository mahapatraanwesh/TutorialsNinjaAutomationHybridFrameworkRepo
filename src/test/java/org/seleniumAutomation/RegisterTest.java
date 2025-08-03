package org.seleniumAutomation;

import org.openqa.selenium.WebDriver;
import org.seleniumAutomation.BasePackage.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumAutomation.pages.Accountpage;
import seleniumAutomation.pages.Homepage;
import seleniumAutomation.pages.RegisterPage;
import seleniumAutomation.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerpage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void startUp() {
		driver = launchBrowserAndInitializeURL(prop.getProperty("browserName"));
		Homepage homepage = new Homepage(driver);
		registerpage = homepage.clickOnMyAccountAndSelectRegisterOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringByProvidingOnlyMandatoryFields() {

		Accountpage accountpage = registerpage.registrationWithOnlyMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.getEmailWithTimeStamp(), dataProp.getProperty("telephone"),
				dataProp.getProperty("password"));
		System.out.println(accountpage.verifyAccountCreationSuccessfulMessage());
		Assert.assertTrue(accountpage.verifyAccountCreationSuccessfulMessage()
				.equals(dataProp.getProperty("expectedConfirmationMessage")), "Account creation is not successful");

	}

	@Test(priority = 2)
	public void verifyRegisteringByProvidingAllFields() {

		Accountpage accountpage = registerpage.registrationWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.getEmailWithTimeStamp(), dataProp.getProperty("telephone"),
				dataProp.getProperty("password"));
		System.out.println(accountpage.verifyAccountCreationSuccessfulMessage());
		Assert.assertTrue(accountpage.verifyAccountCreationSuccessfulMessage()
				.equals(dataProp.getProperty("expectedConfirmationMessage")), "Account creation is not successful");

	}

	@Test(priority = 3)
	public void verifyWarningMessageDisplayed() {
		registerpage.clickOnContinueButton();
		boolean bool = registerpage.verifyWarningMessagesAreDisplayedWhenNoInputIsProvided(
				dataProp.getProperty("firstNameWarningMessageText"), dataProp.getProperty("lastNameWarningMessageText"),
				dataProp.getProperty("emailWarningMessageText"), dataProp.getProperty("telephoneWarningMessageText"),
				dataProp.getProperty("passwordWarningMessageText"), dataProp.getProperty("privacyWarningMessageText"));
		Assert.assertTrue(bool);
		System.out.println("All the warning messages are getting displayed correctly.");
	}

}
