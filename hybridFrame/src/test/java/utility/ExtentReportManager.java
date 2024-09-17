package utility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	// This method runs at the start of the test execution
	public void onStart(ITestContext testContext) {
		// Creating a timestamp to ensure the report name is unique
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";  // Report name with timestamp

		// Initializing the SparkReporter and setting its properties
		//C:\Users\abhai\eclipse-workspace\hybrid\hybridFrame\report
		//.\\report\\
		sparkReporter = new ExtentSparkReporter("C:\\Users\\abhai\\eclipse-workspace\\hybrid\\hybridFrame\\report\\" + repName); // Location of report
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Setting report title
		sparkReporter.config().setReportName("Opencart Functional Testing"); // Report name in UI
		sparkReporter.config().setTheme(Theme.DARK); // Dark theme for report

		// Initializing the ExtentReports object and attaching the reporter
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Adding system/environment information to the report for context
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name")); // Fetches current system username
		extent.setSystemInfo("Environment", "QA"); // Environment info (e.g., QA, Staging, etc.)

		// Fetching OS and Browser from TestNG XML and adding to the report
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		// Adding included test groups (if any) to the report
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	// This method runs when a test case is marked as passed
	public void onTestSuccess(ITestResult result) {
		// Creating a test entry in the report
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // Assign test groups (if any)

		// Logging the test result as passed
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	// This method runs when a test case is marked as failed
	public void onTestFailure(ITestResult result) {
		// Creating a test entry in the report for the failed test case
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		// Logging the failure details
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage()); // Logging the exception message

		// Capturing a screenshot for the failed test case
		try {
			String imgPath = new BaseClass().captureScreen(result.getName()); // Capturing the screen using a utility method
			test.addScreenCaptureFromPath(imgPath); // Attaching the screenshot to the report
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// This method runs when a test case is skipped
	public void onTestSkipped(ITestResult result) {
		// Creating a test entry in the report for the skipped test case
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		// Logging that the test was skipped
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage()); // Logging the reason for skipping
	}

	// This method runs after all the test cases are executed
	public void onFinish(ITestContext testContext) {
		// Flushing the report to ensure all information is written
		extent.flush();

		// Automatically open the report after the execution completes
		//String pathOfExtentReport = System.getProperty("user.dir") + "\\report\\" + repName;
		String pathOfExtentReport = "C:\\Users\\abhai\\eclipse-workspace\\hybrid\\hybridFrame\\report" + repName;
		
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); // Opening the report in the default browser
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* 
		// Example code for sending the report via email (disabled here)
		try {
			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com"); // Set your SMTP server
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("your-email@gmail.com", "your-password")); // Add correct credentials
			email.setSSLOnConnect(true);
			email.setFrom("your-email@gmail.com"); // Sender email
			email.setSubject("Test Results"); // Subject of the email
			email.setMsg("Please find the attached report."); // Message body
			email.addTo("receiver-email@gmail.com"); // Recipient email
			email.attach(url, "extent report", "Please check the attached report.");
			email.send(); // Send the email
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
