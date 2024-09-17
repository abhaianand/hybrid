package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	//locators
	String input_email_xpath = "//input[@id='input-email']";
	String input_password_xpath = "//input[@id='input-password']";
	String button_login_xpath = "//button[@type='submit']";
	
	//actionmethods
	
	public void sendusername(String username){
		driver.findElement(By.xpath(input_email_xpath)).clear();
		
		driver.findElement(By.xpath(input_email_xpath)).sendKeys(username);
	}
   public void sendpassword(String password){
	   driver.findElement(By.xpath(input_password_xpath)).clear();
		
		driver.findElement(By.xpath(input_password_xpath)).sendKeys(password);
	}
   public void clicksLogin_button(){
		
		driver.findElement(By.xpath(button_login_xpath)).click();
	}
	
	
	
	
	
	

}
