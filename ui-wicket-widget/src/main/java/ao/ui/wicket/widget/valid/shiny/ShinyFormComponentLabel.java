package ao.ui.wicket.widget.valid.shiny;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.model.ResourceModel;

/**
 * User: aostrovsky
 * Date: 24-Dec-2009
 * Time: 1:45:35 PM
 */
public class ShinyFormComponentLabel
        extends FormComponentLabel
{
    //--------------------------------------------------------------------
    private static final long serialVersionUID = 1L;


    //--------------------------------------------------------------------
    public ShinyFormComponentLabel(
            String           id,
            FormComponent<?> component)
    {
        super(id, component);
        component.setLabel(
                new ResourceModel(component.getId()));
    }


    //--------------------------------------------------------------------
    @Override
    protected void onComponentTagBody(
            MarkupStream markupStream,
            ComponentTag openTag)
    {
        super.onComponentTagBody(markupStream, openTag);

        FormComponent fc = getFormComponent();
        if (fc.isRequired())
        {
            fc.getResponse().write(new ResourceModel(
                    "required.indicator").getObject());
        }

        if (! fc.isValid())
        {
            String error;
            if (fc.hasFeedbackMessage())
            {
                error = fc.getFeedbackMessage().getMessage().toString();
            }
            else
            {
                error = fc.getString("input.invalid");
            }

            fc.getResponse().write(
                    "<strong class=\"validation-msg\">" +
                        error +
                    "</strong>");
        }
    }
    

    //--------------------------------------------------------------------
    @Override
    public FormComponent getFormComponent() {
        return (FormComponent) super.getFormComponent();
    }
}
