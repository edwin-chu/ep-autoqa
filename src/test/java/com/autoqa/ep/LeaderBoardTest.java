package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class LeaderBoardTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public LeaderBoardTest(String baseUrl)
	{
		super(baseUrl);
	}

	@Test
	public void navigateLeaderBoardTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToLeaderBoard();
		setGames();
	}

	public void selectDuration() throws Exception
	{
		Thread.sleep(5000);
		WebElement duration = getDriver().findElement(By.id("period-filter"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(duration));
		Thread.sleep(1000);
		duration.click();
	}

	public void selectGames() throws Exception
	{
		WebElement allGames = getDriver().findElement(By.id("game-filter"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(allGames));
		Thread.sleep(1000);
		allGames.click();
	}

	public void setAllGames()
	{
		WebElement ag = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(ag));
		ag.click();
	}

	public void setCoinFlip()
	{
		WebElement cf = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[2]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(cf));
		cf.click();
	}

	public void setCoinSlots()
	{
		WebElement cs = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[6]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(cs));
		cs.click();
	}

	public void setDuration() throws Exception
	{
		selectDuration();
		setTwentyFourHours();
		selectDuration();
		setSevenDays();
		selectDuration();
		setThirtyDays();
	}

	public void setEsportsBook()
	{
		WebElement esb = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[4]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(esb));
		esb.click();
	}

	public void setGames() throws Exception
	{
		setDuration();
		selectGames();
		setCoinFlip();
		setDuration();
		selectGames();
		setJackPot();
		setDuration();
		selectGames();
		setEsportsBook();
		setDuration();
		selectGames();
		setLuckyDice();
		setDuration();
		selectGames();
		setCoinSlots();
		setDuration();
		selectGames();
		setSkinDeposits();
		setDuration();
		setAllGames();
	}

	public void setJackPot()
	{
		WebElement jp = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[3]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(jp));
		jp.click();
	}

	public void setLuckyDice()
	{
		WebElement ld = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[5]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(ld));
		ld.click();
	}

	public void setSevenDays()
	{
		WebElement sevenDays = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[2]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(sevenDays));
		sevenDays.click();
	}

	public void setSkinDeposits()
	{
		WebElement sd = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[7]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(sd));
		sd.click();
	}

	public void setThirtyDays()
	{
		WebElement thirtyDays = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[3]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(thirtyDays));
		thirtyDays.click();
	}

	public void setTwentyFourHours()
	{
		WebElement twentyFour = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(twentyFour));
		twentyFour.click();
	}
}
