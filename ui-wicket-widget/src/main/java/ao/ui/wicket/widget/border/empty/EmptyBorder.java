package ao.ui.wicket.widget.border.empty;

import org.apache.wicket.markup.html.border.Border;

/**
 * User: aostrovsky
 * Date: 10-Sep-2009
 * Time: 12:55:52 PM
 */
@SuppressWarnings("serial")
public class EmptyBorder extends Border {
    public EmptyBorder(String id) {
        super(id);
        setOutputMarkupId(true);
    }
}
