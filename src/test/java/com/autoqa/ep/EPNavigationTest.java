package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class EPNavigationTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public EPNavigationTest(String baseUrl)
	{
		super(baseUrl);
	}

	public void clickOnSetting()
	{
		WebElement setting = getDriver().findElement(By.cssSelector(".dropdown-toggle.icon-settings"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".dropdown-toggle.icon-settings")));
		setting.click();
	}

	@Test
	public void navigateLandingPage() throws Exception
	{
		navigateLeftMenu();
		navigateMenuAtTopRight();
	}

	public void navigateLeftMenu() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToJackPot();
		navigateToEsportsBook();
		navigateToLuckyDice();
		navigateToCoinSlots();
		navigateToShop();
		navigateToMyItems();
		navigateToDepositSkins();
		navigateToAddCoins();
		navigateToRecruitFriends();
		navigateToLeaderBoard();
		navigateToContactSupport();
		navigateToCoinFlip();
	}

	public void navigateMenuAtTopRight()
	{
		navigateToCoinIcon();
		navigateToMessage();
		navigateSettingToAccSetting();
		navigateSettingToCoinHistory();
		navigateSettingToAboutEP();
	}

	public void navigateSettingToAboutEP()
	{
		clickOnSetting();
		navigateToTermOfUse();
		clickOnSetting();
		navigateToPrivacyPolicy();
		clickOnSetting();
		navigateToAboutUs();
	}

	public void navigateSettingToAccSetting()
	{
		clickOnSetting();
		WebElement accSetting = getDriver().findElement(By.cssSelector("ul > li:nth-child(2) a[data-route='userAccountSettings']"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul > li:nth-child(2) a[data-route='userAccountSettings']")));
		accSetting.click();
	}

	public void navigateSettingToCoinHistory()
	{
		clickOnSetting();
		WebElement coinHistory = getDriver().findElement(By.cssSelector("ul > li:nth-child(3) a[data-route='paymentVirtualPointHistory']"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul > li:nth-child(3) a[data-route='paymentVirtualPointHistory']")));
		coinHistory.click();
	}

	public void navigateToAboutUs()
	{
		Actions action = new Actions(getDriver());
		WebElement aboutEP = getDriver().findElement(By.cssSelector("ul > li:nth-child(4) a[href='#'] > span"));
		action.moveToElement(aboutEP).moveToElement(getDriver().findElement(By.id("user-menu-about-us"))).click();
	}

	public void navigateToAddCoins()
	{
		WebElement addCoins = getDriver().findElement(By.cssSelector(".has-image.icon-add-coins"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(addCoins));
		addCoins.click();
	}

	public void navigateToCoinFlip()
	{
		checkForCFHowToPlayPopup();
		WebElement coinFlip = getDriver().findElement(By.cssSelector("ul > li:nth-child(1) a[data-route='coinFlipLobby'] > span.coin-flip-joinable-count"));
		By id = By.id("ul > li:nth-child(1) a[data-route='coinFlipLobby'] > span.coin-flip-joinable-count");
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(coinFlip));
		coinFlip.click();
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(id));
		// Actions actions = new Actions(getDriver());
		// actions.moveToElement(coinFlip).click().perform();
	}

	public void navigateToCoinIcon()
	{
		WebElement coinIcon = getDriver().findElement(By.cssSelector(".icon-coins.money-text"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(coinIcon));
		coinIcon.click();
	}

	public void navigateToContactSupport()
	{
		WebElement contactSupport = getDriver().findElement(By.cssSelector(".has-image.icon-support"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(contactSupport));
		contactSupport.click();
	}

	public void navigateToMessage()
	{
		WebElement messageIcon = getDriver().findElement(By.cssSelector(".icon-messages"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(messageIcon));
		messageIcon.click();
	}

	public void navigateToMyItems()
	{
		WebElement myItems = getDriver().findElement(By.cssSelector(".has-image.icon-my-items"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(myItems));
		myItems.click();
	}

	public void navigateToPrivacyPolicy()
	{
		Actions action = new Actions(getDriver());
		WebElement aboutEP = getDriver().findElement(By.cssSelector("ul > li:nth-child(4) a[href='#'] > span"));
		action.moveToElement(aboutEP).moveToElement(getDriver().findElement(By.id("user-menu-terms-of-use"))).click();
	}

	public void navigateToRecruitFriends()
	{
		WebElement recruitFriends = getDriver().findElement(By.cssSelector(".has-image.icon-recruit-friends"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(recruitFriends));
		recruitFriends.click();
	}

	public void navigateToTermOfUse()
	{
		Actions action = new Actions(getDriver());
		WebElement aboutEP = getDriver().findElement(By.cssSelector("ul > li:nth-child(4) a[href='#'] > span"));
		action.moveToElement(aboutEP).moveToElement(getDriver().findElement(By.id("user-menu-privacy-policy"))).click();
	}
}
