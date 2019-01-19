package ao.ui.wicket.auth.plain;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AuthSession
		extends AuthenticatedWebSession
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG =
		LoggerFactory.getLogger(AuthSession.class);
	
	
	//--------------------------------------------------------------------
	private final Roles roles;
	
	
	//--------------------------------------------------------------------
	public AuthSession(Request request)
	{
		super(request);
		roles = new Roles();
	}
	
    
	//--------------------------------------------------------------------
	@Override
	public boolean authenticate(
			String userName,
			String password)
	{
		roles.clear();
		
		if (! logIn(userName, password)) {
			LOG.debug("Unsuccessful login attempt {}/{}",
						userName, password);
			return false;
		}
		
		assignRoles(userName, roles);
		
		LOG.debug("Successful login {}: {}", userName, roles);
		return true;
	}
	
	protected abstract boolean logIn(
			String userName, String password);
	
	protected abstract void
			assignRoles(String userName, Roles userRoles);
	
	
	//--------------------------------------------------------------------
	@Override
	public Roles getRoles()
	{
		return roles;
	}
}
