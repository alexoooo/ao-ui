package ao.ui.wicket.widget.valid.v1.model;

import ao.ui.wicket.widget.proxy.VisualProxy;
import java.util.ArrayList;
import org.apache.wicket.Component;
import org.apache.wicket.model.util.ListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: aostrovsky
 * Date: 18-Aug-2009
 * Time: 11:07:58 AM
 */
public class ValidationMessageList implements VisualProxy
{
    //--------------------------------------------------------------------
    private static final Logger LOG =
            LoggerFactory.getLogger(ValidationMessageList.class);


    //--------------------------------------------------------------------
    private final ListModel<String> errors;


    //--------------------------------------------------------------------
    public ValidationMessageList()
    {
        errors = new ListModel<String>(
                     new ArrayList<String>());
    }


    //--------------------------------------------------------------------
    public synchronized boolean isEmpty()
    {
        LOG.trace("isEmpty");
        return errors.getObject().isEmpty();
    }


    //--------------------------------------------------------------------
    public synchronized void add(String errorMessage)
    {
        LOG.trace("add: {}", errorMessage);
        errors.getObject().add( errorMessage );
    }


    //--------------------------------------------------------------------
    public synchronized void clear()
    {
        LOG.trace("clearing");
        errors.getObject().clear();
    }


    //--------------------------------------------------------------------
    public Component displayWicket(String id) {
        LOG.trace("displaying {}", errors);
        return new ValidationDisplay(id, errors);
    }
}
