package ao.ui.wicket.widget.choice;

import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 1-Oct-2009
 * Time: 4:09:44 PM
 */
public class UpdatingRadioChoice<T extends Enum<T>>
        extends RadioChoice<T>
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	

	//--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public UpdatingRadioChoice(
            String    id,
            List  <T> choices,
            IModel<T> selection)
    {
        super(id, selection, choices);

        add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override protected void onUpdate(
                    AjaxRequestTarget target) {}
        });
    }


//    //--------------------------------------------------------------------
//    @Override public boolean wantOnSelectionChangedNotifications()
//    {
//        return true;
//    }
}
