package com.nagaraju.eBanking.ui.pages.admin;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.TestContext;
import org.openqa.selenium.By;

public class AccountHolderDetailsPage extends BasePage {
    private TestContext  context;
    public AccountHolderDetailsPage(TestContext context) {
        super(context);
        this.context = context;
            }

    private final By takeActionButton = By.xpath("//button[text()='Take Action']");
    private final By remarkTextArea = By.xpath("//textarea[@name='remark']");
    private final By selectStatusDropdown = By.xpath("//select[@id='status']");
    private final By updateButton = By.xpath("//button[@name='submit']");
    private final By initialAmountField = By.xpath("//input[@name='iniamt']");
    private final By popupWindowTitle = By.xpath("//h5[text()='Take Action']");
    private final By searchAccountLink = By.xpath("//span[text()='Search Account']");


    public AccountHolderDetailsPage approveAccount(String remark,String downDownValue,String amount)
    {



        context.getBot().waitForModalWindowVisible(popupWindowTitle)
        .enterText(remarkTextArea,remark)
        .selectValueFromDropdown(selectStatusDropdown,downDownValue)
        .enterText(initialAmountField,amount)
        .click(updateButton);

       return this;


    }

    public AccountHolderDetailsPage openTakeActionPopup()
    {
        context.getBot().click(takeActionButton);
        context.setPopupWindowTitle(context.getBot().getWebElement(popupWindowTitle).getText());
        return this;
    }

    public AccountHolderDetailsPage acceptSuccessPopupWindow()
    {
        context.getBot().acceptAlert();
        return this;
    }

    public AccountHolderDetailsPage rejectAccount(String remork,String downDownValue)
    {

        context.getBot().click(takeActionButton)
                .waitForModalWindowVisible(popupWindowTitle)
                .enterText(remarkTextArea,remork)
                .selectValueFromDropdown(selectStatusDropdown,downDownValue)
                .click(updateButton);
        return this;


    }

    public SearchAccountHolderPage gotToSearchUserAccount()
    {
        context.getBot().click(searchAccountLink);
        return new SearchAccountHolderPage(context);

    }



}