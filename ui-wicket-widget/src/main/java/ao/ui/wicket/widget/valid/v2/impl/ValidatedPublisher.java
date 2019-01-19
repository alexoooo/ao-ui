package ao.ui.wicket.widget.valid.v2.impl;

import ao.ui.wicket.widget.valid.v2.Validation;
import ao.ui.wicket.widget.valid.v2.ValidationListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 8-Jan-2010
 * Time: 11:35:53 AM
 */
public class ValidatedPublisher
        implements Serializable
{
    //--------------------------------------------------------------------
    private final List<ValidationListener> validListeners;
    private final List<Validation<String>> validations;

    private final IModel<String> validValue;
    private final IModel<String> scratchPad;


    //--------------------------------------------------------------------
    public ValidatedPublisher(
            IModel<String> valid,
            IModel<String> scratch)
    {
        validations    = new ArrayList<Validation<String>>();
        validListeners = new ArrayList<ValidationListener>();

        validValue = valid;
        scratchPad = scratch;
    }
    

    //--------------------------------------------------------------------
    public List<String> errors()
    {
        List<String> errors = new ArrayList<String>();
        String       value  = scratchValue();

        for (Validation<String> validation : validations)
        {
            validation.validate(value, errors);
        }

        return errors;
    }

    public List<String> validate(AjaxRequestTarget target)
    {
        List<String> errors = errors();

        if (errors.isEmpty())
        {
            validValue.setObject( scratchValue() );
        }

        for (ValidationListener listener : validListeners)
        {
            listener.hear(errors, target);
        }

        return errors;
    }


    //--------------------------------------------------------------------
    public void add(Validation<String> validation)
    {
        validations.add(validation);
    }

    public void add(ValidationListener listener)
    {
        validListeners.add(listener);
    }


    //--------------------------------------------------------------------
    private String scratchValue()
    {
        String value = scratchPad.getObject();
        return (value == null ? "" : value);
    }
}
