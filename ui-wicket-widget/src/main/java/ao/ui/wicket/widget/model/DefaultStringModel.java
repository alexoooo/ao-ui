package ao.ui.wicket.widget.model;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 1-Oct-2009
 * Time: 5:44:25 PM
 */
public class DefaultStringModel
        extends AbstractReadOnlyModel<String>
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private final IModel<?> DELEGET;
    private final String    DEFAULT;


    //--------------------------------------------------------------------
    public DefaultStringModel(
            IModel<?> deleget,
            String    valueIfNull)
    {
        DELEGET = deleget;
        DEFAULT = valueIfNull;
    }


    //--------------------------------------------------------------------
    @Override public String getObject()
    {
        Object value = DELEGET.getObject();
        return (value == null)
               ? DEFAULT
               : value.toString();
    }
}
