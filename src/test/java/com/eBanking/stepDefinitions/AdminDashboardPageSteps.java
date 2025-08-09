package com.eBanking.stepDefinitions;

import com.nagaraju.eBanking.ui.engine.TestContext;
import com.nagaraju.eBanking.ui.pages.admin.AdminDashboardPage;
import io.cucumber.java.en.When;

public class AdminDashboardPageSteps {
    private TestContext context;
    private AdminDashboardPage adminDashboardPage;

    public AdminDashboardPageSteps(TestContext context)
    {
        this.context = context;
        this.adminDashboardPage = new AdminDashboardPage(context);
    }

    @When("I click on 'New Account Request' link")
    public void clickOnNewAccountRequestLink() {
        adminDashboardPage.navigateToNewAccountRequestPage();
    }
}
