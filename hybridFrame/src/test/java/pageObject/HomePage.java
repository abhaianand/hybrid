package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage extends BasePage{
	//constructor
	public HomePage(WebDriver driver){
		 super(driver);
	}
	//elements
	//action methods
	//elements
	String myaccount_xpath = "//span[normalize-space()='My Account']";
	String dropdown_Register_xpath = "//a[normalize-space()='Register']";
	String dropdown_Login_xpath = "//a[normalize-space()='Login']";
	//action methods
	public void click_myaccount(){
		driver.findElement(By.xpath(myaccount_xpath)).click();}
	 public void click_Register(){
		 driver.findElement(By.xpath(dropdown_Register_xpath)).click();}
	 public void click_Login(){
		 driver.findElement(By.xpath(dropdown_Login_xpath)).click();}	
}
