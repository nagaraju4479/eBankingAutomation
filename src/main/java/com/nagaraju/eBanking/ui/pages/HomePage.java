package com.nagaraju.eBanking.ui.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.PropertiesManager;
import com.nagaraju.eBanking.ui.engine.TestContext;

import enums.UserType;

public class HomePage extends BasePage{

	private TestContext context;
	
	public HomePage(TestContext context) {
		super(context);
		this.context = context;
	}
	
	
	private final Logger logger = LoggerFactory.getLogger(HomePage.class);
	private final By userMenuLink = By.xpath("(//a[contains(.,'Account Holder')])[2]");
	private final By cashierMenuLink = By.xpath("(//a[text()='Cashier'])[2]");
	private final By adminMenuLink = By.xpath("(//a[text()='Admin'])[2]");



	public void goToHomePage()
	{
		context.getDriver().get(PropertiesManager.getProperty("base.url"));
	}
	
	public void navigateToPage(UserType user) {
		switch (user) {
		case USER:
			context.getBot().click(userMenuLink);
			logger.info("Landed on {} login page" + user);
			break;
			
		case ADMIN:
			context.getBot().click(adminMenuLink);
			logger.info("Landed on {} login page" + user);
			break;
			
		case CASHIER:
			
			context.getBot().click(cashierMenuLink);
			logger.info("Landed on {} login page" + user);
			break;

		default:
			throw new IllegalArgumentException("Invalid user selected: " + user);
		}

	}
	
	

}
