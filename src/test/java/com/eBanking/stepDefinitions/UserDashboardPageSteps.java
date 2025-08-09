package com.eBanking.stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.eBanking.ui.engine.PropertiesManager;
import com.nagaraju.eBanking.ui.engine.TestContext;
import com.nagaraju.eBanking.ui.pages.HomePage;
import com.nagaraju.eBanking.ui.pages.users.RegistrationPage;
import com.nagaraju.eBanking.ui.pages.users.UserDashboardPage;
import com.nagaraju.eBanking.ui.pages.users.UserLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UserDashboardPageSteps {
	private static final Logger logger = LoggerFactory.getLogger(UserDashboardPageSteps.class);
	private TestContext context;
	private UserLoginPage userLoginPage;
	private HomePage homePage;
	private RegistrationPage registrationPage;
	private UserDashboardPage dashboardPage;
	
	public UserDashboardPageSteps(TestContext context)
	{
		this.context = context;
		this.userLoginPage = new UserLoginPage(context);
		this.homePage= new HomePage(context);
		this.registrationPage= new RegistrationPage(context);
		this.dashboardPage = new UserDashboardPage(context);
	}
	
	
	
	@When("I navigate to Home page")
	public void iNavigateToHomePage()
	{
	
		context.getDriver().navigate().to(PropertiesManager.getProperty("base.url"));
		logger.info("Navigated to home page");
		
	}
	
	@When("I login with valid credentials")
	public void iLoginWithValidCredentials()
	{
		userLoginPage.loginToUserAccount(context.getRegisteredUserEmailId(), context.getRegisteredUserPassword());
		logger.info("User loggedin successfully");
	}
	
	@When("I go to Account opening page")
	public void iGoToAccountOpeningPage()
	{
		dashboardPage.goToAccountOpeningPage();
		
	}
	
	@Given("I am on Add Payee page")
	public void iAmOnAddPayeePage()
	{
		dashboardPage.goToAddPayeePage();
	}
	

}
