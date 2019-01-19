package ao.ui.wicket.widget.text;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

/**
 * User: aostrovsky
 * Date: 17-Aug-2009
 * Time: 2:20:06 PM
 */
public class UpdatingTextField
        extends TextField<String>
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private final     boolean               passwordType;
    private transient Collection<Component> dependants;


    //--------------------------------------------------------------------
    public UpdatingTextField(String id, IModel<String> model) {
        this(id, model, false);
    }
    public UpdatingTextField(
            String id, IModel<String> model, boolean isPassword) {
        super(id, model);

        passwordType = isPassword;
//        dependants   = new ArrayList<Component>();
        setup();
    }


    //--------------------------------------------------------------------
    private Collection<Component> dependants() {
        if (dependants == null) {
            dependants = new ArrayList<Component>();
        }
        return dependants;
    }


    //--------------------------------------------------------------------
    private void setup()
    {
        setOutputMarkupId(true);

        add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			{
                setThrottleDelay(Duration.milliseconds( 250 ));
            }

            @Override protected void onUpdate(
                    AjaxRequestTarget target) {
                for (Component dependant : dependants()) {
                    target.addComponent(dependant);
                }
            }
        });
    }


    //--------------------------------------------------------------------
    public void addDependant(Collection<Component> dependant)
    {
        dependants().addAll( dependant );
    }
    public void addDependant(Component dependant)
    {
        dependants().add( dependant );
    }


    //--------------------------------------------------------------------
    @Override protected String getInputType()
	{
		return passwordType
               ? "password"
               : null;
	}
}
