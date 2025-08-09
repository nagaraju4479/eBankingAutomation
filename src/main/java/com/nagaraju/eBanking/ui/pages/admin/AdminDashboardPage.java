package com.nagaraju.eBanking.ui.pages.admin;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.TestContext;
import org.openqa.selenium.By;

public class AdminDashboardPage extends BasePage {
    private TestContext context;
    public AdminDashboardPage(TestContext context) {
        super(context);
        this.context = context;
    }

    private final By newAccountRequestLink = By.xpath("//div[contains(normalize-space(text()), 'New Account Requests')]");
    private final By rejectedAccountRequestsLink = By.xpath("//div[contains(normalize-space(text()),'Rejected Account Requests')]");
    private final By cashierLink = By.xpath("//div[contains(normalize-space(text()),'Cashier')]");
    private final By accountHolderLink = By.xpath("//div[contains(normalize-space(text()),'Account Holder')]");
    private final By totalDispositLbl = By.xpath("//div[contains(normalize-space(text()),'Total Deposit Amount')]");
    private final By generateTransactionReportLink = By.xpath("//a[contains(normalize-space(.),'Generate Txn Report')]");


    public NewAccountOpeningRequestsPage navigateToNewAccountRequestPage()
    {
        context.getBot().click(newAccountRequestLink);
        return new NewAccountOpeningRequestsPage(context);
    }




}
