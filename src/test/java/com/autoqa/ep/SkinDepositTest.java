package com.autoqa.ep;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class SkinDepositTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public SkinDepositTest(String baseUrl)
	{
		super(baseUrl);
	}

	@Test
	public void checkSkinDepositTest() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToDepositSkins();
		navigateToHistory();
		navigateToSell();
		loadInventory();
	}

	public void loadInventory()
	{
		WebElement loadButton = getDriver().findElement(By.cssSelector(".submit-btn.load-inventory.active-with-color"));
		loadButton.click();
	}

	public void navigateToHistory() throws Exception
	{
		WebElement history = getDriver().findElement(By.cssSelector("#app-top-menu-buttons>ul>li>a[data-route='sellSkinsSell']"));
		history.click();
		Thread.sleep(1000);
	}

	public void navigateToSell() throws Exception
	{
		WebElement history = getDriver().findElement(By.cssSelector(".active>a>span"));
		history.click();
		Thread.sleep(1000);
	}
}
