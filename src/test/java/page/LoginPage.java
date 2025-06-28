package page;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	
	//declaration
	private String userName="#input-username";
	
	//intialization
	public LoginPage(Page page)
	{
		this.page=page;
	}
	
	//utilization
	
	public void setUserName(String un)
	{
		page.locator(userName).fill(un);
	}
}
