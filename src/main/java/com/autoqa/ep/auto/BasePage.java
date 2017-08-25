package com.autoqa.ep.auto;

import org.openqa.selenium.support.*;

/**
 * BasePage //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public class BasePage extends BaseHelpers
{

	public BasePage(BaseSetUp test)
	{
		super(test);
		PageFactory.initElements(getDriver(), this);
	}
}
