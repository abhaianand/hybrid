package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC002_Login_Test extends BaseClass {
	
	@Test(groups= {"regression","master"})
	public void TC002_LoginTest (){
		try{
		logger.info("****test started TC002_Login_Test");
		HomePage hp = new HomePage(driver);
		hp.click_myaccount();
		logger.info("****click_myaccount");
		hp.click_Login();
		logger.info("***click_Login***");
	    LoginPage lp = new LoginPage(driver);
	    lp.sendusername(p.getProperty("admin_username"));
	    logger.info("***sendusername***");
	    lp.sendpassword(p.getProperty("admin_password"));
	    logger.info("***sendpassword***");
	    lp.clicksLogin_button();
	    logger.info("***clicksLogin_button***");
	    MyAccountPage myaccount = new MyAccountPage(driver);
	    boolean status =   myaccount.isMyAccountpageExists();
	    Assert.assertEquals(status, true);
	    logger.info("***test case passed_TC002_Login_Test***");
		}
		 catch(AssertionError  ae){
			 
			 logger.info("***test failed***"+ae.getMessage());
			 Assert.fail("Test case failed due to exception: " + ae.getMessage());
		}
		finally{
			logger.info("****test completed****");
		}	
	}
}
