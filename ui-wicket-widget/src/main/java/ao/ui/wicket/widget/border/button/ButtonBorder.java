package ao.ui.wicket.widget.border.button;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.border.Border;

/**
 * User: aostrovsky
 * Date: 3-Sep-2009
 * Time: 3:37:52 PM
 */
@SuppressWarnings("serial")
public class ButtonBorder extends Border
{	
	//--------------------------------------------------------------------
	public ButtonBorder(String id)
	{
        super(id);
        
        add(CSSPackageResource.getHeaderContribution(
        		ButtonBorder.class, "button.css"));
    }
}
