package ao.ui.wicket.widget.valid.v2.change;

import java.io.Serializable;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.time.Duration;

/**
 * User: aostrovsky
 * Date: 30-Dec-2009
 * Time: 4:54:10 PM
 */
public class FormComponentChangeSource
        extends    AbstractChangeSource
        implements Serializable
{
    //--------------------------------------------------------------------
    public FormComponentChangeSource(
            FormComponent<?> formComponent)
    {
        formComponent.add(new OnChangeAjaxBehavior()
        {
            {
                setThrottleDelay(Duration.milliseconds(250));
            }

            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
                publish( target );
            }
        });
    }
}
