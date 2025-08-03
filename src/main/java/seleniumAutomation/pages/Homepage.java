package seleniumAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	
	@FindBy(css=".caret")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement registerOption;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	public Homepage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}

	public RegisterPage selectRegisterInOption()
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	public RegisterPage clickOnMyAccountAndSelectRegisterOption()
	{
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
		
	}
	public LoginPage clickOnMyAccountAndSelectLoginOption()
	{
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
}
