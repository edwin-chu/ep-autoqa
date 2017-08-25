package com.autoqa.ep;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class MyItemsRetrieval extends EPTest
{

	private static String cf = "Coin Flip";

	private static String jp = "Jackpot";

	private static String esb = "Esportsbook";

	private static String adminUrl = "http://esportsplus-dev-admin-v1-31479.esportsplusdev.com:31101/";

	private static String steamUrl = "http://steamcommunity.com/";

	private String mainWindow = "";

	private String steamWindow = "";

	private String adminWindow = "";

	private String steamPopupWindow = "";

	/**
	 * @param baseUrl
	 */
	public MyItemsRetrieval(String baseUrl)
	{
		super(baseUrl);
	}

	// this function is for accepting trade without needing to do mobile authentication
	public void confirmTrade() throws Exception
	{
		// click on the box to accept the trade in steam popup window
		WebElement acceptBox = getDriver().findElement(By.xpath(".//*[@id='you_notready']/div"));
		acceptBox.click();
		// finally accept the trade. Add some delay so that element will be
		// clickable before clicking
		WebElement accept = getDriver().findElement(By.id("trade_confirmbtn_text"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(accept));
		Thread.sleep(1000);
		accept.click();
		Thread.sleep(1000);
		// close steam popup window
		getDriver().close();
	}

	public void dismissPopup() throws Exception
	{
		WebElement popup1 = getDriver().findElement(By.cssSelector(".esp-submit.button.btn-default.primary"));
		Thread.sleep(1000);
		popup1.click();
		Thread.sleep(5000);
		try
		{
			WebElement popup2 = getDriver().findElement(By.id("dfs-button-0"));
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(popup2));
			popup2.click();
		} catch (NoSuchElementException e)
		{
			System.out.println("No 2nd Popup");
			;
		} finally
		{
			System.out.println("Program should continue");
		}
	}

	public void loginAdminAndSteam() throws Exception
	{
		// will be use for two-factor code from admin site for use on steam site
		String code = "";
		createChildWindow();
		// get handles for two windows
		Set<String> handles1 = getDriver().getWindowHandles();
		System.out.println(handles1);
		// iterator to iterate through 2 window handles
		Iterator<String> iter1 = handles1.iterator();
		// iterate two window handles, find out handle for admin window
		while (iter1.hasNext())
		{
			String window1 = iter1.next();
			// if handle is not main window, then assign handle as admin window
			if (!this.mainWindow.equals(window1))
			{
				this.adminWindow = window1;
				System.out.println("Admin Window: " + this.adminWindow);
			}
		}
		// switch to admin windown and navigate to admin site and sign in
		getDriver().switchTo().window(this.adminWindow);
		getDriver().navigate().to(this.adminUrl);
		signInAdmin();
		// get 2-factor code from admin and assign to code
		code = findAuthCode();
		// switch back to main window to create another child window
		getDriver().switchTo().window(this.mainWindow);
		System.out.println("Main Window: " + this.mainWindow);
		createChildWindow();
		// get handles for all windows, this time there will be 3 windows
		Set<String> handles2 = getDriver().getWindowHandles();
		System.out.println(handles2);
		// iterator to iterate through 3 windows
		Iterator<String> iter2 = handles2.iterator();
		// iterate through 3 window handles, find out handle for steam window
		while (iter2.hasNext())
		{
			String window2 = iter2.next();
			// if handle is not main and admin window handle, assign handle as
			// steam window
			if (!this.mainWindow.equals(window2) && !this.adminWindow.equals(window2))
			{
				this.steamWindow = window2;
				System.out.println("steam Window: " + this.steamWindow);
			}
		}
		// switch to steam window and navigate to steam site and sign in
		getDriver().switchTo().window(this.steamWindow);
		getDriver().navigate().to(this.steamUrl);
		signInSteam();
		// enter two factor code with code obtain from admin site to complete
		// sign in
		enterTwoFactorCode(code);
		getDriver().switchTo().window(this.mainWindow);
	}

	public void moveToSteamPopupWindow()
	{
		Set<String> handles3 = getDriver().getWindowHandles();
		System.out.println(handles3);
		// iterator to go through all window handles
		Iterator<String> iter3 = handles3.iterator();
		// iterate through 4 window handles, find out handle for steam pop up
		// window
		while (iter3.hasNext())
		{
			String window3 = iter3.next();
			// if window handle is not main, admin, AND steam window, assign
			// handle as steam popup window
			if (!this.mainWindow.equals(window3) && !this.steamWindow.equals(window3) && !this.adminWindow.equals(window3))
			{
				this.steamPopupWindow = window3;
				System.out.println("Steam Popup Window: " + this.steamPopupWindow);
			}
		}
		// switch to steam pop up window to confirm trade
		getDriver().switchTo().window(this.steamPopupWindow);
		System.out.println("Steam Trade PopUp Window: " + this.steamPopupWindow);
	}

	public void retrieveItems() throws Exception
	{
		boolean hasItem = false;
		while (!hasItem)
		{
			try
			{
				// identify the first item and select to retrieve
				WebElement item = getDriver().findElement(By.xpath(".//*[@id='shop-history-unretrieve-scroller']/div[1]/div"));
				WebDriverWait wait = new WebDriverWait(getDriver(), 10);
				wait.until(ExpectedConditions.elementToBeClickable(item));
				String name = item.getText();	// get text to check retrieval type
				item.click();
				if (name.contains(cf))			// check for cf substring
				{
					// click on retrieve button
					WebElement retrieveCF = getDriver().findElement(By.cssSelector(".coin-flip-retrieve.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect"));
					WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
					wait1.until(ExpectedConditions.elementToBeClickable(retrieveCF));
					retrieveCF.click();
					dismissPopup();
					getDriver().switchTo().window(this.steamWindow);
					acceptTradeSteam();
					moveToSteamPopupWindow();
					confirmTrade();
				} else if (name.contains(jp))		// check for jp substring
				{
					// click on retrieve button
					WebElement retrieveJP = getDriver().findElement(By.cssSelector(".skin-jackpot-retrieve.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect"));
					WebDriverWait wait2 = new WebDriverWait(getDriver(), 10);
					wait2.until(ExpectedConditions.elementToBeClickable(retrieveJP));
					retrieveJP.click();
					dismissPopup();
					getDriver().switchTo().window(this.steamWindow);
					acceptTradeSteam();
					moveToSteamPopupWindow();
					confirmTrade();
				} else if (name.contains(esb))	// check for esb substring
				{
					// click on retrieve button
					WebElement retrieveESB = getDriver().findElement(By.cssSelector(".sportsbook-retrieve.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect"));
					WebDriverWait wait3 = new WebDriverWait(getDriver(), 10);
					wait3.until(ExpectedConditions.elementToBeClickable(retrieveESB));
					retrieveESB.click();
					dismissPopup();
					getDriver().switchTo().window(this.steamWindow);
					acceptTradeSteam();
					moveToSteamPopupWindow();
					confirmTrade();
				} else			// if substring not include cf, jp, and esb, then it's for shop
				{
					// click on retrieve button
					WebElement retrieveShop = getDriver().findElement(By.cssSelector(".shop-item-retrieve.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect"));
					WebDriverWait wait4 = new WebDriverWait(getDriver(), 10);
					wait4.until(ExpectedConditions.elementToBeClickable(retrieveShop));
					retrieveShop.click();
					dismissPopup();
					getDriver().switchTo().window(this.steamWindow);
					acceptTradeSteam();
					moveToSteamPopupWindow();
					confirmTrade();
				}
			} catch (NoSuchElementException e)
			{
				System.out.println("No More Item To Retrieve");
				hasItem = true;
			}
			getDriver().switchTo().window(this.mainWindow);
			// move back to Unretrieved tab and stays in while loop if there are more item to be retrieved
			WebElement unretrieved = getDriver().findElement(By.xpath(".//*[@id='app-top-menu-buttons']/ul/li[1]/a/span"));
			WebDriverWait wait5 = new WebDriverWait(getDriver(), 10);
			wait5.until(ExpectedConditions.elementToBeClickable(unretrieved));
			Thread.sleep(1000);
			unretrieved.click();
		}
	}

	@Test
	public void retrieveItemTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToMyItems();
		loginAdminAndSteam();
		retrieveItems();
	}
}
