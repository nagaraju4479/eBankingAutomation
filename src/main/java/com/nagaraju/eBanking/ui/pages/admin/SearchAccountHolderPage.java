package com.nagaraju.eBanking.ui.pages.admin;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.SynchronizationManager;
import com.nagaraju.eBanking.ui.engine.TestContext;
import org.openqa.selenium.By;

public class SearchAccountHolderPage extends BasePage {

    private TestContext context;
    private SynchronizationManager synchronizationManager;
    public SearchAccountHolderPage(TestContext context) {
        super(context);

        this.context = context;
        this.synchronizationManager = new SynchronizationManager(context.getDriver());

    }
    private final By searchAccountField = By.xpath("//input[@id='searchdata']");
    private final By submitButton = By.xpath("//button[@id='submit']");
    private final By accountStatusButtonText = By.xpath("//span[contains(@class,'badge-success')]");

    public SearchAccountHolderPage searchUserAccount(String accountName)
    {
        context.getBot().enterText(searchAccountField,accountName)
                .click(submitButton);
        synchronizationManager.waitForTheElementToBeVisible(searchAccountField);
        context.setAccountCurrentStatus(context.getBot().getWebElement(accountStatusButtonText).getText());
        return this;

    }
}
