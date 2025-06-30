package page;


import org.testng.Reporter;

import com.microsoft.playwright.Page;

public class LoginPage{
	
	private Page page;
	
	//declaration
	private String userName="#input-username";
	private String password="#input-password";
	private String goButton=".btn-primary";
	private String errMsg=".error";
	
	//initialization
	public LoginPage(Page page)
	{
		this.page=page;
	}
	
	//utilization
	
	public void setUserName(String un)
	{
		page.locator(userName).fill(un);
	}
	
	public void setPassword(String pw)
	{
		page.locator(password).fill(pw);
	}
	
	public void clickGoButton()
	{
		page.locator(goButton).click();
	}
	
	
	public boolean verifyErrMsgIsDisplayed()
	{
		try
		{
				page.locator(errMsg).waitFor();
				String text = page.locator(errMsg).textContent();
				Reporter.log(text,true);//home work--> print it in extent report
				return true;
		}
		catch (Exception e) 
		{
				return false;
		}
	}
}
