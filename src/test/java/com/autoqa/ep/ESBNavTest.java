package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class ESBNavTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public ESBNavTest(String baseUrl)
	{
		super(baseUrl);
	}

	public void backToLobby() throws Exception
	{
		WebElement lobby = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='sportsbookLobby']>span"));
		lobby.click();
		Thread.sleep(1000);
	}

	@Test
	public void navigateESBTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToEsportsBook();
		navigateTopMenu();
		navigateGamesPage();
	}

	public void navigateGamesPage() throws Exception
	{
		Thread.sleep(1000);
		navigateToCSGO();
		backToLobby();
		navigateToLOL();
		backToLobby();
		navigateToDT2();
		backToLobby();
		navigateToCOD();
		backToLobby();
		navigateToOtherEsports();
		backToLobby();
		navigateToSports();
		backToLobby();
	}

	public void navigateToCOD()
	{
		WebElement cod = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[4]"));
		cod.click();
	}

	public void navigateToCompleted() throws Exception
	{
		WebElement completed = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='completedContests']>span"));
		completed.click();
		Thread.sleep(1000);
	}

	public void navigateToCSGO() throws Exception
	{
		WebElement csgo = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[1]"));
		csgo.click();
		WebElement coin = getDriver().findElement(By.id("contest-list-pc-coin-tab"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(coin));
		Thread.sleep(1000);
		coin.click();
		WebElement skin = getDriver().findElement(By.id("contest-list-pc-skin-tab"));
		WebDriverWait wait2 = new WebDriverWait(getDriver(), 10);
		wait2.until(ExpectedConditions.elementToBeClickable(coin));
		skin.click();
	}

	public void navigateToDT2()
	{
		WebElement dt2 = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[3]"));
		dt2.click();
	}

	public void navigateToLive() throws Exception
	{
		WebElement live = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='liveContests']>span"));
		live.click();
		Thread.sleep(1000);
	}

	public void navigateToLOL()
	{
		WebElement lol = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[2]"));
		lol.click();
	}

	public void navigateToOtherEsports()
	{
		WebElement otherEsports = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[5]"));
		otherEsports.click();
	}

	public void navigateTopMenu() throws Exception
	{
		navigateToUpcoming();
		navigateToLive();
		navigateToCompleted();
		backToLobby();
	}

	public void navigateToSports()
	{
		WebElement sports = getDriver().findElement(By.xpath(".//*[@id='touch-zones']/a[6]"));
		sports.click();
	}

	public void navigateToUpcoming() throws Exception
	{
		WebElement upcoming = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='upcomingContests']>span"));
		upcoming.click();
		Thread.sleep(1000);
	}
}
