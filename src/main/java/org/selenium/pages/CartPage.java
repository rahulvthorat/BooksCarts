package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.enums.WaitStrategy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	//Book name
	private final By book1=By.cssSelector("a[ng-reflect-router-link='/books/details/,2']");
	private final By book2=By.cssSelector("a[ng-reflect-router-link='/books/details/,3']");
	private final By book3=By.cssSelector("a[ng-reflect-router-link='/books/details/,4']");
	private final By book4=By.cssSelector("a[ng-reflect-router-link='/books/details/,5']");
	
	
	//Book quantity
	private final By items1=By.xpath("//tbody[1]/tr[1]/td[4]/div[1]/div[3]/button[1]/span[4]");
	private final By items2=By.xpath("//tbody[1]/tr[2]/td[4]/div[1]/div[3]/button[1]/span[4]");
	
	//cart price
	private final By initialPrice=By.xpath("//strong[contains(text(),'₹1,201.00')]");
	private final By updatedPrice=By.xpath("//strong[contains(text(),'₹1,649.00')]");
	
    //clear cart
	private final By clearCart=By.xpath("//span[normalize-space()='Clear cart']");
	
	//verification
	public boolean areAllItemsDisplayed() {
	    boolean item1Displayed = isDisplayed(book1, WaitStrategy.PRESENCE, "Harry Potter and the Chamber of Secrets");
	    boolean item2Displayed = isDisplayed(book2, WaitStrategy.PRESENCE, "Harry Potter and the Prisoner of Azkaban");
	    boolean item3Displayed = isDisplayed(book3, WaitStrategy.PRESENCE, "Harry Potter and the Goblet of Fire");
	    boolean item4Displayed = isDisplayed(book4, WaitStrategy.PRESENCE, "Harry Potter and the Order of the Phoenix");

	    return item1Displayed || item2Displayed || item3Displayed || item4Displayed;
	}
	
	
	public CartPage addItem1() {
		click(items1, WaitStrategy.CLICKABLE, "Increased quantity Harry Potter and the Chamber of Secrets ");
		return this;
	}
	
	public CartPage addItem2() {
		click(items2, WaitStrategy.CLICKABLE, "Increased quantity Harry Potter and the Prisoner of Azkaban");
		return this;
	}
	
	
	public String getInitialTotalPrice() {
	    String initialPriceText = getText(initialPrice, WaitStrategy.VISIBLE, "Initial Total Price");
	    if (initialPriceText != null && !initialPriceText.isEmpty()) {
	        return initialPriceText;
	    } else {
	        return "0.00";
	    }
	}

	public String getUpdatedTotalPrice() {
	    String updatedPriceText = getText(updatedPrice, WaitStrategy.VISIBLE, "Updated Total Price");
	    if (updatedPriceText != null && !updatedPriceText.isEmpty()) {
	        return updatedPriceText;
	    } else {
	        return "0.00"; 
	    }
	}

	public CartPage clearCart() {
		click(clearCart, WaitStrategy.CLICKABLE, "Clear cart");
		return this;
	}
	
	
	
}
