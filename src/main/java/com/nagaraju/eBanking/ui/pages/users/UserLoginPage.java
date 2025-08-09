package com.nagaraju.eBanking.ui.pages.users;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.TestContext;



public class UserLoginPage  extends BasePage {
	
	
	public UserLoginPage(TestContext context) {
		super(context);
		this.context = context;
	
	}

	private static final Logger logger = LoggerFactory.getLogger(UserLoginPage.class);
	private TestContext context;

	private  final By userEmailIDTxtField = By.xpath("//input[@id='email']");
	private  final  By userPasswordTxtField = By.xpath("//input[@id='password']");
	private  final By userSubmitButton = By.xpath("//button[@type='submit']");
	private  final By userRegistrationLink = By.xpath("//a[text()='Create an Account!']");
	private  final By userForgotPasswordLink = By.xpath("//a[text()='Forgot Password?']");
	
	


	public  UserDashboardPage loginToUserAccount(String emailID , String password)
	{
		try {
			context.getBot().enterText(userEmailIDTxtField,emailID);	
			context.getBot().enterText(userPasswordTxtField, password)
			.click(userSubmitButton);
			context.setDashboardPageTitle(context.getDriver().getTitle());
			
		} catch (Exception e) {
			logger.error("Unexpected error occured while login "+ e.getMessage());
		}
		return new UserDashboardPage(context);	
		
	}
	
	public RegistrationPage goToRegistrationPage()
	{
		context.getBot().click(userRegistrationLink);
		return new RegistrationPage(context);
	}
	

}
