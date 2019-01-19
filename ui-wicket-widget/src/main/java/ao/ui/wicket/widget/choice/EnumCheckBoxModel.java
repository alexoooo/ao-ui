package ao.ui.wicket.widget.choice;

import org.apache.wicket.extensions.model.AbstractCheckBoxModel;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 1-Oct-2009
 * Time: 2:37:29 PM
 * 
 * http://surunairdejava.blogspot.com/2008/09/
 *          wicket-checkbox-abstractcheckboxmodel.html
 */
public class EnumCheckBoxModel<T extends Enum<T>>
        extends AbstractCheckBoxModel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private final IModel<T> model;
    private final T         enumValue;


    //--------------------------------------------------------------------
    public EnumCheckBoxModel(
            IModel<T> model,
            T         enumValue)
    {
        this.model = model;
        this.enumValue = enumValue;
    }


    //--------------------------------------------------------------------
    @Override
    public boolean isSelected() {
        return model.getObject() == enumValue;
    }


    //--------------------------------------------------------------------
    @Override
    public void select() {
        model.setObject(enumValue);
    }
    

    //--------------------------------------------------------------------
    @Override
    public void unselect() {
        model.setObject(null);
    }
}
