package com.autoqa.ep;

import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

import com.autoqa.ep.auto.*;

public class EPTest extends AbstractLoginSetUp
{

	private static boolean loginPopupClosed = false;

	public EPTest(String baseUrl)
	{
		super("http://esportsplus-dev-main-31479.esportsplusdev.com:30101/");
	}

	// after entering 2-factor code, accept trade with this function
	public void acceptTradeSteam()
	{
		// click on trade notification at top right
		WebElement tradeNotice = getDriver().findElement(By.id("header_notification_link"));
		tradeNotice.click();
		// select trade message from the dropdown
		WebElement tradeNoticeDropdown = getDriver().findElement(By.xpath(".//*[@id='header_notification_dropdown']/div/a[6]/span[2]"));
		tradeNoticeDropdown.click();
		// select trade response to bring up trade confirmation window
		WebElement tradeResponse = getDriver().findElement(By.cssSelector(".link_overlay"));
		tradeResponse.click();
		// handle warning popup for fraudulent bot
		try
		{
			WebElement warning = getDriver().findElement(By.xpath("html/body/div[3]/div[2]/div/div[2]/div/span"));
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(warning));
			warning.click();
		} catch (NoSuchElementException e)
		{
			getLog().info("No Warning Popup");
		} finally		// program will continue even there is
		// NoSuchElementException
		{
			getLog().info("Program Should Continue");
		}
	}

	// check pop up when going to CF screen for new users
	public void checkForCFHowToPlayPopup()
	{
		try
		{
			WebElement cfHowToPlay = getDriver().findElement(By.cssSelector("dfs-button-0"));
			cfHowToPlay.click();
		} catch (NoSuchElementException e)
		{
			getLog().info("No Login Bonus Popup");
		} finally		// program will continue even there is
		// NoSuchElementException
		{
			getLog().info("Program Should Continue");
		}
	}

	// check pop up when going to LD screen for new users
	public void checkForLDHowToPlayPopup()
	{
		try
		{
			WebElement ldHowToPlay = getDriver().findElement(By.cssSelector("dfs-button-0"));
			ldHowToPlay.click();
		} catch (NoSuchElementException e)
		{
			getLog().info("No Login Bonus Popup");
		} finally		// program will continue even there is
		// NoSuchElementException
		{
			getLog().info("Program Should Continue");
		}
	}

	// check for daily login bonus popup
	public void checkForLoginBonusPopup() throws Exception
	{
		try
		{
			WebElement loginBonus = getDriver().findElement(By.id("dfs-button-0"));
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("dfs-button-0")));
			Thread.sleep(1000);
			loginBonus.click();
		} catch (NoSuchElementException e)
		{
			getLog().info("No Login Bonus Popup");
		} finally
		{
			// program will continue even there is NoSuchElementException
			getLog().info("Program Should Continue");
		}
	}

	// check for popups when logging into EP app
	public void checkForLoginPopup()
	{
		try
		{
			WebElement popupCloseIcon = getDriver().findElement(By.cssSelector(".glyphicon.glyphicon-menu-left"));
			getLog().info("Popup is being closed");
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(popupCloseIcon));
			popupCloseIcon.click();
		} catch (NoSuchElementException e)
		{
			getLog().info("Popup is not present.");
		} finally
		{
			// program will continue even there is NoSuchElementException
			getLog().info("Program Should Continue");
		}
	}

	// this function will happen on steam trade confirmation popup window to
	// confirm trade with mobile authentication needed
	public void confirmTradeSteam() throws Exception
	{
		// click on the box to accept the trade in steam popup window
		WebElement acceptBox = getDriver().findElement(By.xpath(".//*[@id='you_notready']/div"));
		acceptBox.click();
		// confirm that it's a gift on the pop up
		WebElement yes = getDriver().findElement(By.cssSelector(".btn_green_white_innerfade.btn_medium>span"));
		yes.click();
		// finally accept the trade. Add some delay so that element will be
		// clickable before clicking
		WebElement accept = getDriver().findElement(By.id("trade_confirmbtn_text"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(accept));
		Thread.sleep(1000);
		accept.click();
		Thread.sleep(1000);
	}

	// creating child window by clicking on steam status link from landing page
	public void createChildWindow() throws Exception
	{
		WebElement child = getDriver().findElement(By.cssSelector(".no-hover-effect>a"));
		Thread.sleep(1000);
		child.click();
	}

	// this is to create new CF game by clicking on the "Create New Game" button
	public void createNewGame() throws Exception
	{
		WebElement newGameButton = getDriver().findElement(By.cssSelector(".coin-flip-generic.coin-flip-new"));
		Thread.sleep(1000);
		newGameButton.click();
	}

	// enter 2-factor code in during steam login process
	public void enterTwoFactorCode(String code)
	{
		// enter two factor code
		WebElement twoFactor = getDriver().findElement(By.id("twofactorcode_entry"));
		twoFactor.clear();
		twoFactor.sendKeys(code);
		// click on submit button
		WebElement submit = getDriver().findElement(By.xpath(".//*[@id='login_twofactorauth_buttonset_entercode']/div[1]"));
		submit.click();
	}

	// find 2-factor code in admin for steam login
	public String findAuthCode() throws Exception
	{
		// click on shop dropdown menu
		Thread.sleep(5000);
		WebElement shop = getDriver().findElement(By.cssSelector("nav > ul > li:nth-child(3) > a > span"));
		shop.click();
		// select 2-factor code option
		WebElement code = getDriver().findElement(By.cssSelector("nav > ul > li:nth-child(3) > ul > li:nth-child(6)"));
		code.click();
		// enter steam account name
		WebElement accountField = getDriver().findElement(By.id("keyword"));
		accountField.clear();
		accountField.sendKeys(getProp("steamAccountName"));
		// click on search button
		WebElement search = getDriver().findElement(By.cssSelector(".btn.btn-sm.btn-primary.search"));
		search.click();
		// get two factor code, store in String result and return result
		WebElement twoFactor = getDriver().findElement(By.cssSelector("#search-2-factor-code>b"));
		String result = twoFactor.getText();
		System.out.println("Two Factor Code: " + result);
		return result;
	}

	// this is to hide the banner right after logging in
	public void hideBanner()
	{
		WebElement hideBanner = getDriver().findElement(By.id("giveaway-banner-hide"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(hideBanner));
		hideBanner.click();
	}

	/**
	 * @throws InterruptedException
	 * @throws Exception
	 * @see com.sqa.ec.auto.AbstractLoginTest#login(java.lang.String,
	 *      java.lang.String)
	 */
	// this login is used to login to EP App
	@Override
	public void login(String username, String password) throws Exception
	{
		checkForLoginPopup();
		Thread.sleep(2000);
		hideBanner();
		// click on login link at the top right
		WebElement loginButton = getDriver().findElement(By.cssSelector(".link-signin"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		// enter email address at the popup login screen
		WebElement emailField = getDriver().findElement(By.id("inputEmail"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(emailField));
		emailField.clear();
		emailField.sendKeys(username);
		// enter password at the popup login screen
		WebElement passwordField = getDriver().findElement(By.id("inputPassword"));
		WebDriverWait wait2 = new WebDriverWait(getDriver(), 10);
		wait2.until(ExpectedConditions.elementToBeClickable(passwordField));
		passwordField.clear();
		passwordField.sendKeys(password);
		// click on sign in button
		WebElement signInButton = getDriver().findElement(By.id("sign_in_btn"));
		WebDriverWait wait3 = new WebDriverWait(getDriver(), 10);
		wait3.until(ExpectedConditions.elementToBeClickable(signInButton));
		signInButton.click();
	}

	/**
	 * @see com.sqa.ec.auto.AbstractLoginTest#logout()
	 */
	// this is to log out of EP App
	@Override
	public void logout()
	{
		WebElement logoutButton = getDriver().findElement(By.cssSelector(".link-logout"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		Actions action = new Actions(getDriver());
		action.moveToElement(logoutButton).click();
	}

	// mobile confirmation in admin
	public void mobileConfirmAdmin() throws Exception
	{
		// click on shop dropdown menu
		WebElement shop = getDriver().findElement(By.cssSelector("nav > ul > li:nth-child(3) > a > span"));
		shop.click();
		// select Mobile Trade Confirmation option
		WebElement mobile = getDriver().findElement(By.cssSelector("nav > ul > li:nth-child(3) > ul > li:nth-child(7)"));
		mobile.click();
		// enter steam account name
		WebElement accountField = getDriver().findElement(By.id("keyword"));
		accountField.clear();
		accountField.sendKeys(getProp("steamAccountName"));
		// click on list button to search for trade confirmation
		WebElement listBtn = getDriver().findElement(By.cssSelector(".btn.btn-sm.btn-primary.list"));
		Thread.sleep(15000);
		listBtn.click();
		Thread.sleep(2000);
		// click on accept button for mobile trade confirmation
		WebElement acceptBtn = getDriver().findElement(By.cssSelector(".btn.btn-default.acceptTrade"));
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(acceptBtn));
		acceptBtn.click();
		Thread.sleep(1000);
		// click yes to confirm the mobile confirmation
		WebElement confirm = getDriver().findElement(By.xpath("html/body/div[5]/div/div/div[2]/button[2]"));
		confirm.click();
	}

	// this is to navigate to Coin Slots menu at the left from landing page
	public void navigateToCoinSlots() throws Exception
	{
		WebElement coinSlots = getDriver().findElement(By.cssSelector(".has-image.icon-mystery-boxes"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(coinSlots));
		Thread.sleep(1000);
		coinSlots.click();
	}

	// this is to navigate to Deposit Skin menu at the left from landing page
	public void navigateToDepositSkins()
	{
		WebElement depositSkins = getDriver().findElement(By.cssSelector(".has-image.icon-sell-skins"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(depositSkins));
		depositSkins.click();
	}

	// this is to navigate to EsportsBook menu at the left from landing page
	public void navigateToEsportsBook() throws Exception
	{
		WebElement esportsBook = getDriver().findElement(By.cssSelector(".has-image.icon-play"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(esportsBook));
		Thread.sleep(1000);
		esportsBook.click();
	}

	// this is to navigate to JackPot menu at the left from landing page
	public void navigateToJackPot()
	{
		WebElement jackpot = getDriver().findElement(By.cssSelector("ul > li:nth-child(2) a[data-route='skinJackpotHome'] > span.jackpot-value"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(jackpot));
		jackpot.click();
	}

	// this is to navigate to Leaderboard menu at the left from landing page
	public void navigateToLeaderBoard()
	{
		WebElement leaderboard = getDriver().findElement(By.cssSelector(".has-image.icon-leaderboard"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(leaderboard));
		leaderboard.click();
	}

	// this is to navigate to Lucky Dice menu at the left from landing page
	public void navigateToLuckyDice()
	{
		checkForLDHowToPlayPopup();
		WebElement luckyDice = getDriver().findElement(By.cssSelector(".has-image.icon-lucky-dice"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(luckyDice));
		luckyDice.click();
	}

	// this is to navigate to MyItems menu at the left from landing page
	public void navigateToMyItems() throws Exception
	{
		WebElement myItems = getDriver().findElement(By.cssSelector(".has-image.icon-my-items"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(myItems));
		Thread.sleep(1000);
		myItems.click();
	}

	// this is to navigate to Shop menu at the left from landing page
	public void navigateToShop()
	{
		WebElement shop = getDriver().findElement(By.cssSelector(".has-image.icon-cart"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(shop));
		shop.click();
	}

	// randomly select a cf side
	public void selectASides() throws Exception
	{
		int maxValue = 100;
		Random random = new Random();
		// generate random value from 1 to 100
		int value = random.nextInt(maxValue) + 1;
		System.out.println("Random value is:" + value);
		if (value <= maxValue / 2)
		{
			WebElement sideA = getDriver().findElement(By.cssSelector("img.coin-flip-avatar[coinside='1']"));
			WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
			wait1.until(ExpectedConditions.elementToBeClickable(sideA));
			Thread.sleep(1000);
			sideA.click();
			System.out.println("Side A is selected");
		} else
		{
			WebElement sideB = getDriver().findElement(By.cssSelector(".coin-flip-avatar.blur"));
			WebDriverWait wait2 = new WebDriverWait(getDriver(), 10);
			wait2.until(ExpectedConditions.elementToBeClickable(sideB));
			Thread.sleep(1000);
			sideB.click();
			System.out.println("Side B is selected");
		}
	}

	// function for signing in to admin site
	public void signInAdmin()
	{
		// click Sign In link
		WebElement signIn = getDriver().findElement(By.id("login-sign-in-link"));
		signIn.click();
		// enter email
		WebElement email = getDriver().findElement(By.id("login-email"));
		email.clear();
		email.sendKeys(getProp("adminEmail"));
		// enter password
		WebElement pass = getDriver().findElement(By.id("login-password"));
		pass.clear();
		pass.sendKeys(getProp("adminPass"));
		// click Sign In button to sign in
		WebElement signInBtn = getDriver().findElement(By.id("login-buttons-password"));
		signInBtn.click();
	}

	// function for signing into steam site
	public void signInSteam()
	{
		// click on login link
		WebElement login = getDriver().findElement(By.xpath(".//*[@id='global_action_menu']/a"));
		login.click();
		// enter steam username
		WebElement usernameField = getDriver().findElement(By.id("steamAccountName"));
		usernameField.clear();
		usernameField.sendKeys(getProp("steamAccountName"));
		// enter password
		WebElement passField = getDriver().findElement(By.id("steamPassword"));
		passField.clear();
		passField.sendKeys(getProp("steamPass"));
		// click on sign in button
		WebElement signInBtn = getDriver().findElement(By.id("SteamLogin"));
		signInBtn.click();
	}
}
