package com.nagaraju.eBanking.ui.pages.admin;

import com.nagaraju.eBanking.ui.engine.BasePage;
import com.nagaraju.eBanking.ui.engine.TestContext;
import org.openqa.selenium.By;

public class AdminLoginPage extends BasePage {

	private  TestContext context;

	public AdminLoginPage(TestContext context) {
        super(context);
        this.context = context;
	}

	private final By searchUserAccountLink = By.xpath("//span[text()='Search Account']");
	private final By searchButton = By.xpath("//button[@id='submit']");







}
