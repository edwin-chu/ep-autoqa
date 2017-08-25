package com.autoqa.ep.auto;

import java.util.concurrent.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.safari.*;
import org.testng.annotations.*;

/**
 * BaseSetUp //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public class BaseSetUp extends BaseHelpers
{

	public BaseSetUp(String baseUrl)
	{
		super(baseUrl);
	}

	@BeforeClass(enabled = false, groups = "chrome")
	public void setUpChrome()
	{
		// Set system property to use Chrome driver
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		// Setup the driver to use Chrome
		System.out.println("Before Setting Chrome Driver");
		setDriver(new ChromeDriver());
		System.out.println("After setting Chrome Driver");
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		Dimension targetSize = new Dimension(1680, 1050);
		getDriver().manage().window().setSize(targetSize);
		// getDriver().get(getBaseURL());
	}

	@BeforeClass(enabled = true, groups = "firefox")
	public void setUpFirefox()
	{
		// Setup the driver to use Firefox
		System.out.println("Before Setting FF Driver");
		setDriver(new FirefoxDriver());
		System.out.println("After setting FF Driver");
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		getDriver().manage().window().maximize();
		// getDriver().get(getBaseURL());
	}

	@BeforeClass(enabled = false, groups = "safari")
	public void setUpSafari()
	{
		// Setup the driver to use Safari
		System.out.println("Before Setting Safari Driver");
		setDriver(new SafariDriver());
		System.out.println("After setting Safari Driver");
		// Set an implicit wait of up to 30 seconds
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Maximize the window
		Dimension targetSize = new Dimension(1680, 1050);
		getDriver().manage().window().setSize(targetSize);
		// getDriver().get(getBaseURL());
		// DesiredCapabilities desiredCapabilities =
		// DesiredCapabilities.safari();
		// SafariOptions safariOptions = new SafariOptions();
		// safariOptions.setUseCleanSession(true);
		// desiredCapabilities.setCapability(SafariOptions.CAPABILITY,
		// safariOptions);
	}

	@BeforeMethod
	public void setupTest()
	{
		// goto base URL
		// getDriver().get(getBaseURL());
	}

	@AfterClass
	public void tearDown()
	{
		// quit closes all instances of the browser.
		getDriver().quit();
		// close method would close current instance only.
		// ex: driver.close();
	}
}
