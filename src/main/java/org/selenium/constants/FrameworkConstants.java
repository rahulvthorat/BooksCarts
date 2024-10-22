
package org.selenium.constants;

import java.time.Duration;

import org.selenium.utils.ReportPathUtils;

public class FrameworkConstants {

	private FrameworkConstants() {}

	private static final String PROJECT_PATH = System.getProperty("user.dir");

	/* ICONS - START */
	public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
	public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
	public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";
	public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
	public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
	public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";
	public static final String ICON_NAVIGATE_RIGHT = "<i class='fa fa-arrow-circle-right' ></i>";
	public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
	public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";

	/* style="text-align:center;" */
	public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/rahulvthorat/";
	public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/rahulvthorat/";
	public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
			+ "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
	public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
			+ "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";
	public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";

	public static final String ICON_BROWSER_EDGE = "edge";
	public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
	public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";
    /* ICONS - END */

	public static final String ASSERTION_FOR_RESPONSE_STATUS_CODE = "Assertion for Response Status Code";
	public static final String ASSERTION_FOR_RESPONSE_HEADER = "Assertion for Response Headers";
	public static final String ASSERTION_FOR_RESPONSE_CUSTOM_FIELD = "Assertion for Response Custom Field";

	public static final String YES = "yes";
	public static final String NO = "no";

	private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(10);
	public static final int WAIT = 5;

	
	public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "/ExtentReports/";
	public static final String EXTENT_REPORT_NAME = "AutomationReport.html";
	private static String extentReportFilePath = "";

	/** Zip file of Extent Reports */
	public static final String Zipped_ExtentReports_Folder_Name = "ExtentReports.zip";

	private static final String Project_Name = "Automation Test Suite Report - Selenium Java Framework";

	public static String getProjectPath() {
		return PROJECT_PATH;
	}

	public static String getProjectName() {
		return Project_Name;
	}

	public static String getZippedExtentReportsFolderName() {
		return Zipped_ExtentReports_Folder_Name;
	}

	public static String getExtentReportFilePath() {

		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = ReportPathUtils.createExtentReportPath();
		}
		return extentReportFilePath;
	}

	public static Duration getExplicitWait() {
		return EXPLICIT_WAIT;
	}
	
}
