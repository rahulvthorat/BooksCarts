
package org.selenium.reports;

import static org.selenium.constants.FrameworkConstants.ICON_LAPTOP;
import static org.selenium.constants.FrameworkConstants.ICON_SOCIAL_GITHUB;
import static org.selenium.constants.FrameworkConstants.ICON_SOCIAL_LINKEDIN;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.selenium.constants.FrameworkConstants;
import org.selenium.enums.AuthorType;
import org.selenium.enums.CategoryType;
import org.selenium.utils.BrowserInfoUtils;
import org.selenium.utils.IconUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public final class ExtentReport {

	private ExtentReport() {}

	private static ExtentReports extent;

	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath())

					.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST,
							ViewName.CATEGORY, ViewName.AUTHOR, ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG })
					.apply();

			extent.attachReporter(spark);

			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle(FrameworkConstants.getProjectName() + " - ALL");
			spark.config().setReportName(FrameworkConstants.getProjectName() + " - ALL");
			spark.config().setProtocol(Protocol.HTTPS);
			spark.config().setEncoding("UTF-8");
			spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

			extent.setSystemInfo("Organization", "Groupon");
			extent.setSystemInfo("Employee",
					"<b> Rahul Thorat </b>" + " " + ICON_SOCIAL_LINKEDIN + " " + ICON_SOCIAL_GITHUB);
			extent.setSystemInfo("Domain", "Engineering (IT - Software)" + "  " + ICON_LAPTOP);
			extent.setSystemInfo("Skill", "Test Automation Engineer" + "  " + ICON_LAPTOP);
			extent.setSystemInfo("Environment", "QA");
		}
	}

	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTest(String testCaseName) {
		ExtentManager.setExtentTest(extent.createTest(IconUtils.getBrowserIcon() + " : " + testCaseName));
	}

	public static synchronized void addAuthors(AuthorType[] authors) {
		for (AuthorType author : authors) {
			ExtentManager.getExtentTest().assignAuthor(author.toString());
		}
	}

	public static synchronized void addCategories(CategoryType[] categories) {
		for (CategoryType category : categories) {
			ExtentManager.getExtentTest().assignCategory(category.toString());
		}
	}

	public static synchronized void addDevices() {
		ExtentManager.getExtentTest().assignDevice(BrowserInfoUtils.getBrowserInfo());
	}
}
