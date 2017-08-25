package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class JPNavTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public JPNavTest(String baseUrl)
	{
		super(baseUrl);
	}

	public void backToJPMain()
	{
		WebElement back = getDriver().findElement(By.cssSelector(".glyphicon.glyphicon-menu-left.back-btn"));
		back.click();
	}

	public void checkJPHowToLink()
	{
		WebElement cfHowTo = getDriver().findElement(By.id("skin_jackpot_how_to"));
		cfHowTo.click();
		WebElement okButton = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait1 = new WebDriverWait(getDriver(), 10);
		wait1.until(ExpectedConditions.elementToBeClickable(okButton));
		okButton.click();
	}

	public void depositItem() throws Exception
	{
		WebElement deposit = getDriver().findElement(By.cssSelector(".btn.btn-default.recent-skin-button.deposit"));
		Thread.sleep(1000);
		deposit.click();
	}

	public void navigateBackToJP() throws Exception
	{
		WebElement jp = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='skinJackpotHome']>span"));
		jp.click();
		Thread.sleep(1000);
	}

	@Test
	public void navigateJPTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToJackPot();
		navigateToProvablyFair();
		navigateBackToJP();
		checkJPHowToLink();
		depositItem();
		backToJPMain();
	}

	public void navigateToProvablyFair() throws Exception
	{
		WebElement provablyFair = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='skinJackpotProvablyFair']>span"));
		provablyFair.click();
		Thread.sleep(1000);
	}
}
