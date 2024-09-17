package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class Account_Register_page extends BasePage {
	//constructor
	public Account_Register_page(WebDriver driver){	
		super(driver);
	}
	//elements
	//Your Personal Details
	String inputBox_FirstName_xpath = "//input[@id='input-firstname']";
	String inputBox_LastName_xpath = "//input[@id='input-lastname']";
	String inputBox_EMail_xpath = "//input[@id='input-email']";
	//String inputBox_Telephone_xpath = "//input[@id='input-email']";
	//Your Password
	String inputBox_Password_xpath = "//input[@id='input-password']";
	//String inputBox_Password_Confirm_xpath = "//input[@id='input-confirm']";
	//Newsletter
	String toggle_button_Subscribe__xpath = "//input[@id='input-newsletter']";
	//String toggle_button_Subscribe_No_xpath = "//div[@class=\"col-sm-10\"]//label[2]//input[@name=\"newsletter\"]";
	//policy_box
	String toggle_button_policy_xpath = "//input[@name='agree']";
	//continue_button
	String button_continue_xpath = "//button[normalize-space()='Continue']";
	//success validation message
	String text_success_validation_message_xpath = "//h1[normalize-space()='Your Account Has Been Created!']";
	//methods
	//Your Personal Details
	public void setFirstName(String FirstName){
		driver.findElement(By.xpath(inputBox_FirstName_xpath)).sendKeys(FirstName);}
	public void setLastName(String LastName){
		driver.findElement(By.xpath(inputBox_LastName_xpath)).sendKeys(LastName);}
	public void setEmail(String Email){
		driver.findElement(By.xpath(inputBox_EMail_xpath)).sendKeys(Email);}
	//public void setTelephoneNumber(String Telephone){
		//driver.findElement(By.xpath(inputBox_Telephone_xpath)).sendKeys(Telephone);}
	////Your Password
	public void setPassword(String Password){
		driver.findElement(By.xpath(inputBox_Password_xpath)).sendKeys(Password);}
	//public void setPasswordConfirm(String Password){
		//driver.findElement(By.xpath(inputBox_Password_Confirm_xpath)).sendKeys(Password);}
	//Newsletter
	public void clicks_subscribe_toggle_button(){
		driver.findElement(By.xpath(toggle_button_Subscribe__xpath)).click();}
	//public void set_radio_button_Subscribe_No(){
			//driver.findElement(By.xpath(radio_button_Subscribe_No_xpath)).click();}
	//policy_box
	public void click_policy_togglebutton(){
		driver.findElement(By.xpath(toggle_button_policy_xpath)).click();}
	//continue_button
	public void click_continue_button(){
		driver.findElement(By.xpath(button_continue_xpath)).click();}
	//success validation message
	
	public  String validationmessage_success_registration(){
		try {
		String validation_message = driver.findElement(By.xpath(text_success_validation_message_xpath)).getText();
		return (validation_message);
	        }
		catch(Exception e) {
		return (e.getMessage());}
	 
	 }
	}
	
	
	
	
	
	
	
	
	

	
