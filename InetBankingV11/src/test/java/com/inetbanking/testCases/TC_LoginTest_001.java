package com.inetbanking.testCases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
	{

	@Test
	public void loginTest() throws IOException
	{
		logger.info("url opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Enterd username");
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickSubmit();
		logger.info("sumitted");
		
		if(driver.getTitle().contains("Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("login test failed");
		}
	}
		
}
