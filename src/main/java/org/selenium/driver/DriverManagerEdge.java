
package org.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManagerEdge implements DriverManager_OC {

	@Override
	public WebDriver createDriver() {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		return driver;
	}

}
