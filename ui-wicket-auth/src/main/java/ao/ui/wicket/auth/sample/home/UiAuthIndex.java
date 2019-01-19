package ao.ui.wicket.auth.sample.home;

import ao.ui.wicket.auth.sample.admin.AdminPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * Homepage
 */
@SuppressWarnings("serial")
public class UiAuthIndex extends WebPage
{
	//--------------------------------------------------------------------
//	private static final long   serialVersionUID = 1L;

//	private static final Logger LOG              =
//        	LoggerFactory.getLogger(UiIndex.class);


	//--------------------------------------------------------------------
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
	public UiAuthIndex()
	{

        add(new BookmarkablePageLink<AdminPage>(
        			"admin-link", AdminPage.class));
	}
}