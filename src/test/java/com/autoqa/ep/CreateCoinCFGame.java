package com.autoqa.ep;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class CreateCoinCFGame extends EPTest
{

	private String amount = "5000";	// default amount for coin cf game creation

	/**
	 * @param baseUrl
	 */
	public CreateCoinCFGame(String baseUrl)
	{
		super(baseUrl);
	}

	public void betCoin()
	{
		WebElement amountField = getDriver().findElement(By.id("coinFlipCreateGameAddCoins-input-wager"));
		amountField.clear();
		amountField.sendKeys(this.amount);
	}

	@Test
	public void coinCFGameTest() throws Exception
	{
		checkForLoginBonusPopup();
		createNewGame();
		setupCFOptions();
		selectContinueBtn();
		betCoin();
		selectCreateGame();
	}

	public void selectContinueBtn()
	{
		WebElement continueButton = getDriver().findElement(By.id("btn-continue"));
		continueButton.click();
	}

	// click on CREATE GAME button
	public void selectCreateGame()
	{
		WebElement createBtn = getDriver().findElement(By.cssSelector(".coin-flip.coin-flip-new"));
		createBtn.click();
	}

	// call after setCreateGameType. default after the call is Coin. Right click
	// once to get to Either setting
	public void setAcceptBetType()
	{
		WebElement rightArrowBetType = getDriver().findElement(By.xpath(".//*[@id='coinFlipCreateGameBody-scroller']/div[3]/div[1]/div[2]/div[2]/div/button[2]"));
		rightArrowBetType.click();
	}

	// set Create Game Type to Coin
	public void setCreateGameType()
	{
		WebElement rightArrowGameType = getDriver().findElement(By.xpath(".//*[@id='coinFlipCreateGameBody-scroller']/div[3]/div[1]/div[1]/div[2]/div/button[2]"));
		rightArrowGameType.click();
	}

	// will use default for now
	public void setExpiration()
	{
		System.out.println("Will use default setting for now");
	}

	// will use default for now
	public void setOpponentVariance()
	{
		System.out.println("Will use default setting for now");
	}

	public void setupCFOptions() throws Exception
	{
		selectASides();
		setCreateGameType();
		setAcceptBetType();
		setOpponentVariance();
		setExpiration();
	}
}
