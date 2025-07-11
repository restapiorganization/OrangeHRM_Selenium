package page;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;
	private String logoutLink ="//a[text()='Logout']";
	
	public HomePage(Page page)
	{
		this.page=page;
	}
	
	public boolean verifyHomePageIsDisplayed()
	{
		try
		{
				page.locator(logoutLink).waitFor();
				return true;
		}
		catch (Exception e) 
		{
				return false;
		}
	}
	
}
