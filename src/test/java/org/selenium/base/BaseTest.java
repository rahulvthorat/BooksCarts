
package org.selenium.base;

import java.io.File;

import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.driver.DriverManager;
import org.selenium.driver.DriverManagerFactory;
import org.selenium.enums.DriverType;
import org.selenium.listeners.AnnotationTransformer;
import org.selenium.listeners.ListenerClass;
import org.selenium.listeners.MethodInterceptor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Listeners({ AnnotationTransformer.class, ListenerClass.class, MethodInterceptor.class })
public class BaseTest {

	/* Class level -> Not Thread safe */
	/*
	 * Need to use ThreadLocal -> As Many test cases will try to access it at the
	 * time of parallel execution
	 */
	// protected WebDriver driver;
	// private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	protected WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	private void setDriver(WebDriver driver) {
		DriverManager.setDriver(driver);
	}

	/*
	 * @Optional -> You can run the test case individually directly from Java class
	 */
	@Parameters("browser")
	@BeforeMethod
	public synchronized void startDriver(@Optional String browser) {

		browser = setBrowserValue(browser);
		setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
		System.out.println("Current Thread info = " + Thread.currentThread().getId() + ", Driver = " + getDriver());
	}

	@Parameters("browser")
	@AfterMethod
	public synchronized void quitDriver(@Optional String browser, ITestResult result) throws IOException {

		takeScreenshotOnTestFailure(browser, result);
		getDriver().quit();

	}

	private void takeScreenshotOnTestFailure(String browser, ITestResult result) throws IOException {
		browser = setBrowserValue(browser);
		System.out.println("Current Thread info = " + Thread.currentThread().getId() + ", Driver = " + getDriver());

		if (result.getStatus() == ITestResult.FAILURE) {
			File destFile = new File("Screenshots" + File.separator + browser + File.separator
					+ result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName()
					+ ".png");
			takeScreenshot(destFile);
		}
	}

	private void takeScreenshot(File destFile) throws IOException {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, destFile);
	}

	@SuppressWarnings("unused")
	private void takeScreenshotUsingAshot(File destFile) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(getDriver());
		try {
			ImageIO.write(screenshot.getImage(), "PNG", destFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private String setBrowserValue(String browser) {
		/*
		 * System.getProperty("browser" -> This is for test execution using Maven
		 * Command Line file
		 */
		note();

		/* This is for test case execution individually from Java class */
		if (browser == null) {
			browser = "EDGE";
			System.out.println(
					"Test execution not done by Maven cmd or TestNG.xml file ->  setting the value: " + "EDGE");
		}

		/* This is for test case execution from Maven command line or testng.xml file */
		browser = System.getProperty("browser", browser);
		return browser;
	}

	private void note() {
		// String browser = System.getProperty("browser");
		/* DriverType.CHROME.toString() -> Default Browser */
		/*
		 * We can run the test cases even as TestNG suite - not mandatory to run from
		 * Maven command line
		 */
		/** This is for test execution using Maven command line */
		// String browser = System.getProperty("browser", DriverType.CHROME.toString());

		/** This is for test execution using testng.xml file */
		// String browser = browserFromTestNG;
	}

}
