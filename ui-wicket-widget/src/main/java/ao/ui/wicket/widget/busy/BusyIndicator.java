package ao.ui.wicket.widget.busy;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: aostrovsky
 * Date: 4-Sep-2009
 * Time: 3:11:14 PM
 */
@SuppressWarnings("serial")
public class BusyIndicator extends Panel
{
    //--------------------------------------------------------------------
    public BusyIndicator(String id)
    {
        super(id);
        
        add(JavascriptPackageResource.getHeaderContribution(
        		BusyIndicator.class, "busy-indicator.js"));
        
        add(CSSPackageResource.getHeaderContribution(
        		BusyIndicator.class, "busy-indicator.css"));
    }
}
