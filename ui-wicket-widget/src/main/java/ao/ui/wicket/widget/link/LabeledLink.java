package ao.ui.wicket.widget.link;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ILinkListener;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: aostrovsky
 * Date: 18-Aug-2009
 * Time: 11:34:04 AM
 */
public class LabeledLink
        extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	

	//--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public LabeledLink(
            final String        id,
            final String        label,
            final ILinkListener listener)
    {
        super(id);

        add(new Link<String>("link") {
            @Override public void onClick() {
                listener.onLinkClicked();
            }
        }.add(new Label("label", label)));
    }
}
