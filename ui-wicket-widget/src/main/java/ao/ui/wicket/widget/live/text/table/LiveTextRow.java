package ao.ui.wicket.widget.live.text.table;

import ao.ui.wicket.widget.live.text.LiveText;
import ao.ui.wicket.widget.valid.v2.ValidationListener;
import ao.ui.wicket.widget.valid.v2.common.ValidatedString;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 20-Jan-2010
 * Time: 10:19:01 PM
 */
public class LiveTextRow
        extends Panel
        implements ValidatedString<LiveTextRow>
{
    //--------------------------------------------------------------------
    private final LiveText delegate;


    //--------------------------------------------------------------------
    public LiveTextRow(String id, String label)
    {
        this(id, label, new Model<String>());
    }

    public LiveTextRow(
            String         id,
            String         label,
            IModel<String> model)
    {
        this(id, new Model<String>(label), model);
    }

    public LiveTextRow(
            String         id,
            IModel<String> label,
            IModel<String> model)
    {
        super(id);

        delegate = new LiveText("field", model);

        add(delegate);
        add(new Label("label", label));
    }


    //--------------------------------------------------------------------
    @Override
    public IModel<String> getModel()
    {
        return delegate.getModel();
    }

    @Override
    public IModel<String> getScratchPad()
    {
        return delegate.getScratchPad();
    }

    @Override
    public String getScratchValue()
    {
        return delegate.getScratchValue();
    }

    @Override
    public String getValue()
    {
        return delegate.getValue();
    }

    @Override
    public boolean isValid()
    {
        return delegate.isValid();
    }

    @Override
    public LiveTextRow setAllowEdit(boolean allowEdit)
    {
        delegate.setAllowEdit( allowEdit );
        return this;
    }

    @Override
    public LiveTextRow add(ValidationListener listener)
    {
        delegate.add( listener );
        return this;
    }

    //--------------------------------------------------------------------
    public LiveTextRow setType(LiveText.Type displayType)
    {
        delegate.setType( displayType );
        return this;
    }


    //--------------------------------------------------------------------
    @Override public String toString() {
        return delegate.toString();
    }
}
