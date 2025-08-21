package com.bookingzoneadmin.pages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;


import com.aventstack.extentreports.ExtentTest;
import com.bookingzoneadmin.utils.CreateBookingCommonActions;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains the base setup and teardown methods for Selenium tests along with ExtentReports integration.
 */
public class BaseClass {
	public Logger logger = LogManager.getLogger(BaseClass.class);
	public static WebDriver driver;
	public static String screenshotsSubFolderName;
	private String username;
	private String password;
	private String userType;
    private BookingDetailsPage bookingDetailsPage;
	private static boolean isLoggedIn = false;

	/**
	 * Initializes the browser instance and sets up the ExtentReports configuration.
	 * 
	 * @param context The TestNG context for accessing test parameters.
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 * @throws EncryptedDocumentException If there is an error with encrypted documents.
	 * @throws IOException If an I/O error occurs.
	 */
	@BeforeSuite
	public void initialiseBrowser(ITestContext context) throws InterruptedException, EncryptedDocumentException, IOException {
		// Initialize ExtentReports before anything else
		ExtentReportManager.initializeExtentReports("BZ Tests Report");

		if (driver == null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");  // New headless mode
			options.addArguments("--disable-gpu");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("start-maximized");
			options.addArguments("test-type");
			options.addArguments("disable-notifications");
			options.addArguments("--window-size=1920,1080");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		
			Thread.sleep(2000);

			driver.get(UtilityClass.getDataFromEs(1, "Bussiness URL", "APK_URL"));
			//driver.manage().window().maximize();
			

			Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
			String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion().split("\\.")[0];

			String author = context.getCurrentXmlTest().getParameter("author");
			ExtentTest test = ExtentReportManager.startTest(context.getName()); 
		test.assignAuthor(author).assignDevice(device);  
		ExtentReportManager.setTest(test);  
		}

		fetchLoginData(context);
		performLoginOnce(userType);
	}


	/**
	 * Sets up the ExtentReport for each test method.
	 * 
	 * @param method The test method being executed.
	 */
	@BeforeMethod
	public void setupTest(Method method) {
		// Set meaningful names for your individual test cases here
		String testName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
		ExtentTest test = ExtentReportManager.startTest(testName);  // Start a new test with a more detailed name
		ExtentReportManager.setTest(test);  // Set this as the current test
		logger.info("Starting test: " + testName);
		
		if (driver != null) {
	        bookingDetailsPage = new BookingDetailsPage(driver);
	    }
	}

	private void fetchLoginData(ITestContext context) throws IOException {
	     userType = context.getCurrentXmlTest().getParameter("userType");
	    
	    switch (userType) {
	        case "business":
	            username = UtilityClass.getDataFromEs(1, "Email", "Business_Data");
	            password = UtilityClass.getDataFromEs(1, "Bussiness Password", "Business_Data");
	            break;
	        case "admin":
	            username = UtilityClass.getDataFromPF("Admin_UserName");
	            password = UtilityClass.getDataFromPF("Admin_Password");
	            break;
	        // Add more cases for other roles (e.g., Manager)
	    }
	}

  private void performLoginOnce(String role) throws IOException, InterruptedException {
	    if (!isLoggedIn) {
	        CreateBookingCommonActions.performLogin(role, driver, username, password);
	        isLoggedIn = true;
	        logger.info(role + " login performed successfully.");
	    }}
	/**
	 * Checks the test result status and captures a screenshot if the test fails.
	 * 
	 * @param result The result of the test execution.
	 * @throws IOException If an I/O error occurs during screenshot capture.
	 */
	@AfterMethod
public void checkStatusAndCaptureScreenshot(ITestResult result) throws IOException {
    if (result.getStatus() == ITestResult.FAILURE) {
        try {
            String screenshotPath = captureScreenshot(result.getName() + ".jpg");
            logger.info("Screenshot captured successfully: " + screenshotPath);

            // Pass the relative path to Extent Report
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath, "Screenshot of Failure");
            ExtentReportManager.getTest().fail(result.getThrowable());
            
            bookingDetailsPage.closeOnTestFailure(result);
        } catch (Exception ex) {
            ExtentReportManager.logFail("Exception during capturing screenshot: " + ex.getMessage());
        }
    } else if (result.getStatus() == ITestResult.SUCCESS) {
        ExtentReportManager.logPass(result.getMethod().getMethodName() + " is passed");
    }
    
    
}
	
	
	/**
	 * Captures a screenshot of the current browser window.
	 * 
	 * @param fileName The name of the screenshot file.
	 * @return The file path of the captured screenshot.
	 * @throws IOException If an I/O error occurs during screenshot capture.
	 */
	public String captureScreenshot(String fileName) throws IOException {
		if (screenshotsSubFolderName == null) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			screenshotsSubFolderName = now.format(formatter);
		}
	
		// Create the Screenshots folder if it doesn't exist
		String screenshotsDir = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + screenshotsSubFolderName;
		new File(screenshotsDir).mkdirs();
	
		// Save the screenshot
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotsDir + File.separator + fileName);
		FileUtils.copyFile(sourceFile, destFile);
	
		// Calculate the relative path from the reports folder to the screenshots folder
		String reportsDir = System.getProperty("user.dir") + File.separator + "reports" + File.separator + screenshotsSubFolderName;
		File reportFile = new File(reportsDir);
		File screenshotFile = new File(destFile.getAbsolutePath());
		String relativePath = reportFile.toURI().relativize(screenshotFile.toURI()).getPath();
	
		return relativePath;
	}
	
	@AfterClass
    public void tearDownClass() {
        //Always close popup/stepper at the end of test class
		bookingDetailsPage.closeAfterClass();
       
    }

	/**
	 * Closes the browser after test execution and logs the closure.
	 * Added a shutdown hook to ensure that Extent Reports are flushed and the browser is closed properly.
	 */
	@AfterSuite
	public void teardownSuite() {
		System.out.println("Teardown started...");
		cleanupResources();
	}

	// Method to handle resource cleanup, called from @AfterSuite and shutdown hook
	private void cleanupResources() {
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
				ExtentReportManager.logInfo("Browser closed");
				System.out.println("Browser closed.");
			}
			// Flush ExtentReports after all tests are complete
			ExtentReportManager.flushReports();
			System.out.println("Reports flushed.");
		} catch (Exception e) {
			ExtentReportManager.logFail("Error during cleanup: " + e.getMessage()+ e);
		}
	}

	// Constructor to add a shutdown hook to ensure browser closure and report flushing in case AfterSuite is skipped
	public BaseClass() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Shutdown hook triggered.");
			cleanupResources();
		}));
	}
}
