
package org.selenium.java_Mail_API;

import org.selenium.constants.FrameworkConstants;

/**
 * Data for Sending EMail after execution
 */
public class EmailConfig {

	protected EmailConfig() {

	}

	public static final String SERVER = "gmail.com";
	public static final String PORT = "000";

	public static final String FROM = "@gmail.com";
	public static final String PASSWORD = "********";

	/* "**********@gmail.com", */
	public static final String[] TO = { "@gmail.com" };
	public static final String SUBJECT = FrameworkConstants.getProjectName();
}