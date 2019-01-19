package ao.ui.wicket.auth.sample;

import ao.ui.wicket.common.JettyRunner;

public class StartAuth
{
    //-------------------------------------------------------------------------
	public static void main(String[] args)
    {
        JettyRunner.run(8080, "/ui-auth", "ui-wicket-auth");
	}
}