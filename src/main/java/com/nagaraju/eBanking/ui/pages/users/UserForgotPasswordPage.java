package com.nagaraju.eBanking.ui.pages.users;

import org.openqa.selenium.By;

import com.nagaraju.eBanking.ui.engine.TestContext;

public class UserForgotPasswordPage {
	
	private TestContext context;
	
	public UserForgotPasswordPage(TestContext context) {
		this.context = context;
	}
	
	private final By emailField         = By.name("email");
	private final By mobileField        = By.name("mobile");
	private final By newPasswordField   = By.name("newpassword");
	private final By confirmPasswordField = By.name("confirmpassword");
	private final By resetButton        = By.name("submit");


}
