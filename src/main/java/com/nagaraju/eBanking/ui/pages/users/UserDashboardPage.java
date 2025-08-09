package com.nagaraju.eBanking.ui.pages.users;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.Bot;
import com.nagaraju.eBanking.ui.engine.TestContext;

public class UserDashboardPage extends BasePage{
	
	private TestContext context;
	
	public UserDashboardPage(TestContext context) {
		super(context);
		this.context = context;
	}

	private final Logger logger = LoggerFactory.getLogger(UserDashboardPage.class);

	private final By newAccountLoginTitleMessage = By.xpath("//div[@class='alert alert-danger']");
	private final By payeeLink = By.xpath("//span[text()='Payee / Beneficiary']");
	private final By addPayeeLink = By.xpath("//a[text()='Add']");
	private final By accountOpeningLink = By.xpath("//span[text()='Account Openning']");

	

	public AccountDetailsPage goToAccountOpeningPage() {

		context.getBot().click(accountOpeningLink);	
		logger.info("landed on User Account details page");
		return new AccountDetailsPage(context);
	}

	public AddPayeePage goToAddPayeePage() {
		
		context.getBot().click(payeeLink)
		.click(addPayeeLink);
		return new AddPayeePage(context);
		
	}
	
	

}
