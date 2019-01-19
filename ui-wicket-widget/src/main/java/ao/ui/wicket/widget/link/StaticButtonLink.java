package ao.ui.wicket.widget.link;

import ao.ui.wicket.widget.border.button.ButtonBorder;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * User: aostrovsky
 * Date: 25-Sep-2009
 * Time: 2:19:30 PM
 */
public class StaticButtonLink extends Border
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	

	//--------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public StaticButtonLink(
            String                id,
            Class<? extends Page> pageClass,
            PageParameters        pageParameters)
    {
        super(id);
        setOutputMarkupId(true);
        
        add(new ButtonBorder("button").add(
                new BookmarkablePageLink("link",
                        pageClass, pageParameters)));
    }
}
