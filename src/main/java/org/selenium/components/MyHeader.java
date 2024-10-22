package org.selenium.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.enums.WaitStrategy;
import org.selenium.pages.BasePage;

public class MyHeader extends BasePage {

	public MyHeader(WebDriver driver) {
		super(driver);

	}

	private final By headingBookCart = By.xpath("//span[normalize-space()='Book Cart']");

	/* Fluent Interface */
	public boolean getHeaderMsg() {
		isDisplayed(headingBookCart, WaitStrategy.PRESENCE, " Book Cart ");
		return true;
	}

}
