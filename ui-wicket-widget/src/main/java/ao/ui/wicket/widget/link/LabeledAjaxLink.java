package ao.ui.wicket.widget.link;

import java.io.Serializable;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: aostrovsky
 * Date: 18-Aug-2009
 * Time: 1:48:44 PM
 */
public class LabeledAjaxLink extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public LabeledAjaxLink(
            final String   id,
            final String   label,
            final Listener listener)
    {
        super(id);

        add(new AjaxFallbackLink<String>("link") {
            @Override public void onClick(AjaxRequestTarget target) {
                listener.onClick(target);
            }
        }.add(new Label("label", label)));
    }


    //--------------------------------------------------------------------
    public static interface Listener extends Serializable
    {
        public void onClick(AjaxRequestTarget target);
    }
}
