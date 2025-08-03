package seleniumAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailAddress;
	
	@FindBy(id="input-password")
	private WebElement validPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(css=".alert-danger")
	private WebElement invalidCredentialsWarningText;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterValidEmailAddress(String email)
	{
		emailAddress.sendKeys(email);
	}
	
	public void enterValidPassword(String password)
	{
		validPassword.sendKeys(password);
	}
	
	public Accountpage clickOnLogInButton()
	{
		loginButton.click();
		return new Accountpage(driver);
	}
	
	public Accountpage logInToApplication(String email,String password)
	{
		emailAddress.sendKeys(email);
		validPassword.sendKeys(password);
		loginButton.click();
		return new Accountpage(driver);
	}
	
	public boolean verifyWarningMessageWithInvalidCredentials(String expectedInvalidCredentialsWarningText)
	{
		String actualInvalidCredentialsWarningText = invalidCredentialsWarningText.getText();
		boolean bool = actualInvalidCredentialsWarningText.contains(expectedInvalidCredentialsWarningText);
		return bool;
	}

}
