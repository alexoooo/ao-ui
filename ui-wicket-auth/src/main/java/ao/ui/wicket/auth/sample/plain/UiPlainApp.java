package ao.ui.wicket.auth.sample.plain;

import ao.ui.wicket.auth.plain.AuthApplication;
import ao.ui.wicket.auth.plain.AuthSession;
import ao.ui.wicket.auth.sample.home.UiAuthIndex;
import org.apache.wicket.Page;

/**
 * Application object for your web application.
 * If you want to run this application without
 * 	deploying, run the Start class.
 * 
// * @see wicket.myproject.Start#main(String[])
 */
public class UiPlainApp extends AuthApplication
{
	//--------------------------------------------------------------------
	public UiPlainApp() {}

	
	//--------------------------------------------------------------------
	@Override
	public Class<? extends Page> getHomePage() {
		return UiAuthIndex.class;
	}
	
	
	//--------------------------------------------------------------------
	@Override
	protected Class<? extends AuthSession> getWebSessionClass() {
		return UiPlanSess.class;
	}
}
