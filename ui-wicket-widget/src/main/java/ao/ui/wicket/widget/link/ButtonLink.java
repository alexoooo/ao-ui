package ao.ui.wicket.widget.link;

import ao.ui.wicket.widget.border.button.ButtonBorder;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.link.Link;

/**
 * User: aostrovsky
 * Date: 3-Sep-2009
 * Time: 4:13:40 PM
 */
public abstract class ButtonLink extends Border
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public ButtonLink(String id)
    {
        super(id);
        setOutputMarkupId(true);

        add(new ButtonBorder("button").add(
                new Link<Object>("link") {
                    @Override public void onClick() {
                        ButtonLink.this.onClick();
                    }
                }));
    }


    //--------------------------------------------------------------------
    public abstract void onClick();
}
