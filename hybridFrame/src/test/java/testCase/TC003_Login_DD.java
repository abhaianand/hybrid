package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utility.DataProviders;


public class TC003_Login_DD extends BaseClass{
	
	@Test(dataProvider="logindata1",dataProviderClass=DataProviders.class,groups= {"sanity","regression","master"})
	public void TC003_LoginTest_DD (String email,String password,String data_status) throws InterruptedException{
	try{
		logger.info("****test started TC002_Login_Test");
		HomePage hp = new HomePage(driver);
		hp.click_myaccount();
		logger.info("****click_myaccount");
		hp.click_Login();
		Thread.sleep(5000);
		logger.info("***click_Login***");
	    LoginPage lp = new LoginPage(driver);
	    lp.sendusername(email);
	    logger.info("***sendusername***");
	    Thread.sleep(2000);
	    lp.sendpassword(password);
	    logger.info("***sendpassword***");
	    Thread.sleep(2000);
	    lp.clicksLogin_button();
	    Thread.sleep(2000);
	    logger.info("***clicksLogin_button***");
	     MyAccountPage myaccount = new MyAccountPage(driver);
	     boolean status =   myaccount.isMyAccountpageExists(); //if login it will return true
	     System.out.print(status);
	    //valid data -login pass - test pass
	    //valid data - login fail - test fail
	    //invalid data - login pass - test fail
	    //invalid data - login fail - test pass
	   if (data_status.equalsIgnoreCase("Valid")){
	    	if(status==true){
	 	     myaccount.clicks_logout();
	 	    Thread.sleep(3000);
	 	     myaccount.clicks_continue();
	 	    logger.info("***clicks logout***");
	 	    logger.info("***TC003_LoginTest_DD_testcase passed***");}
	    else{
	 	    logger.info("***TC003_LoginTest_DD_testcase failed***");
	 	     Assert.fail();}
	    	}
	     else if (data_status.equalsIgnoreCase("Invalid")){
	 		 if(status==true) {
		 	    myaccount.clicks_logout();
		 	   myaccount.clicks_continue();
		 	   logger.info("***clicks logout***");
		 	    logger.info("***TC003_LoginTest_DD_testcase failed***");
		 	     Assert.fail();}
	 	 else{
	 	     logger.info("***TC003_LoginTest_DD_testcase passed***");
	 	     Assert.fail();
	 	    }} 
	} catch(AssertionError ae){
		 logger.info("***test failed***"+ae.getMessage());
		 Assert.fail("Test case failed due to exception: " + ae.getMessage());}
	finally{
		logger.info("****test completed****");}	
	 	 }}
		
			
		


