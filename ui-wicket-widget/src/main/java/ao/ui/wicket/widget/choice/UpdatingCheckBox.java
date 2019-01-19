package ao.ui.wicket.widget.choice;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 17-Sep-2009
 * Time: 2:47:30 PM
 */
public class UpdatingCheckBox extends AjaxCheckBox
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
    public UpdatingCheckBox(String id) {
        super(id);
    }
    public UpdatingCheckBox(String id, IModel<Boolean> model) {
        super(id, model);
    }


    //--------------------------------------------------------------------
    @Override protected void onUpdate(AjaxRequestTarget target) {
        // update content
    }
}
