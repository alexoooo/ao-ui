package ao.ui.wicket.widget.valid.v2.change;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * User: aostrovsky
 * Date: 30-Dec-2009
 * Time: 4:54:10 PM
 */
public abstract class AbstractChangeSource
        implements AjaxChangeSource
{
    //--------------------------------------------------------------------
    private final List<AjaxChangeListener> listeners =
            new ArrayList<AjaxChangeListener>();


    //--------------------------------------------------------------------
    protected void publish(AjaxRequestTarget target)
    {
        for (AjaxChangeListener listener : listeners)
        {
            listener.onChange( target );
        }
    }


    //--------------------------------------------------------------------
    @Override
    public void add(AjaxChangeListener listener)
    {
        listeners.add( listener );
    }
}