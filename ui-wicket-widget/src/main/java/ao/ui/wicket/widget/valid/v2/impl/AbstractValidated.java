package ao.ui.wicket.widget.valid.v2.impl;

import ao.ui.wicket.widget.valid.v2.Validated;
import ao.ui.wicket.widget.valid.v2.Validation;
import ao.ui.wicket.widget.valid.v2.ValidationListener;
import ao.ui.wicket.widget.valid.v2.change.AjaxChangeListener;
import ao.ui.wicket.widget.valid.v2.change.AjaxChangeSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 30-Dec-2009
 * Time: 4:15:10 PM
 */
public abstract class AbstractValidated<T>
        implements Validated<AbstractValidated<T>>,
                   Validation<T>,
                   Serializable
{
    //--------------------------------------------------------------------
    private final List<ValidationListener> listeners =
            new ArrayList<ValidationListener>();


    //--------------------------------------------------------------------
    public AbstractValidated(
            final IModel<T>        model,
            final AjaxChangeSource change)
    {
        change.add(new AjaxChangeListener() {
            @Override public void onChange(
                    AjaxRequestTarget target)
            {
                List<String> errors = new ArrayList<String>();
                validate( model.getObject(), errors );
                for (ValidationListener listener : listeners) {
                    listener.hear(errors, target);
                }
            }
        });
    }


    //--------------------------------------------------------------------
    @Override
    public AbstractValidated<T> add(ValidationListener listener)
    {
        listeners.add( listener );
        return this;
    }
}
