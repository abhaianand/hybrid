package testCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.Account_Register_page;
import pageObject.HomePage;

public class TC001_AccountRegistrerationTest extends BaseClass{
	@Test(priority=1,groups= {"sanity","master"})
	public void TC001_verify_account_registration(){
		try{
		logger.info("***test case started: TC001_AccountRegistrerationTest");
		HomePage hp = new HomePage(driver);
		Account_Register_page arp = new Account_Register_page(driver);
		hp.click_myaccount();
		logger.info("***clicks on my account***");
		hp.click_Register();
		logger.info("***clicks on my Register***");
		arp.setFirstName(autoGenerateAlphabeticalData().toUpperCase());
		arp.setLastName(autoGenerateAlphabeticalData().toUpperCase());
		arp.setEmail(autoGenerateAlphabeticalData()+"@gmail.com");
		//arp.setTelephoneNumber(autoGenerateNumb());
		String password = autoGenerateAlphabeticalData();
		arp.setPassword(password+"123");
		//arp.setPasswordConfirm(password+"123");
		arp.clicks_subscribe_toggle_button();
		arp.click_policy_togglebutton();
		arp.click_continue_button();
		logger.info("***clicks on continue_button***");
		System.out.print(arp.validationmessage_success_registration());
	    Assert.assertEquals(arp.validationmessage_success_registration(),"Your Account Has Been Created!");
	    logger.info("***testcase passed***");}
		 // Handle if assert is false due to any unexpected result
	    catch(AssertionError  ae){
		logger.error("*******test case failed*******",ae);
		Assert.fail("Test case failed due to exception: " + ae.getMessage());
		}
		finally {
		logger.info("***testcase finished***");
		}}
	}
