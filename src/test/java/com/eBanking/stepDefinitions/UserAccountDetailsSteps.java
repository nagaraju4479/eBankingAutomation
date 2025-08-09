package com.eBanking.stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.eBanking.ui.engine.Common;
import com.nagaraju.eBanking.ui.engine.TestContext;
import com.nagaraju.eBanking.ui.pages.users.AccountDetailsPage;
import com.nagaraju.eBanking.ui.pages.users.UserDashboardPage;

import enums.AccountStatus;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAccountDetailsSteps {
	private final Logger logger = LoggerFactory.getLogger(UserAccountDetailsSteps.class);
	private TestContext context;
	private AccountDetailsPage accountDetailsPage;
	
	public UserAccountDetailsSteps(TestContext context)
	{
		this.context = context;
		this.accountDetailsPage = new AccountDetailsPage(context);
		
	}
	
	@When("I fill the account details with below deatils:")
	public void iFillTheAccountDetailsWithBelowDetails(DataTable userAccountDetails)
	{
		Map<String, String> userAccountMap =  userAccountDetails.asMap();
		accountDetailsPage.addAccountDetails(
				userAccountMap.get("Address proof ID Number").trim().replace("${aadharCard}" , Common.generateRandomAadharNumber())
				
				, userAccountMap.get("PAN Card Number").trim().replace("${panNumber}", Common.generatePanNumber()),
				userAccountMap.get("Address").trim(), 
				userAccountMap.get("Date of Birth").trim());
		
	}
	
	@Then("I should see success message as {}")
	public void iShouldSeeSuccessMessageAs(String expectedSuccessMessage)
	{
		accountDetailsPage.acceptAccountSubmition();
		assertEquals(expectedSuccessMessage, context.getAcutalUserAccountSubmissionSuccessMessage());
		logger.info("User account details sent to Admin");
		assertEquals(AccountStatus.NEW_REQUEST.getStatus(), context.getAccountCurrentStatus());
		logger.info("Account Expected status is :"+AccountStatus.NEW_REQUEST.getStatus());
		logger.info("Account Acutal status is :"+AccountStatus.NEW_REQUEST.getStatus());
		
	}
	

}
