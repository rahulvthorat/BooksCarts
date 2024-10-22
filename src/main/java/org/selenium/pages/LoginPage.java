package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.enums.WaitStrategy;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private final By loginlLink = By.xpath("//span[normalize-space()='Login']");
	private final By logoutlLink = By.xpath("//span[contains(text(),'EmiRod')]");
	private final By usernameField = By.cssSelector("#mat-input-0");
	private final By passwordField = By.xpath("/html[1]/body[1]/app-root[1]/div[1]/app-login[1]/div[1]/mat-card[1]/mat-card-content[1]/form[1]/mat-form-field[2]/div[1]/div[1]/div[2]/input[1]");
	private final By loginButton = By.xpath("/html[1]/body[1]/app-root[1]/div[1]/app-login[1]/div[1]/mat-card[1]/mat-card-content[1]/form[1]/mat-card-actions[1]/button[1]/span[2]");
	private final By logoutButton=By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/button[2]/span[1]");
	@Step
	public LoginPage load() {
		load("");
		return this;
	}
	
	public LoginPage clickOnLoginLink() {
		click(loginlLink, WaitStrategy.CLICKABLE, "Login link");
		return this;
		
	}
	
	public LoginPage clickOnLogoutLink() {
		click(logoutlLink, WaitStrategy.CLICKABLE, "Login link");
		return this;
		
	}
	
	public LoginPage enterUsername(String username) {
		sendKeys(usernameField, username, WaitStrategy.PRESENCE, "Email Field");
		return this;
	}

	public LoginPage enterPassword(String password) {
		sendKeys(passwordField, password, WaitStrategy.PRESENCE, "Password Field");
		return this;
	}

	public HomePage clickLoginButton() {
		click(loginButton, WaitStrategy.CLICKABLE, "Login Button");
		return new HomePage(driver);
	}
	
	public LoginPage clickOnLogoutButton() {
		click(logoutButton, WaitStrategy.CLICKABLE, "Logout");
		return this;
	}

	
	
}
