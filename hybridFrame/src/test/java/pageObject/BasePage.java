package pageObject;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	WebDriver driver;
	
	//constructor
	public BasePage(WebDriver driver){
		this.driver = driver;	
	}	
}
