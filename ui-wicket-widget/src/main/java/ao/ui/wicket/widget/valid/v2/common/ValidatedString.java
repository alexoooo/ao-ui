package ao.ui.wicket.widget.valid.v2.common;

import ao.ui.wicket.widget.valid.v2.Validated;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 20-Jan-2010
 * Time: 10:27:40 PM
 */
public interface ValidatedString<T extends ValidatedString>
        extends Validated<T>
{
    //--------------------------------------------------------------------
    public IModel<String> getModel();

    public IModel<String> getScratchPad();

    public String getScratchValue();

    public String getValue();

    public boolean isValid();

    public T setAllowEdit(boolean allowEdit);
}
