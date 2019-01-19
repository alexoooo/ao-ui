package ao.ui.wicket.widget.valid.shiny;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 24-Dec-2009
 * Time: 1:52:20 PM
 */
public class ShinyForm<T>
        extends Form<T>
{
    //--------------------------------------------------------------------
    private static final long serialVersionUID = 1L;


    //--------------------------------------------------------------------
    private ShinyFormVisitor shinyVisitor = new ShinyFormVisitor();


    //--------------------------------------------------------------------
    public ShinyForm(String id)
    {
        super(id);
    }

    public ShinyForm(String id, IModel<T> model)
    {
        super(id, model);
    }
    

    //--------------------------------------------------------------------
    @Override
    protected void onBeforeRender()
    {
        visitFormComponents(shinyVisitor);
        super.onBeforeRender();
    }
}