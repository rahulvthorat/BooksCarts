package org.selenium.dataproviders;

import org.testng.annotations.DataProvider;

public final class LoginDataProvider {

	@DataProvider(name = "InvalidData")
	public Object[][] loginData1() {
		return new Object[][] { { "EmiRod", "Zxcvbnm1@" }, };
	}

}
