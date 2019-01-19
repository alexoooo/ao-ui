package ao.ui.wicket.widget.valid.v2;

import java.io.Serializable;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * User: aostrovsky
 * Date: 29-Dec-2009
 * Time: 6:08:37 PM
 */
public interface ValidationListener
        extends Serializable
{
    public void hear(
            List<String>      errorMessages,
            AjaxRequestTarget target);
}
