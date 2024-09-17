package testCase;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.io.File; // Import for file handling

public class BaseClass {
	
	public static WebDriver driver; // should be static bcz during extend report manger on test fail,we are using instance of the base class,
	public Logger logger;            //so if the driver is not static it will create a duplicate(copy of driver) which will make confusion for the program
	public Properties p;
	
	@BeforeClass(groups= {"master","sanity","regression"})
	@Parameters("browser")
	 public void setup(String br) throws IOException{
		
	    //log
		logger = LogManager.getLogger(this.getClass());
		switch(br.toLowerCase()){
		case "chrome": driver = new ChromeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "safari": driver = new SafariDriver(); break;
		default:System.out.print("invalid browser name");
		return; //if the browser name fails it return and exit further execution
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		driver.get(p.getProperty("adminpanel_url"));
		driver.manage().window().maximize();	
	}
	@AfterClass(groups= {"master","sanity","regression"})
	public void tearDown(){	
		driver.quit();	
	}
	//dynamicTestData //random data // apache common .io
       public String autoGenerateAlphabeticalData() {
	   String GenerateAlphabetica = RandomStringUtils.randomAlphabetic(5);
	   return GenerateAlphabetica;
			}
       public String autoGenerateNumb(){
	   String GenerateNumb = RandomStringUtils.randomNumeric(10);
	   return GenerateNumb;	
			}
       
       public String captureScreen(String tname) throws IOException {

    	    // Create a timestamp with the format "yyyyMMddhhmmss" to ensure each screenshot file has a unique name
    	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

    	    // TakesScreenshot is a WebDriver interface for capturing a screenshot
    	    // Casting the driver to TakesScreenshot to use its getScreenshotAs method
    	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

    	    // Capture the screenshot and store it as a file object in the sourceFile variable
    	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

    	    // Set the target file path where the screenshot will be saved, including the test name and timestamp
    	    //String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
    	    //C:\Users\abhai\eclipse-workspace\hybrid\hybridFrame\screenshots
    	    String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
    	    File targetFile = new File(targetFilePath);

    	    // Rename the source file to the target file path
    	    sourceFile.renameTo(targetFile);

    	    // Return the path of the saved screenshot for later use in reports
    	    return targetFilePath;
    	}

}
