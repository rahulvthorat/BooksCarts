
package org.selenium.driver;

import org.selenium.enums.DriverType;

public class DriverManagerFactory {

	private DriverManagerFactory() {}

	public static DriverManager_OC getManager(DriverType driverType) {

		switch (driverType) {

		case CHROME: {
			return new DriverManagerChrome();
		}
		case FIREFOX: {
			return new DriverManagerFirefox();
		}
		case EDGE: {
			return new DriverManagerEdge();
		}
		case SAFARI: {
			return new DriverManagerSafari();
		}
		default:
			throw new IllegalArgumentException("Invalid Driver: " + driverType);
		}
	}

}
