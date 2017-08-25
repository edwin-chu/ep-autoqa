package com.autoqa.ep;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class EnterESBSkinContest extends EPTest
{

	private static String attributeName = "contest-data";

	private static String adminUrl = "http://esportsplus-dev-admin-v1-31479.esportsplusdev.com:31101/";

	private static String steamUrl = "http://steamcommunity.com/";

	private String mainWindow = "";

	private String steamWindow = "";

	private String adminWindow = "";

	private String steamPopupWindow = "";

	/**
	 * @param baseUrl
	 */
	public EnterESBSkinContest(String baseUrl)
	{
		super(baseUrl);
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

	public void enterSkinContest(int numContest) throws Exception
	{
		int min = 5;
		int max = 6;
		boolean noContest = false;
		int index = 1;
		getDriver().switchTo().window(this.mainWindow);
		// enter contest for both sides for all available contests
		/*
		 * while (!noContest)
		 * {
		 * try
		 * {
		 * WebElement contest = getDriver().findElement(By.xpath(".//*[@id='contest-list-scroller']/div[" + index + "]"));
		 * WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		 * wait.until(ExpectedConditions.elementToBeClickable(contest));
		 * contest.click();
		 * selectUpperTeam();
		 * selectLowerTeam();
		 * index++;
		 * } catch (NoSuchElementException e)
		 * {
		 * System.out.println("No More Contest!");
		 * noContest = true;
		 * }
		 * }
		 */
		// enter contest for both sides for the first max contests.
		/*
		 * while (index <= max)
		 * {
		 * WebElement contest = getDriver().findElement(By.xpath(".//*[@id='contest-list-scroller']/div[" + index + "]"));
		 * WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		 * wait.until(ExpectedConditions.elementToBeClickable(contest));
		 * contest.click();
		 * selectUpperTeam();
		 * selectLowerTeam();
		 * index++;
		 * }
		 */
		// enter contest for a range of contest from min to max
		for (index = min; index <= max; index++)
		{
			WebElement contestTop = getDriver().findElement(By.xpath(".//*[@id='contest-list-scroller']/div[" + index + "]"));
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(contestTop));
			contestTop.click();
			selectUpperTeam();
			Thread.sleep(5000);
			WebElement contestBot = getDriver().findElement(By.xpath(".//*[@id='contest-list-scroller']/div[" + index + "]"));
			WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
			wait1.until(ExpectedConditions.elementToBeClickable(contestBot));
			contestBot.click();
			selectLowerTeam();
			Thread.sleep(5000);
		}
	}

	@Test
	public void esbSkinContestTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToEsportsBook();
		selectCSGO();
		int totalContest = extractContestData();
		loginAdminAndSteam();
		enterSkinContest(totalContest);
	}

	public int extractContestData()
	{
		ArrayList<String> contestData = new ArrayList<String>();
		int index = 1;
		boolean noElement = false;
		while (!noElement)
		{
			try
			{
				WebElement contest = getDriver().findElement(By.xpath(".//*[@id='contest-list-scroller']/div[" + index + "]"));
				contestData.add(contest.getAttribute(attributeName));
				index++;
			} catch (NoSuchElementException e)
			{
				System.out.println("No More Contests");
				noElement = true;
			}
		}
		System.out.println("Contest-Data: " + contestData);
		System.out.println("Number of Contests: " + contestData.size());
		return contestData.size();
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

	public void selectCSGO()
	{
		WebElement csgo = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[1]"));
		csgo.click();
	}

	public void selectLowerTeam() throws Exception
	{
		// click on check mark of the team at the top
		WebElement checkMark = getDriver().findElement(By.xpath(".//*[@id='editBettingSkin-wrapper']/div[2]/div[3]/div/div[2]/div[5]/img"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(checkMark));
		checkMark.click();
		// click on load inventory button
		WebElement loadBtn = getDriver().findElement(By.id("edit-skin-betting-load-inventory"));
		loadBtn.click();
		// select an item "div[1]" represent the first item on the list.
		WebElement item = getDriver().findElement(By.xpath(".//*[@id='coinFlipItemList-scroller']/div[20]/div[3]/div/img"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(item));
		item.click();
		// click on Join Game button to get trade offer to join the game
		WebElement joinBtn = getDriver().findElement(By.id("esportsbookAddItemsBtn"));
		joinBtn.click();
		dismissPopup();
		getDriver().switchTo().window(this.steamWindow);
		Thread.sleep(20000);
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
			if (!this.mainWindow.equals(window3) && !this.steamWindow.equals(window3) && !this.adminWindow.equals(window3))
			{
				this.steamPopupWindow = window3;
				System.out.println("Steam Popup Window: " + this.steamPopupWindow);
			}
		}
		// switch to steam pop up window to confirm trade
		getDriver().switchTo().window(this.steamPopupWindow);
		System.out.println("Steam Trade PopUp Window: " + this.steamPopupWindow);
		confirmTradeSteam();
		// close steam popup window
		getDriver().close();
		// switch to admin site for mobile confirmation
		getDriver().switchTo().window(this.adminWindow);
		System.out.println("Admin Window: " + this.adminWindow);
		// mobile confirmation in admin site
		mobileConfirmAdmin();
		System.out.println("Mobile Trade Confirmation Successful");
		getDriver().switchTo().window(this.mainWindow);
		// dismiss game added confirmation popup
		Thread.sleep(5000);
		WebElement gameAdded = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait2 = new WebDriverWait(getDriver(), 10);
		wait2.until(ExpectedConditions.elementToBeClickable(gameAdded));
		gameAdded.click();
	}

	public void selectUpperTeam() throws Exception
	{
		// click on check mark of the team at the top
		WebElement checkMark = getDriver().findElement(By.xpath(".//*[@id='editBettingSkin-wrapper']/div[2]/div[3]/div/div[1]/div[5]/img"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(checkMark));
		checkMark.click();
		// click on load inventory button
		WebElement loadBtn = getDriver().findElement(By.id("edit-skin-betting-load-inventory"));
		loadBtn.click();
		// select an item "div[1]" represent the first item on the list.
		WebElement item = getDriver().findElement(By.xpath(".//*[@id='coinFlipItemList-scroller']/div[20]/div[3]/div/img"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(item));
		item.click();
		// click on Join Game button to get trade offer to join the game
		WebElement joinBtn = getDriver().findElement(By.id("esportsbookAddItemsBtn"));
		joinBtn.click();
		dismissPopup();
		getDriver().switchTo().window(this.steamWindow);
		Thread.sleep(20000);
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
			if (!this.mainWindow.equals(window3) && !this.steamWindow.equals(window3) && !this.adminWindow.equals(window3))
			{
				this.steamPopupWindow = window3;
				System.out.println("Steam Popup Window: " + this.steamPopupWindow);
			}
		}
		// switch to steam pop up window to confirm trade
		getDriver().switchTo().window(this.steamPopupWindow);
		System.out.println("Steam Trade PopUp Window: " + this.steamPopupWindow);
		confirmTradeSteam();
		// close steam popup window
		getDriver().close();
		// switch to admin site for mobile confirmation
		getDriver().switchTo().window(this.adminWindow);
		System.out.println("Admin Window: " + this.adminWindow);
		// mobile confirmation in admin site
		mobileConfirmAdmin();
		System.out.println("Mobile Trade Confirmation Successful");
		getDriver().switchTo().window(this.mainWindow);
		Thread.sleep(5000);
		WebElement gameAdded = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait2 = new WebDriverWait(getDriver(), 10);
		wait2.until(ExpectedConditions.elementToBeClickable(gameAdded));
		gameAdded.click();
	}
}
