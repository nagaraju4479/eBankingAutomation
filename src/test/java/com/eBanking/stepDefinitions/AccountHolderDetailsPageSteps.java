package com.eBanking.stepDefinitions;

import com.nagaraju.eBanking.ui.engine.TestContext;
import com.nagaraju.eBanking.ui.pages.admin.AccountHolderDetailsPage;
import com.nagaraju.eBanking.ui.pages.admin.NewAccountOpeningRequestsPage;
import com.nagaraju.eBanking.ui.pages.admin.SearchAccountHolderPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Map;
@Log4j2
public class AccountHolderDetailsPageSteps {
    private TestContext context;
    private AccountHolderDetailsPage accountHolderDetailsPage;
    private SearchAccountHolderPage searchAccountHolderPage;

    public AccountHolderDetailsPageSteps(TestContext context)
    {
        this.context = context;
        this.accountHolderDetailsPage = new AccountHolderDetailsPage(context);
        this.searchAccountHolderPage = new SearchAccountHolderPage(context);
    }

    @When("I click on 'Take Action' button")
    public void clickOnTakeActionButton() {
        accountHolderDetailsPage.openTakeActionPopup();
    }

    @Then("I should see 'Take Action' model window popup opened")
    public void takeActionModalPopupWindowIsOpened() {
        String actualPopupWindowTitle = context.getPopupWindowTitle();
        String expectedPopupWindowTitle = "Take Action";
        //assertEquals(expectedPopupWindowTitle,actualPopupWindowTitle);
        log.info("Take action model widow opened successfully");

    }

    @When("I add below remarks:")
    public void addRemarks(DataTable dataTable) {

      try{
          var remarksMap = dataTable.asMap(String.class,String.class);

          accountHolderDetailsPage.approveAccount(
                  remarksMap.get("Remarks").trim(),
                  remarksMap.get("Status").trim(),
                  remarksMap.get("Initial Amount").trim()
          );

      }catch (Exception e) {
          log.info("Unexpected error occurred ",e);
          throw e;
      }


    }

    @Then("I should see User account has been approved successfully")
    public void verifyAccountApprovedMessage() {
        String actualSuccessAlertMessage = context.getBot().getAlertText();
        String expectedSuccessAlertMessage = "Remark has been updated";
        assertEquals(expectedSuccessAlertMessage,actualSuccessAlertMessage);
        log.info("User account has been approved successfully");
        accountHolderDetailsPage.acceptSuccessPopupWindow();
    }

    @When("I search the user account and verify the account status")
    public void searchAccountAndVerifyStatus() {

        accountHolderDetailsPage.gotToSearchUserAccount();
        searchAccountHolderPage.searchUserAccount(context.getSearchUserAccount());
        log.info("Account retrieved successfully");
    }

    @Then("I should see User account status is {string}")
    public void verifyAccountStatus(String expectedStatus) {
        expectedStatus = "Approved";
        String actualAccountStatus = context.getAccountCurrentStatus();
        assertEquals(expectedStatus,actualAccountStatus);
        log.info("User account now is :"+actualAccountStatus);

    }
}
