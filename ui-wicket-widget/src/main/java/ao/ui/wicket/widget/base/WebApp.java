package ao.ui.wicket.widget.base;

import org.apache.wicket.protocol.http.WebApplication;


/**
 * Application object for your web application.
 * If you want to run this application without deploying,
 *		run the Start class.
 * 
// * @see wicket.myproject.Start#main(String[])
 */
public abstract class WebApp
		extends WebApplication
{
	//--------------------------------------------------------------------
	private boolean isInitialized = false;
	
	
	//--------------------------------------------------------------------
	public WebApp() {}
	
	
	//--------------------------------------------------------------------
	protected void init()
	{
		if (isInitialized) return;
		
		super.init();
		getResourceSettings().setParentFolderPlaceholder("$up$");
		
		isInitialized = true;
	}
}
