package ao.ui.wicket.widget.border.round;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.border.Border;

/**
 * User: aostrovsky
 * Date: 4-Sep-2009
 * Time: 11:24:14 AM
 */
@SuppressWarnings("serial")
public class RoundBorder extends Border
{
    //--------------------------------------------------------------------
    public RoundBorder(String id) {
        super(id);
        
        add(CSSPackageResource.getHeaderContribution(
        		RoundBorder.class, "box.css"));
    }
}
