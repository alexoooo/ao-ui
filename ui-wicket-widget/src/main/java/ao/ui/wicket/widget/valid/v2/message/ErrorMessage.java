package ao.ui.wicket.widget.valid.v2.message;

//import ao.util.data.Coll;

import ao.util.data.Colls;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 8-Jan-2010
 * Time: 12:45:21 PM
 */
public class ErrorMessage
        extends Panel
{
    //--------------------------------------------------------------------
    private final Label message;


    //--------------------------------------------------------------------
    public ErrorMessage(String id)
    {
        super(id);
        
        message = new Label("message", new Model<String>());
        message.setOutputMarkupId(true);
        add(message);
    }


    //--------------------------------------------------------------------
    public void update(
            List<String>      errors,
            AjaxRequestTarget target)
    {
        String newValue = Colls.join(errors, " | ");
        if (! newValue.equals(
                message.getDefaultModelObject() ))
        {
            message.setDefaultModelObject( newValue );

            if (target != null)
            {
                target.addComponent( message );
            }
        }
    }


//    //--------------------------------------------------------------------
//    public boolean isValid()
//    {
//        Object errObj = message.getDefaultModelObject();
//        if (errObj == null) return true;
//
//        String err = errObj.toString();
//        return err == null || err.isEmpty();
//    }
}
