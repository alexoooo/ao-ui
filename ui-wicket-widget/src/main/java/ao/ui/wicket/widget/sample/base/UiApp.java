package ao.ui.wicket.widget.sample.base;

import ao.ui.wicket.widget.base.WebApp;
import ao.ui.wicket.widget.sample.home.UiWidgetIndex;
import org.apache.wicket.Page;

/**
 * Application object for your web application.
 * 	If you want to run this application without deploying,
 * 	run the Start class.
 * 
// * @see wicket.myproject.Start#main(String[])
 */
public class UiApp extends WebApp
{
	//--------------------------------------------------------------------
	public UiApp() {}
	
	
	//--------------------------------------------------------------------
	@Override
	public Class<? extends Page> getHomePage()
    {
		return UiWidgetIndex.class;
	}
}
