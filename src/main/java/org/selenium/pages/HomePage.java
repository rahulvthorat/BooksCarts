package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.components.MyHeader;
import org.selenium.enums.WaitStrategy;

public class HomePage extends BasePage {

	private MyHeader appHeader;

	public MyHeader getAppHeader() {
		return appHeader;
	}

	public HomePage(WebDriver driver) {
		super(driver);
		appHeader = new MyHeader(driver);
	}

	private final By searchBox = By.cssSelector("input[placeholder='Search books or authors']");
	private final By searchDropDown = By.xpath("//div[@role='listbox']/mat-option");
	private final By addtoCart=By.xpath("/html[1]/body[1]/app-root[1]/div[1]/app-home[1]/div[1]/div[2]/div[1]/div[1]/app-book-card[1]/mat-card[1]/mat-card-content[1]/app-addtocart[1]/button[1]/span[2]");
	private final By viewCart = By.xpath("/html[1]/body[1]/app-root[1]/app-nav-bar[1]/mat-toolbar[1]/mat-toolbar-row[1]/div[3]/button[2]/span[4]");
	
	public HomePage load() {
		load(" ");
		return this;
	}

	public boolean isHeadingMsgDisplayed() {
		return appHeader.getHeaderMsg();
	}

	public boolean isUserLoggedIn() {
		String pageTitle = getPageTitle();
		return pageTitle.contains("BookCart");
	}

	public HomePage searchItem(String itemName) {
		sendKeys(searchBox, itemName, WaitStrategy.VISIBLE, "Search Box");
		return this;
	}

	public HomePage selectItem(String bookName) {
	dropDown(driver, searchDropDown, bookName, WaitStrategy.CLICKABLE, "Searsh Box");
        return this;
    }
	
	public HomePage addToCart() {
		click(addtoCart, WaitStrategy.CLICKABLE, "Add To Cart");
		return this;
	}
	
	public CartPage goToCart() {
		jsClick(driver, viewCart, WaitStrategy.CLICKABLE, "View Cart");
		return new CartPage(driver);
	}

}
