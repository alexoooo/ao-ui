package ao.ui.wicket.widget.proxy;

import org.apache.wicket.Component;

/**
 * User: aostrovsky
 * Date: 25-Jan-2010
 * Time: 12:44:53 PM
 */
public interface CrudProxy
        extends VisualIoProxy
{
    public Component displayWicket(String id, boolean readOnly);
}
