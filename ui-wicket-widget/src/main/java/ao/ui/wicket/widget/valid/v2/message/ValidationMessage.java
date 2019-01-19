package ao.ui.wicket.widget.valid.v2.message;

import ao.ui.wicket.widget.valid.v2.Validated;
import ao.ui.wicket.widget.valid.v2.ValidationListener;
import ao.util.data.Colls;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 29-Dec-2009
 * Time: 6:10:14 PM
 */
public class ValidationMessage
        extends    Panel
        implements ValidationListener
{
    //--------------------------------------------------------------------
    private final IModel<String> messageModel;
    private final Label          message;


    //--------------------------------------------------------------------
//    public ValidationMessage(
//            String           id,
//            FormComponent<?> formComponent)
//    {
//        this(id, );
//    }

    public ValidationMessage(
            String    id,
            Validated validated)
    {
        super(id);

        messageModel = new Model<String>("");
        message      = new Label("message", messageModel);
        message.setOutputMarkupId(true);

        messageModel.setObject("");
        add(message);

        validated.add( (ValidationListener) this );
    }


    //--------------------------------------------------------------------
    @Override
    public void hear(
            List<String>      errorMessages,
            AjaxRequestTarget target)
    {
        String prevMessage = messageModel.getObject();
        if (errorMessages.isEmpty()) {
            messageModel.setObject("");
        } else {
            messageModel.setObject(
                    Colls.join(errorMessages, " | "));
        }

        if (! prevMessage.equals( messageModel.getObject() )) {
            target.addComponent( message );
        }
    }
}
