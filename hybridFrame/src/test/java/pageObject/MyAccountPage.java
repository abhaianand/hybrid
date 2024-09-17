package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	///locators
	String text_myaccount_xpath ="//h2[normalize-space()='My Account']";
	String _text_logout_xpath ="//a[@class='list-group-item'][normalize-space()='Logout']";
	String button_continue_xpath = "//a[@class='btn btn-primary']";
   //actionmethod

    public boolean isMyAccountpageExists() {
	boolean elemntstatus=driver.findElement(By.xpath(text_myaccount_xpath)).isEnabled();
	return elemntstatus;	
    }
    public void clicks_logout() {
    	driver.findElement(By.xpath(_text_logout_xpath)).click();
    }
    public void clicks_continue() {
    	driver.findElement(By.xpath(button_continue_xpath)).click();
    }
    }