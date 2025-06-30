package page;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	
	//declaration
	private String userName="#input-username";
	private String password="#input-password";
	private String goButton=".btn-primary";
	
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
}
