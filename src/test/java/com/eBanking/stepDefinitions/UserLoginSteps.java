package com.eBanking.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.eBanking.ui.engine.Common;
import com.nagaraju.eBanking.ui.engine.PropertiesManager;
import com.nagaraju.eBanking.ui.engine.TestContext;
import com.nagaraju.eBanking.ui.pages.HomePage;
import com.nagaraju.eBanking.ui.pages.users.RegistrationPage;
import com.nagaraju.eBanking.ui.pages.users.UserLoginPage;

import enums.UserType;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UserLoginSteps {
	
	 private TestContext context;
	 private static final Logger logger = LoggerFactory.getLogger(UserLoginSteps.class);
		
		UserLoginPage userLoginPage;
		HomePage homePage;
		RegistrationPage registrationPage;	
		
		
	public UserLoginSteps(TestContext context)
	{
		this.context = context;
		this.userLoginPage = new UserLoginPage(context);
		this.homePage= new HomePage(context);
		this.registrationPage= new RegistrationPage(context);
		
	}
	
	@Given("I am on home page")
	public void i_am_on_home_page() {
	homePage.goToHomePage();
		
	String actualHomePageTitle = context.getDriver().getTitle();
	String expectedHomePageTitle = PropertiesManager.getProperty("home.page.title").trim();
	assertEquals(expectedHomePageTitle, actualHomePageTitle);	
	logger.info("I am on Homepage now");
	 
	}
	@When("I navigate to user login page as a {}")
	public void iGoToUserLoginPage(String user) {
		context.setUserType(user);
		UserType userType =	UserType.valueOf(user.toUpperCase());
		homePage.navigateToPage(userType);
 	}
	
	
	@When("I login to application with valid credentials:")
	public void iLoginToApplicationWithValidCredentials(DataTable userLoginWithCredentials) {
		try {
		
			 var userLoginMap =   userLoginWithCredentials.asMap(String.class,String.class);
			userLoginPage.loginToUserAccount(userLoginMap.get("emailId"), userLoginMap.get("password"));

			
		} catch (Exception e) {
			logger.info("Unexpected error occurred ",e);
			throw e;
		}
	}
	@Then("I should successfully logged in to the application")
	public void iShouldSuccessfullyLoggedInToTheApplication() {

		switch (context.getUserType().toLowerCase())
		{
			case "user":
				String actualUserDashboardPageTitle = context.getDashboardPageTitle();
				String expectedUserDashboardPageTitle =PropertiesManager.getProperty("user.dashboard.page.title").trim();
				assertEquals(expectedUserDashboardPageTitle, actualUserDashboardPageTitle);
				logger.info("Landed on User dashoard page");
				break;

			case "admin":
				String actualAdminDashboardPageTitle = context.getDashboardPageTitle();
				String expectedAdminDashboardPageTitle =PropertiesManager.getProperty("admin.dashboard.page.title").trim();
				assertEquals(expectedAdminDashboardPageTitle, actualAdminDashboardPageTitle);

				logger.info("actual"+actualAdminDashboardPageTitle);
				logger.info("Expected"+expectedAdminDashboardPageTitle);
				break;
			case "cashier":
				String actualCashierDashboardPageTitle = context.getDashboardPageTitle();
				String expectedCashierDashboardPageTitle =PropertiesManager.getProperty("cashier.dashboard.page.title").trim();
				assertEquals(expectedCashierDashboardPageTitle, actualCashierDashboardPageTitle);


				logger.info("Landed on Cashier dashoard page");
				break;
			default:
				logger.info("Invalid User type");
				break;

		}
		

	   
	}
	
	@When("I go to registration page")
	public void iGoToRegistrationPage()
	{
		userLoginPage.goToRegistrationPage();
		logger.info("Landed on Registration page");
	}
	
	@When("I register user using below info:")
	public void iRegisterUserUsingBelowInfo(DataTable userRegistration)
	{
		var addUserData = userRegistration.asMap();
		registrationPage.registerTheUser(
				addUserData.get("firstName").replace("${random}", Common.random()),
				addUserData.get("lastName"),
				addUserData.get("emailAddress").replace("${random}", Common.random()),
				addUserData.get("mobile"),
				addUserData.get("password").replace("${password}", PropertiesManager.getProperty("user.password")));
		
	}
	
	@Then("I should see registration form sumbitted successfully")
	public void iShouldSeeRegistrationFormSubmittedSuccessfully()
	{
		String actual =  registrationPage.acceptTheRegistrationSuccessPopup();
		String expected = "You have successfully registered with us";
		assertEquals(expected, actual);
	}
	
	@When("I login to the applicaiton")
	public void loginToTheApplicaiton()
	{
		userLoginPage.loginToUserAccount(context.getRegisteredUserEmailId(), context.getRegisteredUserPassword());
	}


}
