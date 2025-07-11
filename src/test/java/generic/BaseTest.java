package generic;


import java.lang.reflect.Method;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class BaseTest {
	public static Playwright playwright;
	public static ExtentReports extent;
	public static ExtentTest test0;
	public static String reportPath;
	public Browser browser;
	public BrowserContext context;
	public Page page;
	public ExtentTest test;
	public String xlPath;
	public String recordVideo;

	@Parameters("propertyFile")
	@BeforeSuite
	public void createReport(@Optional("config.properties") String propertyFile)
	{
		reportPath=FileUtil.getProperty(propertyFile,"reportPath");
		extent = new ExtentReports();
        extent.attachReporter(new ExtentSparkReporter(reportPath+"Spark.html"));
        test0 = extent.createTest("Test0");
        test0.log(Status.INFO,"Initialize Playwright");
        Allure.step("Initialize Playwright");
		playwright = Playwright.create();
	}
	
	@AfterSuite
	public void generateReport()
	{
		 playwright.close();
		 test0.log(Status.INFO,"stop the Playwright");
		 extent.flush();	
	}
	
	@Step("Initialize Playwright instance")
	@Parameters("propertyFile")
	@BeforeMethod
	public void preCondition(Method testMethod,@Optional("config.properties") String propertyFile)
	{
		
		test = extent.createTest(testMethod.getName());
		test.log(Status.INFO,"Reading Property file:"+propertyFile);
		
		xlPath=FileUtil.getProperty(propertyFile,"xlPath");
		recordVideo=FileUtil.getProperty(propertyFile,"recordVideo");
		String useGrid=FileUtil.getProperty(propertyFile,"useGrid");
		String gridURL=FileUtil.getProperty(propertyFile,"gridURL");
		String browserType=FileUtil.getProperty(propertyFile,"browserType");
		String appURL=FileUtil.getProperty(propertyFile,"appURL");
		String defaultTimeout=FileUtil.getProperty(propertyFile,"defaultTimeout");
		String defaultNavigationTimeout=FileUtil.getProperty(propertyFile,"defaultNavigationTimeout");
		
		
		
		if(useGrid.equalsIgnoreCase("yes"))
		{
			test.log(Status.INFO,"Launch the browser:"+browserType+" in remote system");
			if(browserType.equals("chrome"))
			{
				browser = playwright.chromium().connect(gridURL);
				
			}
			else
			{
				browser = playwright.firefox().connect(gridURL);
			}
		}
		else
		{
			test.log(Status.INFO,"Launch the browser:"+browserType+" in local system");
			if(browserType.equals("chrome"))
			{
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			}
			else
			{
				browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			}
		}
		
		
		test.log(Status.INFO,"Enable Video Recording?:"+recordVideo);
		if(recordVideo.equals("yes"))
		{
			context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos")));
		}
		else
		{
			context = browser.newContext();
		}
		
		test.log(Status.INFO,"open the page");
		page = context.newPage();
		
		test.log(Status.INFO,"set Default Timeout:"+defaultTimeout);
		page.setDefaultTimeout(Integer.parseInt(defaultTimeout));
		
		test.log(Status.INFO,"set Navigation Timeout:"+defaultNavigationTimeout);
		page.setDefaultNavigationTimeout(Integer.parseInt(defaultNavigationTimeout));
		
		test.log(Status.INFO,"navigate to url:"+appURL);
		page.navigate(appURL);
		
	}
	
	@AfterMethod
	public void postCondition(ITestResult result)
	{
		String testName = result.getName();
		int status = result.getStatus();
		if(status==2)
		{
			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(reportPath+testName+".png")));
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(testName+".png").build());
		}
		else if(status==3)
		{
			test.skip("This is Skipped");
		}
		test.log(Status.INFO,"close the page");
		page.close();
		
		if(recordVideo.equals("yes")) 
		{
			test.log(Status.INFO,"Save the video");
			page.video().saveAs(Paths.get("videos/"+testName+".webm"));
		}
		else
		{
			test.log(Status.INFO,"video is not recorded");
		}
		test.log(Status.INFO,"close the browser");
		browser.close();
		
		
	}
}
