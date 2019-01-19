package ao.ui.wicket.auth.sample.plain;

import ao.ui.wicket.auth.plain.AuthSession;
import org.apache.wicket.Request;
import org.apache.wicket.authorization.strategies.role.Roles;

public class UiPlanSess
		extends AuthSession
{
	//--------------------------------------------------------------------
	public UiPlanSess(Request request)
	{
		super(request);
	}


	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
	@Override
	protected boolean logIn(String userName, String password) {
		return userName.equals("ao") &&
				password.equals("abc123");
	}
	
	
	//--------------------------------------------------------------------
	@Override
	protected void assignRoles(String userName, Roles userRoles) {
		if (userName.endsWith("ao")) {
			userRoles.add(Roles.ADMIN);
		}
		//userRoles.add(Roles.USER);
	}
}
