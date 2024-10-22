package org.selenium.tests;

import org.selenium.annotations.FrameworkAnnotation;
import org.selenium.base.BaseTest;
import org.selenium.dataproviders.AuthorDataProvider;
import org.selenium.enums.AuthorType;
import org.selenium.enums.CategoryType;
import org.selenium.pages.CartPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.LoginPage;
import org.selenium.utils.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookCartTests extends BaseTest {

	private void performLogin() {
		String username = ConfigLoader.getInstance().getUsername();
		String password = ConfigLoader.getInstance().getPassword();
		LoginPage loginPage = new LoginPage(getDriver());
		HomePage homePage = loginPage
				.load()
				.clickOnLoginLink()
				.enterUsername(username)
				.enterPassword(password)
				.clickLoginButton();
		Assert.assertTrue(homePage.isHeadingMsgDisplayed());
		Assert.assertTrue(homePage.isUserLoggedIn());
	}

	@FrameworkAnnotation(author = { AuthorType.RAHUL }, category = { CategoryType.REGRESSION })
	@Test(dataProvider = "Author", dataProviderClass = AuthorDataProvider.class)
	public void searchAndAddTocart(String Author, String BookName) {
		performLogin();
		HomePage homePage = new HomePage(getDriver());
		homePage
		.searchItem(Author)
		.selectItem(BookName)
		.addToCart();
	}

	@FrameworkAnnotation(author = { AuthorType.RAHUL }, category = {CategoryType.REGRESSION })
	@Test(dependsOnMethods = "searchAndAddTocart")
	public void verifyProduct() {
		performLogin();
		HomePage homePage = new HomePage(getDriver());
		LoginPage loginPage = new LoginPage(getDriver());
		CartPage cartPage = homePage
				.goToCart();
		Assert.assertTrue(cartPage.areAllItemsDisplayed());
		String initialTotalPriceStr = cartPage.getInitialTotalPrice();
		
		
		cartPage
		.addItem1()
		.addItem2();
		
		String updatedTotalPriceStr = cartPage.getUpdatedTotalPrice();
		
		double initialPriceNumeric = Double.parseDouble(initialTotalPriceStr.replaceAll("[^\\d.]", ""));
		double updatedPriceNumeric = Double.parseDouble(updatedTotalPriceStr.replaceAll("[^\\d.]", ""));
		
		
		Assert.assertTrue(updatedPriceNumeric > initialPriceNumeric, "Updated total price should be greater than initial total price.");
	
		cartPage.clearCart();
		
		loginPage
		.clickOnLogoutLink()
		.clickOnLogoutButton();
		
		Assert.assertTrue(homePage.isHeadingMsgDisplayed());
		
	}
}
