package com.nagaraju.eBanking.ui.engine;

import enums.UserType;
import org.openqa.selenium.WebDriver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestContext {
	
	private WebDriver driver;
	private Bot bot;	

	private String registeredUserEmailId;
	private String registeredUserPassword;
	private String acutalUserAccountSubmissionSuccessMessage;
	private String accountCurrentStatus;
	private String parentWindow;
	private String userType;
	private String popupWindowTitle;
	private String searchUserAccount;
	private String dashboardPageTitle;
	

}
