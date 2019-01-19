package ao.ui.wicket.auth.spring;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Application object for your web application.
 * If you want to run this application without deploying,
 *		run the Start class.
 * 
// * @see wicket.myproject.Start#main(String[])
 */
public abstract class SpringAuthApplication
		extends    AuthenticatedWebApplication
		implements ApplicationContextAware
{
	//--------------------------------------------------------------------
	private ApplicationContext context;
	private boolean            isInitialized = false;
	
	
	//--------------------------------------------------------------------
	public SpringAuthApplication() {}
	
	
	//--------------------------------------------------------------------
	protected void init()
	{
		if (isInitialized) return;
		
		super.init();
		setListeners();
		getResourceSettings().setParentFolderPlaceholder("$up$");
		
		isInitialized = true;
	}
	
	private void setListeners() {
        addComponentInstantiationListener(
        		new SpringComponentInjector(this, context, true));
    }
	
	
	//--------------------------------------------------------------------
	public void setApplicationContext(
			ApplicationContext context) throws BeansException
	{
        this.context = context;
    }
	
	
	//--------------------------------------------------------------------
	@Override
    protected Class<? extends AuthenticatedWebSession>
			getWebSessionClass()
    {
        return SpringAuthSession.class;
    }
	
	
//	//--------------------------------------------------------------------
//	/**
//     * @see org.apache.wicket.security.swarm.SwarmWebApplication#getHiveKey()
//     */
//    protected Object getHiveKey()
//    {
//        return getServletContext().getContextPath();
//    }
//    
//    
//    //--------------------------------------------------------------------
//    /**
//     * @see org.apache.wicket.security.swarm.SwarmWebApplication#setUpHive()
//     */
//    protected void setUpHive()
//    {
//        //create factory
//        PolicyFileHiveFactory factory =
//        		new SwarmPolicyFileHiveFactory(getActionFactory());
//        
//        try
//        {
//            // this example uses 1 policy file but
//        	//	 you can add as many as you like
//            factory.addPolicyFile(
//            		getServletContext().getResource(
//            				"/WEB-INF/auth.hive"));
//        }
//        catch (MalformedURLException e)
//        {
//            throw new WicketRuntimeException(e);
//        }
//        
//        //register factory
//        HiveMind.registerHive(getHiveKey(), factory);
//        // note we are not checking if a hive already exist because
//        //	this app will only be deployed once
//    }
}
