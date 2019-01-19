package ao.ui.wicket.widget.valid.v1.model;

import ao.ui.wicket.widget.link.LabeledAjaxLink;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * User: aostrovsky
 * Date: 18-Aug-2009
 * Time: 11:22:14 AM
 */
public class ModelValidator<T> implements Serializable
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
    //--------------------------------------------------------------------
    private final T                     model;
    private final ValidationListener    onValid;
    private final ModelInspector<T>     validator;
    private final ValidationMessageList errors;


    //--------------------------------------------------------------------
    public ModelValidator(
            T                  modelToValidate,
            ModelInspector<T>  validationInspector,
            ValidationListener validListener)
    {
        model     = modelToValidate;
        onValid   = validListener;
        validator = validationInspector;
        errors    = new ValidationMessageList();
    }


    //--------------------------------------------------------------------
    public Component errorMessage(String id)
    {
        return errors.displayWicket(id);
    }

    @SuppressWarnings("serial")
	public Component validateLink(
            String id, String label)
    {
        return new LabeledAjaxLink(id, label,
                new LabeledAjaxLink.Listener() {
                    public void onClick(AjaxRequestTarget target) {
                        errors.clear();
                        validator.inspect(model, errors);
                        onValid.hear( errors.isEmpty(), target );
                    }
                });
    }


    //--------------------------------------------------------------------
    public static interface ModelInspector<T> extends Serializable
    {
        public void inspect(
                T                     model,
                ValidationMessageList errors);
    }

    public static interface ValidationListener extends Serializable
    {
        public void hear(boolean isValid, AjaxRequestTarget target);
    }
}
