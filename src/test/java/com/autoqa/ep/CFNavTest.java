package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class CFNavTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public CFNavTest(String baseUrl)
	{
		super(baseUrl);
	}

	public void backToCFMain() throws Exception
	{
		WebElement back = getDriver().findElement(By.cssSelector(".glyphicon.glyphicon-menu-left.back-btn"));
		back.click();
		Thread.sleep(1000);
		back.click();
	}

	public void checkAcceptBetTypeLink() throws Exception
	{
		boolean exist1 = getDriver().findElements(By.id("acceptBetTypeHelp")).size() > 0;
		System.out.println("Accept Bet Type Help Element Status: " + exist1);
		WebElement betTypeHowTo = getDriver().findElement(By.id("acceptBetTypeHelp"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(betTypeHowTo));
		Thread.sleep(1000);
		betTypeHowTo.click();
		System.out.println("Accept Bet Type Link is clicked");
		Thread.sleep(1000);
		boolean exist2 = getDriver().findElements(By.cssSelector(".esp-submit.button.btn-default.primary")).size() > 0;
		System.out.println("OK Button Element Status: " + exist2);
		getDriver().findElement(By.cssSelector(".esp-submit.button.btn-default.primary")).click();
		System.out.println("OK button is clicked");
	}

	public void checkCFHowToLink()
	{
		WebElement cfHowTo = getDriver().findElement(By.id("coin_flip_how_to"));
		cfHowTo.click();
		WebElement okButton = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(okButton));
		okButton.click();
	}

	public void checkCFLinks() throws Exception
	{
		checkCFHowToLink();
		checkCreateGameTypeLink();
		checkAcceptBetTypeLink();
		checkVarianceLink();
		checkExpLink();
	}

	public void checkCreateGameOptions() throws Exception
	{
		selectASides();
		// setGameType();
		// setBetType();
		// setVariance();
		// setExpiration();
		selectCFContinue();
		backToCFMain();
	}

	public void checkCreateGameTypeLink() throws Exception
	{
		boolean exist1 = getDriver().findElements(By.id("createGameTypeHelp")).size() > 0;
		System.out.println("Create Game Type Help Element Status: " + exist1);
		WebElement gameTypeHowTo = getDriver().findElement(By.id("createGameTypeHelp"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(gameTypeHowTo));
		Thread.sleep(1000);
		gameTypeHowTo.click();
		System.out.println("Create Game Type Link is clicked");
		Thread.sleep(1000);
		boolean exist2 = getDriver().findElements(By.cssSelector(".modal-title>strong")).size() > 0;
		System.out.println("OK Button Element Status: " + exist2);
		getDriver().findElement(By.cssSelector(".esp-submit.button.btn-default.primary")).click();
		System.out.println("OK button is clicked");
	}

	public void checkExpLink() throws Exception
	{
		boolean exist1 = getDriver().findElements(By.id("expirationHelp")).size() > 0;
		System.out.println("Expiration Help Element Status: " + exist1);
		WebElement expHowTo = getDriver().findElement(By.id("expirationHelp"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(expHowTo));
		Thread.sleep(1000);
		expHowTo.click();
		System.out.println("Expiration Link is clicked");
		Thread.sleep(1000);
		boolean exist2 = getDriver().findElements(By.cssSelector(".modal-title>strong")).size() > 0;
		System.out.println("OK Button Element Status: " + exist2);
		getDriver().findElement(By.cssSelector(".esp-submit.button.btn-default.primary")).click();
		System.out.println("OK button is clicked");
	}

	public void checkVarianceLink() throws Exception
	{
		boolean exist1 = getDriver().findElements(By.id("opponentVarianceHelp")).size() > 0;
		System.out.println("Variance Help Element Status: " + exist1);
		WebElement varianceHowTo = getDriver().findElement(By.id("opponentVarianceHelp"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(varianceHowTo));
		Thread.sleep(1000);
		varianceHowTo.click();
		System.out.println("Variance Help Link is clicked");
		Thread.sleep(1000);
		boolean exist2 = getDriver().findElements(By.cssSelector(".modal-title>strong")).size() > 0;
		System.out.println("OK Button Element Status: " + exist2);
		getDriver().findElement(By.cssSelector(".esp-submit.button.btn-default.primary")).click();
		System.out.println("OK button is clicked");
	}

	@Test
	public void navigateCFTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToHistory();
		navigateToProvablyFair();
		navigateToLobby();
		createNewGame();
		checkCFLinks();
		checkCreateGameOptions();
	}

	public void navigateToHistory() throws Exception
	{
		WebElement history = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='coinFlipHistory']>span"));
		history.click();
		Thread.sleep(1000);
	}

	public void navigateToLobby() throws Exception
	{
		WebElement lobby = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='coinFlipLobby']>span"));
		lobby.click();
		Thread.sleep(1000);
	}

	public void navigateToProvablyFair() throws Exception
	{
		WebElement provablyFair = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='coinFlipProvablyFair']>span"));
		provablyFair.click();
		Thread.sleep(1000);
	}

	public void selectCFContinue()
	{
		WebElement continueButton = getDriver().findElement(By.id("btn-continue"));
		continueButton.click();
	}
}
