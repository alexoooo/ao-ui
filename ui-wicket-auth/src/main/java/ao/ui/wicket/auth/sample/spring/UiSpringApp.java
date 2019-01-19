package ao.ui.wicket.auth.sample.spring;

//import ao.ui.wicket.auth.spring.SpringAuthApplication;

import ao.ui.wicket.auth.sample.home.UiAuthIndex;
import ao.ui.wicket.auth.spring.SpringAuthApplication;
import org.apache.wicket.Page;
import org.apache.wicket.authentication.pages.SignInPage;
import org.apache.wicket.markup.html.WebPage;

/**
 * Application object for your web application.
 * If you want to run this application without
 * 	deploying, run the Start class.
 * 
// * @see wicket.myproject.Start#main(String[])
 */
public class UiSpringApp extends SpringAuthApplication
{
	//--------------------------------------------------------------------
	public UiSpringApp() {}
	
	
	//--------------------------------------------------------------------
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override public Class<? extends Page> getHomePage()
	{
		return UiAuthIndex.class;
	}
	
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}
}
