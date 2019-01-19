package ao.ui.wicket.auth.spring;

import org.apache.wicket.Request;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.SpringSecurityException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

public class SpringAuthSession
		extends AuthenticatedWebSession
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG =
		LoggerFactory.getLogger(SpringAuthSession.class);
	
	
	//--------------------------------------------------------------------
	@SpringBean
    private AuthenticationManager authenticationManager;
	
	
	//--------------------------------------------------------------------
	public SpringAuthSession(Request request)
	{
		super(request);
		injectDependencies();
        ensureDependenciesNotNull();
	}
	
	private void ensureDependenciesNotNull()
	{
        if (authenticationManager == null) {
            throw new IllegalStateException(
            		"AdminSession requires an authenticationManager.");
        }
    }

    private void injectDependencies()
    {
        InjectorHolder.getInjector().inject(this);
    }
	
    
	//--------------------------------------------------------------------
	@Override
	public boolean authenticate(
			String username, String password)
	{
		boolean authenticated = false;
        try {
            Authentication authentication =
            	authenticationManager.authenticate(
            			new UsernamePasswordAuthenticationToken(
            					username, password));
            SecurityContextHolder.getContext()
    				.setAuthentication(authentication);
            authenticated = authentication.isAuthenticated();
        } catch (SpringSecurityException e) {
            LOG.warn("User '{}' failed to login. Reason: {}",
            			username, e.getMessage());
            authenticated = false;
        }
        return authenticated;
	}
	
	
	//--------------------------------------------------------------------
	@Override
	public Roles getRoles()
	{
		Roles roles = new Roles();
        getRolesIfSignedIn(roles);
        return roles;
	}
	
	private void getRolesIfSignedIn(Roles roles)
	{
        if (isSignedIn()) {
            Authentication authentication =
            	SecurityContextHolder.getContext().getAuthentication();
            addRolesFromAuthentication(roles, authentication);
        }
    }

    private void addRolesFromAuthentication(
    		Roles roles, Authentication authentication)
    {
        for (GrantedAuthority authority :
        			authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }
}
