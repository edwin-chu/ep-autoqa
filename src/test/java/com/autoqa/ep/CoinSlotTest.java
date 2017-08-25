package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class CoinSlotTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public CoinSlotTest(String baseUrl)
	{
		super(baseUrl);
	}

	public void betFifthCategory() throws Exception
	{
		WebElement fifthCategory = getDriver().findElement(By.xpath(".//*[@id='iscroll-wrapper']/div/div[5]/div[2]/button"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(fifthCategory));
		Thread.sleep(5000);
		fifthCategory.click();
	}

	public void betFirstCategory() throws Exception
	{
		WebElement firstCategory = getDriver().findElement(By.xpath(".//*[@id='iscroll-wrapper']/div/div[1]/div[2]/button"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(firstCategory));
		Thread.sleep(1000);
		firstCategory.click();
	}

	public void betFourthCategory() throws Exception
	{
		WebElement fourthCategory = getDriver().findElement(By.xpath(".//*[@id='iscroll-wrapper']/div/div[4]/div[2]/button"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(fourthCategory));
		Thread.sleep(5000);
		fourthCategory.click();
	}

	public void betSecondCategory() throws Exception
	{
		WebElement secondCategory = getDriver().findElement(By.xpath(".//*[@id='iscroll-wrapper']/div/div[2]/div[2]/button"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(secondCategory));
		Thread.sleep(5000);
		secondCategory.click();
	}

	public void betThirdCategory() throws Exception
	{
		WebElement thirdCategory = getDriver().findElement(By.xpath(".//*[@id='iscroll-wrapper']/div/div[3]/div[2]/button"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(thirdCategory));
		Thread.sleep(5000);
		thirdCategory.click();
	}

	@Test
	public void checkCoinSlot() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToCoinSlots();
		checkCoinSlotHowToLink();
		checkCoinSlotGamePlay();
	}

	public void checkCoinSlotGamePlay() throws Exception
	{
		betFirstCategory();
		betSecondCategory();
		betThirdCategory();
		betFourthCategory();
		betFifthCategory();
	}

	public void checkCoinSlotHowToLink()
	{
		WebElement csHowTo = getDriver().findElement(By.id("mystery_boxes_how_to"));
		csHowTo.click();
		WebElement okButton = getDriver().findElement(By.id("dfs-button-0"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(okButton));
		okButton.click();
	}
}
