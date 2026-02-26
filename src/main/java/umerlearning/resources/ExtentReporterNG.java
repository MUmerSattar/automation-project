package umerlearning.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterNG {
    public static ExtentReports getReportObject()
    {

        String basePath = System.getProperty("user.dir");
        String reportDir = basePath + File.separator + "reports";
        String reportPath = reportDir + File.separator + "index.html";

        // ensure folder exists
        new File(reportDir).mkdirs();

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Umer Sattar");
        return extent;
    }
}
