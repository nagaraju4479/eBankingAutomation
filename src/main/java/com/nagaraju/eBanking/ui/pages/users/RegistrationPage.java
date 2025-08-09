package com.nagaraju.eBanking.ui.pages.users;

import org.openqa.selenium.By;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.TestContext;

public class RegistrationPage extends BasePage{
	
	private TestContext context;
	
	private By registrationPageTitle = By.xpath("//h1[contains(text(), 'Create an Account')]");
	private By firstNameFiled = By.xpath("//input[@name='fname']");
	private By lastNameField = By.xpath("//input[@name='lname']");
	private By emailAddressField = By.xpath("//input[@name='email']");
	private By mobileNumberField = By.xpath("//input[@name='mobno']");
	private By passwordField = By.xpath("//input[@name='password']");
	private By registerAccountButton = By.xpath("//button[@name='submit']");
	
	public RegistrationPage(TestContext context)
	{
		super(context);
		this.context = context;
	}
	
	public void registerTheUser(String firstName,String lastName,String email,String mobileNumber,String password)
	{
		context.getBot().click(firstNameFiled)
		.enterText(firstNameFiled, firstName)
		.enterText(lastNameField, lastName)
		.enterText(emailAddressField, email)
		.enterText(mobileNumberField, mobileNumber)
		.enterText(passwordField, password)
		.click(registerAccountButton);
		context.setRegisteredUserEmailId(email);
		context.setRegisteredUserPassword(password);
		
		
		
	}
	
	public String acceptTheRegistrationSuccessPopup()
	{
		String acutalRegistrationSuccessMessage = context.getBot().getAlertText();
		context.getBot().acceptAlert();
		return acutalRegistrationSuccessMessage;
		
	}
	

}
