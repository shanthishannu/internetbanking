package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
 
public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertPresent()==true) // Nagitive input
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			Thread.sleep(3000);
			logger.info("Login failed");
		}
		else
			Assert.assertTrue(true);
			lp.clickLogout(); //click logout link
			driver.switchTo().alert().accept(); // logout link alert
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			logger.info("Login successfull");
		}
	
		
	public boolean isAlertPresent()
	{
	try
	{
	driver.switchTo().alert();
	return true;
	}
	catch(NoAlertPresentException e)
	{
		return false;
	}
	}
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		//code for read data from excel tehn store into 2 dim array
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
			
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) //rows
		{
			for(int j=0;j<colcount;j++) // colms
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);  //1 0
			}
		}
		
		return logindata;
		
	}	
	
	
	
}
