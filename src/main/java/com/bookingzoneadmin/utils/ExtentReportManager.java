package com.bookingzoneadmin.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	   private static ExtentReports extentReports;
	    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
	    private static  Logger logger = (Logger) LogManager.getLogger();
	    
	    public static synchronized void initializeExtentReports(String reportName) {
	        if (extentReports == null) {
	            try {
	                String date = new SimpleDateFormat("ddMMyyyy").format(new Date());
	                String time = new SimpleDateFormat("HH-mm-ss").format(new Date());
	                String reportFolder = "./reports/BZReport_" + date;
	                File folder = new File(reportFolder);
	                if (!folder.exists()) {
	                    folder.mkdirs();
	                }

	                String reportPath = reportFolder + "/" + time + ".html";
	                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
	                sparkReporter.config().setReportName(reportName);
	                sparkReporter.config().setTheme(Theme.DARK);
	                sparkReporter.config().setDocumentTitle("Test Report");

	                extentReports = new ExtentReports();
	                extentReports.attachReporter(sparkReporter);
	                extentReports.setSystemInfo("OS", System.getProperty("os.name"));
	                extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	                extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
	            } catch (Exception e) {
	                System.err.println("Failed to initialize ExtentReports: " + e.getMessage());
	            }
	        }
	    }

	    public static ExtentTest startTest(String testName) {
	        ExtentTest extentTest = extentReports.createTest(testName);
	        extentTestThreadLocal.set(extentTest);
	        return extentTest;
	    }

	    public static void setTest(ExtentTest test) {
	        extentTestThreadLocal.set(test);
	    }

	    public static ExtentTest getTest() {
	        return extentTestThreadLocal.get();
	    }

	    public static void flushReports() {
	        if (extentReports != null) {
	            extentReports.flush();
	        }
	    }

	    public static void logPass(String message) {
	        getTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	        logger.info(message);
	    }

	    public static void logFail(String message) {
	        getTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	        logger.error(message);
	    }

	    public static void logInfo(String message) {
	        getTest().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
	        logger.info(message);
	    }

	    public static void logWarning(String message) {
	        getTest().warning(MarkupHelper.createLabel(message, ExtentColor.ORANGE));
	        logger.warn(message);
	    }
}
