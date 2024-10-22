
package org.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManagerSafari implements DriverManager_OC {

	@Override
	public WebDriver createDriver() {
		WebDriver driver = new SafariDriver();
		driver.manage().window().maximize();
		return driver;
	}

}
