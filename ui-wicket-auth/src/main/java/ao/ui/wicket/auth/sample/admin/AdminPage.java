package ao.ui.wicket.auth.sample.admin;

//import ao.ui.wicket.base.BasePage;

import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

@AuthorizeInstantiation( Roles.ADMIN )
public class AdminPage extends WebPage
{
	//--------------------------------------------------------------------
	public AdminPage()
	{
		
	}
}
