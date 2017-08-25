package com.autoqa.ep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class ShopNavTest extends EPTest
{

	/**
	 * @param baseUrl
	 */
	public ShopNavTest(String baseUrl)
	{
		super(baseUrl);
	}

	@Test
	public void checkShopNavigation() throws Exception
	{
		checkForLoginBonusPopup();
		navigateToShop();
		navigateGamesType();
		navigateSortType();
	}

	public void navigateAllGames()
	{
		WebElement allGames = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(allGames));
		allGames.click();
	}

	public void navigateCSGO()
	{
		WebElement csgo = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[2]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(csgo));
		csgo.click();
	}

	public void navigateDota()
	{
		WebElement dota = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[3]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(dota));
		dota.click();
	}

	public void navigateGamesType() throws Exception
	{
		selectGamesDropDown();
		navigateCSGO();
		selectGamesDropDown();
		navigateDota();
		selectGamesDropDown();
		navigateOther();
		selectGamesDropDown();
		navigateOnSale();
		selectGamesDropDown();
		navigateAllGames();
	}

	public void navigateNameAToZ()
	{
		WebElement nameAToZ = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[4]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(nameAToZ));
		nameAToZ.click();
	}

	public void navigateNameZToA()
	{
		WebElement nameZToA = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[5]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(nameZToA));
		nameZToA.click();
	}

	public void navigateOnSale()
	{
		WebElement onSale = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[5]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(onSale));
		onSale.click();
	}

	public void navigateOther()
	{
		WebElement other = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[4]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(other));
		other.click();
	}

	public void navigatePriceHToL()
	{
		WebElement priceHToL = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[3]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(priceHToL));
		priceHToL.click();
	}

	public void navigatePriceLToH()
	{
		WebElement priceLToH = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[2]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(priceLToH));
		priceLToH.click();
	}

	public void navigateSortType() throws Exception
	{
		selectSortDropDown();
		navigatePriceLToH();
		selectSortDropDown();
		navigatePriceHToL();
		selectSortDropDown();
		navigateNameAToZ();
		selectSortDropDown();
		navigateNameZToA();
		selectSortDropDown();
		navigateUnsorted();
	}

	public void navigateUnsorted()
	{
		WebElement unsorted = getDriver().findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(unsorted));
		unsorted.click();
	}

	public void selectGamesDropDown() throws Exception
	{
		WebElement games = getDriver().findElement(By.xpath(".//*[@id='virtual-item-list-search']/div[2]/i"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(games));
		Thread.sleep(1000);
		games.click();
	}

	public void selectSortDropDown() throws Exception
	{
		WebElement sort = getDriver().findElement(By.xpath(".//*[@id='virtual-item-list-search']/div[3]/i"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(sort));
		Thread.sleep(1000);
		sort.click();
	}
}
