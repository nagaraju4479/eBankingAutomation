package com.nagaraju.eBanking.ui.pages.admin;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.TestContext;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class NewAccountOpeningRequestsPage extends BasePage {
    private TestContext context;
    public NewAccountOpeningRequestsPage(TestContext context) {
        super(context);
        this.context = context;
    }


    private final By searchInputField = By.xpath("//input[@type='search']");
    private final By accountStatusLbl = By.xpath("(//span[text()='New Request'])[1");
    private final By viewAccountButton = By.xpath("(//a[text()='View '])[1]");
    private final By numberOfRecordsInTable = By.xpath("//tbody/tr[@class='odd' or @class='even']/td[2]");


    public NewAccountOpeningRequestsPage searchAccount(){

       List<WebElement> elements =  context.getDriver().findElements(numberOfRecordsInTable);

       if (elements.size() > 0)
       {
           for( WebElement element : elements)
           {
              String accountName = element.getText();

               context.getBot().enterText(searchInputField,accountName);
               context.setSearchUserAccount(accountName.split(" ")[0]);
              break;

           }
       } else {
           log.info("No record found");
       }
        return this;
    }



    public AccountHolderDetailsPage navigateToAccountHolderDetailsPage()
    {
        context.getBot().click(viewAccountButton);
        return new AccountHolderDetailsPage(context);

    }


}
