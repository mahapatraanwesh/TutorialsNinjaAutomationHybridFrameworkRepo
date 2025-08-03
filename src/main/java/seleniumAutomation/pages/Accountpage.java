package seleniumAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accountpage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement actualConfirmationMessage;
	
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountInfoLink;
	
	public Accountpage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String verifyAccountCreationSuccessfulMessage()
	{
		String actualConfirmationMessageText = actualConfirmationMessage.getText();
		return actualConfirmationMessageText;
		
	}
	
	public boolean verifyEditYourAccountInfoLinkIsDisplayed()
	{
		
		boolean bool = editYourAccountInfoLink.isDisplayed();
		return bool;
		
	}

}
