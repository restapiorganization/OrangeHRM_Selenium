package script;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportDemo {

	public static void main(String[] args) {
		ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
        
        ExtentTest test1 = extent.createTest("Test1");
        
        test1.fail("this is fail");
        test1.skip("this is skip");
        test1.warning("this is warning");
        test1.pass("this is pass");
        test1.info("this is info");
        test1.pass(MediaEntityBuilder.createScreenCaptureFromPath("google.png").build());
        
        ExtentTest test2 = extent.createTest("Test2");
        test2.log(Status.INFO, "this is info");
        test2.log(Status.FAIL, "this is fail");
        test2.log(Status.PASS, "this is pass");
        test2.log(Status.SKIP, "this is skip");
        test2.log(Status.WARNING, "this is WARNING");
        test2.addScreenCaptureFromPath("google.png");
        
        //extent.flush();
        System.out.println("Done");
	}

}
