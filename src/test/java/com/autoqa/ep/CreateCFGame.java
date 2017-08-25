package com.autoqa.ep;

import java.util.*;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class CreateCFGame extends EPTest
{

	private String mainWindow;

	private String adminUrl = "http://esportsplus-dev-admin-v1-31479.esportsplusdev.com:31101/";

	private String steamUrl = "http://steamcommunity.com/";

	/**
	 * @param baseUrl
	 */
	public CreateCFGame(String baseUrl)
	{
		super(baseUrl);
	}

	@Test
	public void createCFGameTest() throws Exception
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
		createNewCFGame();
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

	public void createNewCFGame() throws Exception
	{
		// click on create game button from the landing page (Coin Flip Lobby)
		createNewGame();
		// randomly select a side for CF
		selectASides();
		// click on continue button
		selectContinueBtn();
		// select one item for CF
		selectItem();
		// click on Create Game button
		selectCreateGameBtn();
		// add some delay
		Thread.sleep(1000);
		// dismiss create game and trade notification pop up from app
		dismissPopup();
	}

	// dismiss create game and trade notification pop up from app
	public void dismissPopup() throws Exception
	{
		WebElement popup = getDriver().findElement(By.id("dfs-button-0"));
		Thread.sleep(1000);
		popup.click();
		Thread.sleep(1000);
	}

	// after selecting item for coin flip, click on the continue button
	public void selectContinueBtn()
	{
		WebElement continueButton = getDriver().findElement(By.id("btn-continue"));
		continueButton.click();
	}

	// after selecting item, then click on continue button, click on create new
	// game to create the game
	public void selectCreateGameBtn()
	{
		WebElement createGameBtn = getDriver().findElement(By.cssSelector(".coin-flip.coin-flip-new"));
		createGameBtn.click();
	}

	// this will select the fifth item appear on the list - See /div[5]/ in
	// xpath, can improve to make it more random
	public void selectItem()
	{
		WebElement firstItem = getDriver().findElement(By.xpath(".//*[@id='coinFlipItemList-scroller']/div[5]/div[3]/div/img"));
		firstItem.click();
	}
}
