package ao.ui.wicket.widget.text;

import ao.ui.wicket.widget.proxy.VisualIoProxy;
import ao.ui.wicket.widget.wrap.InputWrapper;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 17-Aug-2009
 * Time: 2:48:24 PM
 */
public class TextFieldProxy implements VisualIoProxy
{
    //--------------------------------------------------------------------
    private final IModel<String> text;


    //--------------------------------------------------------------------
    public TextFieldProxy()
    {
        this("");
    }
    public TextFieldProxy(String initialValue)
    {
        this(new Model<String>(initialValue));
    }
    public TextFieldProxy(IModel<String> model)
    {
        text = model;
    }


    //--------------------------------------------------------------------
    public Component displayWicket(String id) {
        return displayWicket(id, false);
    }
    public Component displayWicket(String id, boolean readOnly) {
        return readOnly
               ? new Label(id, text)
               : new InputWrapper(id,
                    new UpdatingTextField("wrap", text));
    }
}
