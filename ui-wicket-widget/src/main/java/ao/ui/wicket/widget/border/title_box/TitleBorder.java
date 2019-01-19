package ao.ui.wicket.widget.border.title_box;

import ao.ui.wicket.widget.proxy.VisualProxy;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;

/**
 * User: aostrovsky
 * Date: 4-Sep-2009
 * Time: 10:29:39 AM
 */
@SuppressWarnings("serial")
public class TitleBorder extends Border
{
    //--------------------------------------------------------------------
    public static final String TITLE_ID = "title";


    //--------------------------------------------------------------------
    public TitleBorder(
            String id,
            String title)
    {
        this(id, new Label(TITLE_ID, title));
    }

    public TitleBorder(
            String      id,
            VisualProxy headerProxy)
    {
        this(id, headerProxy.displayWicket(TITLE_ID));
    }
    
    
    //--------------------------------------------------------------------
    public TitleBorder(
            String    id,
            Component header)
    {
        super(id);
        
        add(header);
        
        add(CSSPackageResource.getHeaderContribution(
        		TitleBorder.class, "TitleBorder.css"));
    }
}
