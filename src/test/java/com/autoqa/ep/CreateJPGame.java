package com.autoqa.ep;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class CreateJPGame extends EPTest
{

	private String mainWindow;

	private String adminUrl = "http://esportsplus-dev-admin-v1-31479.esportsplusdev.com:31101/";

	private String steamUrl = "http://steamcommunity.com/";

	/**
	 * @param baseUrl
	 */
	public CreateJPGame(String baseUrl)
	{
		super(baseUrl);
	}

	@Test
	public void createJPGameTest() throws Exception
	{
		// will be use for two-factor code from admin site for use on steam site
		String code = "";
		// Strings for 3 window handles
		String steamWindow = "";
		String adminWindow = "";
		String steamPopupWindow = "";
		// get window handle for main window
		this.mainWindow = getDriver().getWindowHandle();
		System.out.println("Main Window: " + this.mainWindow);
		checkForLoginBonusPopup();
		createNewJPGame();
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
				adminWindow = window1;
				System.out.println("Admin Window: " + adminWindow);
			}
		}
		// switch to admin windown and navigate to admin site and sign in
		getDriver().switchTo().window(adminWindow);
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
			if (!this.mainWindow.equals(window2) && !adminWindow.equals(window2))
			{
				steamWindow = window2;
				System.out.println("steam Window: " + steamWindow);
			}
		}
		// switch to steam window and navigate to steam site and sign in
		getDriver().switchTo().window(steamWindow);
		getDriver().navigate().to(this.steamUrl);
		signInSteam();
		// enter two factor code with code obtain from admin site to complete
		// sign in
		enterTwoFactorCode(code);
		acceptTradeSteam();
		// accepting steam trade will generate another popup window and this
		// handles will involve 4 windows
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
			if (!this.mainWindow.equals(window3) && !steamWindow.equals(window3) && !adminWindow.equals(window3))
			{
				steamPopupWindow = window3;
				System.out.println("Steam Popup Window: " + steamPopupWindow);
			}
		}
		// switch to steam pop up window to confirm trade
		getDriver().switchTo().window(steamPopupWindow);
		System.out.println("Steam Trade PopUp Window: " + steamPopupWindow);
		confirmTradeSteam();
		// close steam popup window
		getDriver().close();
		// switch to admin site for mobile confirmation
		getDriver().switchTo().window(adminWindow);
		System.out.println("Admin Window: " + adminWindow);
		// mobile confirmation in admin site
		mobileConfirmAdmin();
		System.out.println("Mobile Trade Confirmation Successful");
		getDriver().switchTo().window(steamWindow);
		getDriver().switchTo().window(this.mainWindow);
	}

	public void createNewJPGame() throws Exception
	{
		navigateToJackPot();
		selectDeposit();
		selectItem();
		selectDepositButton();
		Thread.sleep(2000);
		dismissPopup();
	}

	// dismiss two popups after initiating steam trade
	public void dismissPopup() throws Exception
	{
		WebElement popup1 = getDriver().findElement(By.cssSelector(".esp-submit.button.btn-default.primary"));
		Thread.sleep(1000);
		popup1.click();
		Thread.sleep(5000);
		WebElement popup2 = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(popup2));
		popup2.click();
	}

	// select deposit to deposit item into JP game
	public void selectDeposit()
	{
		WebElement deposit = getDriver().findElement(By.cssSelector(".btn.btn-default.recent-skin-button.deposit"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(deposit));
		deposit.click();
	}

	// click on deposit button to get steam trade offer
	public void selectDepositButton()
	{
		WebElement depositBtn = getDriver().findElement(By.cssSelector(".skin-jackpot-deposit.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(depositBtn));
		depositBtn.click();
	}

	// select item to deposit
	public void selectItem()
	{
		WebElement item = getDriver().findElement(By.xpath(".//*[@id='skinJackpotItemList-scroller']/div[15]/div[3]/div/img"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(item));
		item.click();
	}
}
