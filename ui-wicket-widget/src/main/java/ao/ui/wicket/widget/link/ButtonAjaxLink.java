package ao.ui.wicket.widget.link;

import ao.ui.wicket.widget.border.button.ButtonBorder;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.border.Border;

/**
 * User: aostrovsky
 * Date: 3-Sep-2009
 * Time: 4:13:51 PM
 */
public abstract class ButtonAjaxLink extends Border
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
//    public ButtonAjaxLink(String id)
//    {
//        this(id, null);
//    }

    @SuppressWarnings("serial")
    public ButtonAjaxLink(
            String         id//,
//            IModel<String> labelModel
            )
    {
//        super(id, labelModel);
        super(id);
        setOutputMarkupId(true);

        add(new ButtonBorder("button").add(
                new AjaxLink("link") {
                    @Override public void onClick(
                            AjaxRequestTarget target) {
                        ButtonAjaxLink.this.onClick(target);
                    }
                }));
    }


    //--------------------------------------------------------------------
    public abstract void onClick(AjaxRequestTarget target);
}
