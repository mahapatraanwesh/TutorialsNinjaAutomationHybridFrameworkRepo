package seleniumAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	WebElement firstNameField;

	@FindBy(id = "input-lastname")
	WebElement lastNameField;

	@FindBy(id = "input-email")
	WebElement emailField;

	@FindBy(id = "input-telephone")
	WebElement telephoneField;

	@FindBy(id = "input-password")
	WebElement passwordField;

	@FindBy(id = "input-confirm")
	WebElement confirmPasswordField;

	@FindBy(xpath = "//label[@class='radio-inline']//input[@value='1']")
	WebElement newsletterRadioButton;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement privacycheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueButton;

	@FindBy(css = "#input-firstname+div")
	WebElement firstNameWarningMessagelocator;

	@FindBy(css = "#input-lastname+div")
	WebElement lastNameWarningMessagelocator;

	@FindBy(css = "#input-email+div")
	WebElement emailFieldWarningMessagelocator;

	@FindBy(css = "#input-telephone+div")
	WebElement telephoneFieldWarningMessagelocator;

	@FindBy(css = "#input-password+div")
	WebElement passwordWarningMessagelocator;

	@FindBy(css = ".alert-danger")
	WebElement privacyWarningMessagelocator;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}

	public void enterEmailAddress(String emailID) {
		emailField.sendKeys(emailID);
		System.out.println(emailField.getAttribute("value"));
	}

	public void enterTelephoneNumber(String number) {
		telephoneField.sendKeys(number);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void confirmEnteredPassword(String password) {
		confirmPasswordField.sendKeys(password);
	}

	public void verifyNewsletterRadioButtonIsChecked() {
		System.out.println(newsletterRadioButton.isSelected() + ": Newsletter radio button is selected.");
	}

	public void verifyPrivacyCheckboxIsChecked() {
		System.out.println(privacycheckbox.isSelected() + ": Privacy checkbox is selected.");
	}

	public Accountpage clickOnContinueButton() {
		continueButton.click();
		return new Accountpage(driver);
	}

	public Accountpage registrationWithOnlyMandatoryFields(String firstName, String lastName, String emailID,
			String number, String password) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(emailID);
		System.out.println(emailField.getAttribute("value"));
		telephoneField.sendKeys(number);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		privacycheckbox.click();
		verifyPrivacyCheckboxIsChecked();
		continueButton.click();
		return new Accountpage(driver);

	}

	public Accountpage registrationWithAllFields(String firstName, String lastName, String emailID, String number,
			String password) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(emailID);
		System.out.println(emailField.getAttribute("value"));
		telephoneField.sendKeys(number);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		newsletterRadioButton.click();
		verifyNewsletterRadioButtonIsChecked();
		privacycheckbox.click();
		verifyPrivacyCheckboxIsChecked();
		continueButton.click();
		return new Accountpage(driver);

	}

	public boolean verifyWarningMessagesAreDisplayedWhenNoInputIsProvided(String expectedFirstNameWarningText,
			String expectedLastNameWarningText, String expectedEmailWarningText, String expectedTelephoneWarningText,
			String expectedPasswordWarningText, String expectedPrivacyWarningText) {

		String actualFirstNameWarningText = firstNameWarningMessagelocator.getText();
		boolean firstNameWarningMessageStatsus = actualFirstNameWarningText.contains(expectedFirstNameWarningText);
		String actualLastNameWarningText = lastNameWarningMessagelocator.getText();
		boolean lastNameWarningMessageStatus = actualLastNameWarningText.contains(expectedLastNameWarningText);
		String actualEmailWarningText = emailFieldWarningMessagelocator.getText();
		boolean emailWarningMessageStatus = actualEmailWarningText.contains(expectedEmailWarningText);
		String actualTelephoneWarningText = telephoneFieldWarningMessagelocator.getText();
		boolean telephoneWarningMessageStatus = actualTelephoneWarningText.contains(expectedTelephoneWarningText);
		String actualPasswordWarningText = passwordWarningMessagelocator.getText();
		boolean passwordWarningMessageStatus = actualPasswordWarningText.contains(expectedPasswordWarningText);
		String actualPrivacyWarningText = privacyWarningMessagelocator.getText();
		boolean privacyWarningMessageStatus = actualPrivacyWarningText.contains(expectedPrivacyWarningText);
		
		return firstNameWarningMessageStatsus && lastNameWarningMessageStatus && emailWarningMessageStatus && telephoneWarningMessageStatus && passwordWarningMessageStatus && privacyWarningMessageStatus;

	}
}
