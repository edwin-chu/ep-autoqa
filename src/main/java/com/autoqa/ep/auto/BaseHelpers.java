package com.autoqa.ep.auto;

import org.openqa.selenium.*;
import org.testng.log4testng.*;

import com.autoqa.ep.helpers.*;

/**
 * BaseHelpers //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public class BaseHelpers
{

	private String baseURL;

	private WebDriver driver;

	private Logger log = Logger.getLogger(this.getClass());

	private int screenshotCount = 1;

	protected String testingName = "Auto Test";

	private BaseSetUp relTest;

	public BaseHelpers(BaseSetUp test)
	{
		super();
		this.baseURL = test.getBaseURL();
		this.driver = test.getDriver();
		this.relTest = test;
	}

	public BaseHelpers(String baseURL)
	{
		super();
		this.baseURL = baseURL;
	}

	public BaseHelpers(String baseURL, WebDriver driver)
	{
		super();
		this.baseURL = baseURL;
		this.driver = driver;
	}

	/**
	 * @return the baseURL
	 */
	public String getBaseURL()
	{
		return this.baseURL;
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver()
	{
		return this.driver;
	}

	/**
	 * @return the log
	 */
	public Logger getLog()
	{
		return this.log;
	}

	public String getProp(String propName)
	{
		return AutoHelpers.getProp(propName, "src/main/resources/", "config.properties", getLog());
	}

	public boolean isPresent(By by)
	{
		return AutoHelpers.isElementPresent(getDriver(), by, getLog());
	}

	public boolean takeScreenshot()
	{
		boolean tookScreenshot = AutoHelpers.takeScreenshot("screenshots", this.testingName + this.screenshotCount, getDriver(), getLog());
		if (tookScreenshot)
		{
			this.screenshotCount++;
		}
		return tookScreenshot;
	}

	public boolean takeScreenshot(String fileName)
	{
		return AutoHelpers.takeScreenshot("screenshots", fileName, getDriver(), getLog());
	}

	/**
	 * @return the relTest
	 */
	protected BaseSetUp getRelTest()
	{
		return this.relTest;
	}

	/**
	 * @param driver
	 *            the driver to set
	 */
	protected void setDriver(WebDriver driver)
	{
		this.driver = driver;
	}
}
