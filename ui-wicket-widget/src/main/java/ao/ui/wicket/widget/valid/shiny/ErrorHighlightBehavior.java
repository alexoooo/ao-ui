package ao.ui.wicket.widget.valid.shiny;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 24-Dec-2009
 * Time: 1:16:27 PM
 *
 * See http://londonwicket.org/content/LondonWicket-FormsWithFlair.pdf
 */
public class ErrorHighlightBehavior
        extends AttributeAppender
{
    //--------------------------------------------------------------------
    private static final long serialVersionUID = 1L;


    //--------------------------------------------------------------------
    public ErrorHighlightBehavior() {
        super("class", true, new Model<String>("error"), " ");
    }

    
    //--------------------------------------------------------------------
    @Override
    public boolean isEnabled(Component component) {
            return ! ((FormComponent) component).isValid();
    }
}