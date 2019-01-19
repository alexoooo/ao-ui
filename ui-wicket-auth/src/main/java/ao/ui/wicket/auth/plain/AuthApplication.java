package ao.ui.wicket.auth.plain;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authentication.pages.SignInPage;
import org.apache.wicket.markup.html.WebPage;


/**
 * Application object for your web application.
 * If you want to run this application without deploying,
 *		run the Start class.
 * 
 * @see ao.ui.wicket.sample.base.Start#main(String[])
 */
public abstract class AuthApplication
		extends AuthenticatedWebApplication
{
	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	public AuthApplication() {}
	
	
	//--------------------------------------------------------------------
	@Override
	protected void init()
	{
		super.init();
		getResourceSettings().setParentFolderPlaceholder("$up$");
	}
	
	
	//--------------------------------------------------------------------
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}
	
	
	//--------------------------------------------------------------------
	@Override
    protected abstract Class<? extends AuthenticatedWebSession>
			getWebSessionClass();
	
//	@Override
//    protected Class<? extends AuthSession>
//			getWebSessionClass()
//    {
//        return AuthSession.class;
//    }
}
