package ao.ui.wicket.widget.valid.v2.impl;

import ao.ui.wicket.widget.valid.v2.change.FormComponentChangeSource;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * User: aostrovsky
 * Date: 29-Dec-2009
 * Time: 6:56:05 PM
 */
public abstract class ValidatedFormComponent<T>
        extends AbstractValidated<T>
{
    //--------------------------------------------------------------------
    public ValidatedFormComponent(FormComponent<T> component)
    {
        super(component.getModel(),
              new FormComponentChangeSource(component));
    }
}
