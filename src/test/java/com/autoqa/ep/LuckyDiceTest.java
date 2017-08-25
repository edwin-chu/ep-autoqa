package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class LuckyDiceTest extends EPTest
{

	private static String wager = "1000";

	/**
	 * @param baseUrl
	 */
	public LuckyDiceTest(String baseUrl)
	{
		super(baseUrl);
	}

	public void betOneThousand()
	{
		WebElement wagerField = getDriver().findElement(By.id("luckyDice-input-wager"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(wagerField));
		wagerField.clear();
		wagerField.sendKeys(wager);
	}

	public void checkLDGamePlay() throws Exception
	{
		selectFiftyPercent();
		minBet();
		selectSingleRoll();
		selectTwentyFivePercent();
		selectSingleRoll();
		selectTenPercent();
		selectSingleRoll();
		selectFivePercent();
		selectSingleRoll();
		betOneThousand();
		selectSingleRoll();
		minBet();
		twoxBet();
		selectAutoRoll();
		Thread.sleep(5000);
		selectAutoRoll();
	}

	public void checkLDHowToLink()
	{
		WebElement ldHowTo = getDriver().findElement(By.id("lucky_dice_how_to"));
		ldHowTo.click();
		WebElement okButton = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(okButton));
		okButton.click();
	}

	@Test
	public void checkLuckyDice() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToLuckyDice();
		navigateToProvablyFair();
		navigateBackToLuckyDice();
		checkLDHowToLink();
		checkLDGamePlay();
	}

	public void maxBet() throws Exception
	{
		WebElement max = getDriver().findElement(By.cssSelector(".esp-max.mdl-button.option.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.max-bet"));
		Thread.sleep(1000);
		max.click();
	}

	public void minBet() throws Exception
	{
		WebElement min = getDriver().findElement(By.cssSelector(".esp-min.mdl-button.option.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.min-bet"));
		Thread.sleep(1000);
		min.click();
	}

	public void navigateBackToLuckyDice() throws Exception
	{
		WebElement ld = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='luckyDice']>span"));
		Thread.sleep(1000);
		ld.click();
	}

	public void navigateToProvablyFair() throws Exception
	{
		WebElement fair = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='luckyDiceProvablyFair']>span"));
		Thread.sleep(1000);
		fair.click();
	}

	public void selectAutoRoll() throws Exception
	{
		WebElement auto = getDriver().findElement(By.xpath(".//*[@id='lucky-dice-body-scroller']/div[3]/div[3]/div/div/div"));
		auto.click();
	}

	public void selectFiftyPercent() throws Exception
	{
		WebElement fifty = getDriver().findElement(By.xpath(".//*[@id='lucky-dice-body-scroller']/div[1]/div/div[1]/button"));
		Thread.sleep(1000);
		fifty.click();
	}

	public void selectFivePercent() throws Exception
	{
		WebElement five = getDriver().findElement(By.xpath(".//*[@id='lucky-dice-body-scroller']/div[1]/div/div[4]/button"));
		Thread.sleep(1000);
		five.click();
	}

	public void selectSingleRoll()
	{
		WebElement single = getDriver().findElement(By.xpath(".//*[@id='lucky-dice-body-scroller']/div[3]/div[2]/div/div"));
		single.click();
	}

	public void selectTenPercent() throws Exception
	{
		WebElement ten = getDriver().findElement(By.xpath(".//*[@id='lucky-dice-body-scroller']/div[1]/div/div[3]/button"));
		Thread.sleep(1000);
		ten.click();
	}

	public void selectTwentyFivePercent() throws Exception
	{
		WebElement twentyFive = getDriver().findElement(By.xpath(".//*[@id='lucky-dice-body-scroller']/div[1]/div/div[2]/button"));
		Thread.sleep(1000);
		twentyFive.click();
	}

	public void twoxBet() throws Exception
	{
		WebElement twox = getDriver().findElement(By.cssSelector(".esp-2x.mdl-button.option.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.double-bet"));
		Thread.sleep(1000);
		twox.click();
	}
}
