package com.eBanking.stepDefinitions;

import com.nagaraju.eBanking.ui.engine.PropertiesManager;
import com.nagaraju.eBanking.ui.engine.TestContext;
import com.nagaraju.eBanking.ui.pages.admin.NewAccountOpeningRequestsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class NewAccountOpeningRequestPageSteps {
    private TestContext context;
    private NewAccountOpeningRequestsPage newAccountOpeningRequestsPage;

    public NewAccountOpeningRequestPageSteps(TestContext context)
    {
        this.context = context;
        this.newAccountOpeningRequestsPage = new NewAccountOpeningRequestsPage(context);
    }

    @Then("I should navigated to New Account Request page")
    public void verifyNavigatedToNewAccountRequestPage() {
        String actualTitle = context.getDriver().getTitle();
        String expectedTitle = PropertiesManager.getProperty("new.account.request.page.title");
        assertEquals(expectedTitle, actualTitle);
    }
    @When("I search an user account")
    public void searchUserAccount() {
       newAccountOpeningRequestsPage.searchAccount();
    }

    @When("I view the user account")
    public void viewTheUserAccount() {

        newAccountOpeningRequestsPage.navigateToAccountHolderDetailsPage();
    }

    @Then("I should navigated to Account Holder Details page")
    public void verifyNavigatedToAccountHolderDetailsPage() {

        log.info(context.getDriver().getTitle());
    }




}
