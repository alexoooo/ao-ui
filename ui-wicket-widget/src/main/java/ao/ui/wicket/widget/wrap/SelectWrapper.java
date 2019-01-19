package ao.ui.wicket.widget.wrap;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: aostrovsky
 * Date: 3-Sep-2009
 * Time: 9:46:07 AM
 */
public class SelectWrapper extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
    public static final String WRAP_ID = "wrap";


    //--------------------------------------------------------------------
    public SelectWrapper(String id, FormComponent<?> wrap) {
        super(id);
        add(wrap);
    }
}