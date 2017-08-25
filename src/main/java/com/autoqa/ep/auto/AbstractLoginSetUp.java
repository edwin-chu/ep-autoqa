package com.autoqa.ep.auto;

import org.testng.annotations.*;

/**
 * AbstractLoginSetUp //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Chu, Edwin
 * @version 1.0.0
 * @since 1.0
 */
public abstract class AbstractLoginSetUp extends BaseSetUp
{

	/**
	 * @param baseUrl
	 */
	public AbstractLoginSetUp(String baseUrl)
	{
		super(baseUrl);
	}

	abstract public void login(String username, String password) throws Exception;

	abstract public void logout();

	@BeforeClass
	public void setupLogin() throws Exception
	{
		getDriver().get(getBaseURL());
		login(getProp("username"), getProp("password"));
	}

	@AfterClass
	public void setupLogout()
	{
		logout();
	}
}
