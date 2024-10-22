package org.selenium.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Action {
	
	private Action() {}

	/**
	 * Takes a screenshot of a specific WebElement. This method captures a
	 * screenshot of the specified WebElement.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to capture.
	 * @return A Base64-encoded string representing the screenshot of the
	 *         WebElement.
	 */
	public static String takeElementScreenShot(WebDriver driver, WebElement element) {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String elementName = element.getTagName();
		String trg = System.getProperty("user.dir") + "\\ScreenShots\\" + elementName + "_" + date + ".png";

		try {
			FileUtils.copyFile(src, new File(trg));
		} catch (Exception e) {
			e.getMessage();
		}

		return trg;
	}

	/**
	 * Retrieves the title of the current web page. This method returns the title of
	 * the web page currently loaded in the browser.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @return The title of the current web page as a String.
	 */
	public static String getTitle(WebDriver driver) throws Throwable {
		boolean flag = false;
		String text = driver.getTitle();
		if (flag) {
			System.out.println("PAGE TITLE IS: \"" + text + "\"");
		} else {
			System.out.println("PAGE TITLE NOT MATCH: \"" + text + "\"");
		}
		return text;
	}

	/**
	 * Retrieves the current URL of the web page. This method returns the URL of the
	 * web page currently loaded in the browser.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @return The current URL of the web page as a String.
	 */
	public static String getCurrentURL(WebDriver driver) throws Throwable {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("CURRENT URL IS: \"" + text + "\"");
		} else {
			System.out.println("URL NOT MATCH: \"" + text + "\"");
		}
		return text;
	}

	/**
	 * Clicks on a WebElement. This method performs a click action on the specified
	 * WebElement.
	 * 
	 * @param element The WebElement to click on.
	 */
	public static boolean click1(WebElement locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Able to click on: \"" + locatorName + "\"");
			} else {
				System.out.println("Unable to click on: \"" + locatorName + "\"");
			}
		}

	}

	/**
	 * Checks if a WebElement is displayed. This method determines whether the
	 * specified WebElement is visible and displayed on the web page.
	 * 
	 * @param element The WebElement to check.
	 * @return True if the WebElement is displayed, false otherwise.
	 */
	public static boolean isDisplayed(WebDriver driver, WebElement ele) throws Throwable {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}

	/**
	 * Checks if a WebElement is selected. This method determines whether the
	 * specified WebElement is selected, typically used for checkboxes and radio
	 * buttons.
	 * 
	 * @param element The WebElement to check.
	 * @return True if the WebElement is selected, false otherwise.
	 */
	public static boolean isSelected(WebDriver driver, WebElement ele) throws Throwable {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		} else {
			System.out.println("Not selected ");
		}
		return flag;
	}

	/**
	 * Checks if a WebElement is enabled. This method determines whether the
	 * specified WebElement is enabled for interaction.
	 * 
	 * @param element The WebElement to check.
	 * @return True if the WebElement is enabled, false otherwise.
	 */
	public static boolean isEnabled(WebDriver driver, WebElement ele) throws Throwable {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		} else {
			System.out.println("Not Enabled ");
		}
		return flag;
	}

	/**
	 * Scrolls the page until the specified WebElement is visible. This method
	 * scrolls the page vertically until the specified WebElement is visible in the
	 * viewport.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to scroll to.
	 */
	public static void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}

	/**
	 * Clicks on a WebElement using the Actions class. This method performs a click
	 * action on the specified WebElement using the Actions class.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to click on.
	 */
	public static void click(WebDriver driver, WebElement ele) throws Throwable {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();
	}

	/**
	 * Finds a single WebElement using a locator strategy. This method finds and
	 * returns a single WebElement matching the specified locator strategy.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param locator The locator strategy used to find the WebElement.
	 * @return The found WebElement.
	 */
	public static boolean findElement(WebDriver driver, WebElement ele) throws Throwable {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}

	/**
	 * Types text into a WebElement. This method enters the specified text into the
	 * provided WebElement, typically an input field.
	 * 
	 * @param element The WebElement to type into.
	 * @param text    The text to be typed.
	 */
	public static boolean type(WebElement ele, String text) throws Throwable {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}

		}
		return flag;
	}

	/**
	 * Selects an option from a dropdown using sendKeys. This method selects an
	 * option from a dropdown WebElement by sending keys to it.
	 * 
	 * @param value The value to select.
	 * @param ele   The dropdown WebElement.
	 * @throws Throwable If there is an error while selecting the option.
	 * @return True if the option is successfully selected, false otherwise.
	 */
	public static boolean selectBySendkeys(String value, WebElement ele) throws Throwable {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");
			} else {
				System.out.println("Not Selected value from the DropDown");
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param index       : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 * 
	 **/
	public static boolean selectByIndex(WebElement element, int index) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param value       : Value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	public static boolean selectByValue(WebElement element, String value) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param visibletext : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	public static boolean selectByVisibleText(String visibletext, WebElement ele) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}

	/**
	 * Performs a mouse hover action on a WebElement using JavaScript. This method
	 * triggers a mouse hover action on the specified WebElement using JavaScript.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to hover over.
	 * @throws Throwable If there is an error while performing the mouse hover
	 *                   action.
	 */
	public static boolean mouseHoverByJavaScript(WebElement ele, JavascriptExecutor driver) throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("MouseOver Action is performed");
			} else {
				System.out.println("MouseOver Action is not performed");
			}
		}
	}

	/**
	 * Clicks on a WebElement using JavaScript. This method performs a click action
	 * on the specified WebElement using JavaScript.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to click on.
	 * @throws Throwable If there is an error while performing the click action.
	 */
	public static boolean JSClick(WebDriver driver, WebElement ele, By locator) throws Throwable {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			((JavascriptExecutor) driver).executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue : Frame ID wish to switch
	 * 
	 */
	public static boolean switchToFrameById(WebDriver driver, String idValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue : Frame Name wish to switch
	 * 
	 */
	public static boolean switchToFrameByName(WebDriver driver, String nameValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}

	/**
	 * Switches the WebDriver focus back to the default content frame. This method
	 * switches the focus of the WebDriver back to the default content frame.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 */
	public static boolean switchToDefaultFrame(WebDriver driver) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ", "Frame with Name is selected");
			} else if (!flag) {
				failureReport("SelectFrame ", "The Frame is not selected");
			}
		}
	}

	/**
	 * Moves the mouse pointer over a WebElement. This method moves the mouse
	 * pointer over the specified WebElement.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to move the mouse pointer over.
	 */
	public static void mouseOverElement(WebDriver driver, WebElement element) throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println(" MouserOver Action is performed on ");
			} else {
				System.out.println("MouseOver action is not performed on");
			}
		}
	}

	public static boolean moveToElement(WebDriver driver, WebElement ele, By locator) {
		boolean flag = false;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(ele).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Moves the mouse pointer over a WebElement. This method moves the mouse
	 * pointer over the specified WebElement.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to move the mouse pointer over.
	 */
	public static boolean mouseover(WebDriver driver, WebElement ele, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {

			if (flag) {
				SuccessReport("MouseOver ", "MouserOver Action is performed on \"" + locatorName + "\"");
			} else {
				failureReport("MouseOver", "MouseOver action is not performed on \"" + locatorName + "\"");
			}

		}
	}

	/**
	 * Reports a success message. This method logs or reports a success message.
	 * 
	 * @param action  The action that was successful.
	 * @param message The message indicating the success.
	 */
	private static void SuccessReport(String action, String message) {
		System.out.println("Success: " + action + " - " + message);
	}

	/**
	 * Reports a failure message. This method logs or reports a failure message.
	 * 
	 * @param action  The action that failed.
	 * @param message The message indicating the cause of failure.
	 */
	private static void failureReport(String action, String message) {
		System.out.println("Failure: " + action + " - " + message);
	}

	/**
	 * Drags and drops a WebElement to a specified location. This method performs a
	 * drag-and-drop action on the specified WebElement, moving it to the target
	 * location.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param source  The WebElement to drag.
	 * @param targetX The X-coordinate of the target location.
	 * @param targetY The Y-coordinate of the target location.
	 */
	public static boolean draggable(WebDriver driver, WebElement source, int x, int y) throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();
			flag = true;
			return true;

		} catch (Exception e) {

			return false;

		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \"" + source + "\"");
			} else if (!flag) {
				System.out.println("Draggable action is not performed on \"" + source + "\"");
			}
		}
	}

	/**
	 * Drags and drops a source WebElement onto a target WebElement. This method
	 * performs a drag-and-drop action by dragging the source WebElement and
	 * dropping it onto the target WebElement.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @param source The source WebElement to drag.
	 * @param target The target WebElement to drop onto.
	 */
	public static boolean draganddrop(WebDriver driver, WebElement source, WebElement target) throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if (!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}

	/**
	 * Sets the value of a slider WebElement. This method sets the value of a slider
	 * WebElement to the specified value.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @param slider The slider WebElement.
	 * @param value  The value to set the slider to.
	 */
	public static boolean slider(WebDriver driver, WebElement ele, int x, int y) throws Throwable {
		boolean flag = false;
		try {
			WebElement dragitem = null;
			new Actions(driver).dragAndDropBy(dragitem, 400, 1).build().perform();
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}

	/**
	 * Performs a right-click action on a WebElement. This method performs a
	 * right-click action on the specified WebElement.
	 * 
	 * @param driver  The WebDriver instance controlling the browser.
	 * @param element The WebElement to right-click on.
	 */
	public static boolean rightclick(WebDriver driver, WebElement ele) throws Throwable {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(ele).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("RightClick Action is performed");
			} else {
				System.out.println("RightClick Action is not performed");
			}
		}
	}

	/**
	 * Switches the WebDriver focus to a window with a specific title. This method
	 * switches the WebDriver focus to a window with the specified title.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @param title  The title of the window to switch to.
	 */
	public static Boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) throws Throwable {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count - 1]);

			if (driver.getTitle().contains(windowTitle)) {
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		} catch (Exception e) {

			flag = true;
			return false;
		} finally {
			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not Selected");
			}
		}
	}

	/**
	 * Switches the WebDriver focus to a newly opened window. This method switches
	 * the focus of the WebDriver to the newly opened window.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 */
	public static Boolean switchToNewWindow(WebDriver driver) throws Throwable {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	/**
	 * Switches the WebDriver focus back to the original window. This method
	 * switches the focus of the WebDriver back to the original window.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 */
	public static Boolean switchToOldWindow(WebDriver driver) throws Throwable {
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	/**
	 * Gets the number of columns in a table row. This method retrieves the number
	 * of columns in a table row WebElement.
	 * 
	 * @param row The WebElement representing the table row.
	 * @return The number of columns in the table row.
	 * @throws Exception If there is an error while retrieving the column count.
	 */
	public static int getColumncount(WebElement row) throws Exception {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}

	/**
	 * Gets the number of rows in a table. This method retrieves the number of rows
	 * in a table represented by the specified WebElement.
	 * 
	 * @param table The WebElement representing the table.
	 * @return The number of rows in the table.
	 * @throws Exception If there is an error while retrieving the row count.
	 */
	public static int getRowCount(WebElement table) throws Exception {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */

	public static boolean Alert(WebDriver driver) throws Throwable {
		boolean presentFlag = false;

		try {
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				System.out.println("The Alert is handled successfully");
			} else {
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}

	/**
	 * Launches a URL in the browser. This method navigates the WebDriver to the
	 * specified URL.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @param url    The URL to navigate to.
	 * @return True if the URL was successfully launched, false otherwise.
	 * @throws Throwable If there is an error while navigating to the URL.
	 */
	public static boolean launchUrl(WebDriver driver, String url) throws Throwable {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched \"" + url + "\"");
			} else {
				System.out.println("Failed to launch \"" + url + "\"");
			}
		}
	}

	/**
	 * Checks if an alert is present. This method checks if an alert is present on
	 * the current page.
	 * 
	 * @param driver The WebDriver instance controlling the browser.
	 * @return True if an alert is present, false otherwise.
	 */
	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

}
