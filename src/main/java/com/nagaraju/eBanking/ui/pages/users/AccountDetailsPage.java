package com.nagaraju.eBanking.ui.pages.users;

import org.openqa.selenium.By;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.PropertiesManager;
import com.nagaraju.eBanking.ui.engine.TestContext;

public class AccountDetailsPage extends BasePage{
	
	private TestContext context;
	
	public AccountDetailsPage(TestContext context) {
		super(context);
		this.context = context;
	}

	//private final By = By.xpath("");
	private final By accountDetailsPageName = By.xpath("//h3[text()='Account Details']");
	private final By addressProofDropdown = By.xpath("//select[@name='addproof']");
	private final By addressProofIdField= By.xpath("//input[@name='addpidnum']");
	private final By addressProofFileUpload= By.xpath("//input[@name='attaddproof']");
	private final By panCardFileUpload= By.xpath("//input[@name='uplpancard']");
	private final By panCardNumberField= By.xpath("//input[@name='pancardnum']");
	private final By addressField= By.xpath("//textarea[@name='address']");
	private final By dobCalendar= By.xpath("//input[@type='date']");
	private final By selectDateToday= By.xpath("//input[@id='dob']");
	private final By acceptTandCCheckBox= By.xpath("//input[@id='tandc']");
	private final By accountOpeningSubmit= By.xpath("//button[@id='submit']");
	private final By accountCurrentStatus = By.xpath("//span[contains(@class,'badge')]");
	
	
	private String accountDetailsUploadedSuccessMessage = "Details succesfully submitted.";
	
	public AccountDetailsPage addAccountDetails(String addressProofIdNumber , String panCard,String address,String dob)
	{
		context.getBot().selectValueFromDropdown(addressProofDropdown, "Adhar Card")
		.enterText(addressProofIdField,addressProofIdNumber )
		.uploadFile(addressProofFileUpload, PropertiesManager.getProperty("user.upload.address.proof.path"))
		.uploadFile(panCardFileUpload, PropertiesManager.getProperty("user.upload.pancard.path"))
		.enterText(panCardNumberField, panCard)
		.enterText(addressField, address)
		.enterText(dobCalendar, dob)
		.click(acceptTandCCheckBox)
		.click(accountOpeningSubmit);
		
		return this;
		
		
	}
	
	public AccountDetailsPage acceptAccountSubmition()
	{
		String acutalUserAccountSubmissionSuccessMessage = context.getBot().getAlertText();
		context.setAcutalUserAccountSubmissionSuccessMessage(acutalUserAccountSubmissionSuccessMessage);
		context.getBot().acceptAlert();
		context.setAccountCurrentStatus(context.getBot().getWebElement(accountCurrentStatus).getText());
		return this;
		
	}
	
	
	

}
