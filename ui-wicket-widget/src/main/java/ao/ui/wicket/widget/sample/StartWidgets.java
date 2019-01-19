package ao.ui.wicket.widget.sample;

import ao.ui.wicket.common.JettyRunner;

public class StartWidgets
{
    //-------------------------------------------------------------------------
	public static void main(String[] args)
    {
        JettyRunner.run(8080, "/ui-widget", "ui-wicket-widget");
	}
}
