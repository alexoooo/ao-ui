package ao.ui.wicket.widget.live.text;

import ao.ui.wicket.widget.config.UiConfig;
import ao.ui.wicket.widget.valid.v2.Validation;
import ao.ui.wicket.widget.valid.v2.ValidationListener;
import ao.ui.wicket.widget.valid.v2.common.ValidatedString;
import ao.ui.wicket.widget.valid.v2.impl.ValidatedPublisher;
import ao.ui.wicket.widget.valid.v2.message.ErrorMessage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 8-Jan-2010
 * Time: 10:39:31 AM
 */
public class LiveText
        extends    Panel
        implements ValidatedString<LiveText>
{
    //--------------------------------------------------------------------
    private final ValidatedPublisher publisher;
    private final ErrorMessage err;
    private final IModel<String>     scratchPad;

    private final WebComponent       asReadOnly;
    private final TextField          asLine;
    private final PasswordTextField  asPass;
    private final TextArea           asArea;

    private       boolean            editable = true;
    private       Type               type     = null;


    //--------------------------------------------------------------------
    public LiveText(String id)
    {
        this(id, Type.LINE);
    }

    public LiveText(
            String id,
            Type   type)
    {
        this(id, type, new Model<String>());
    }

    public LiveText(
            String         id,
            IModel<String> model)
    {
        this(id, Type.LINE, model);
    }

    public LiveText(
            String         id,
            Type           displayType,
            IModel<String> model)
    {
        super(id, model);
        setOutputMarkupId(true);

        scratchPad = new Model<String>( model.getObject() );
        publisher  = new ValidatedPublisher(model, scratchPad);

        err        = new ErrorMessage     ("feedback"                );
        asLine     = new TextField<String>("as-line"     , scratchPad);
        asPass     = new PasswordTextField("as-pass"     , scratchPad);
        asArea     = new TextArea <String>("as-area"     , scratchPad);
        asReadOnly = new Label            ("as-read-only", scratchPad);

        add(err       );
        add(asLine    );
        add(asPass    );
        add(asArea    );
        add(asReadOnly);

        asLine.add(new ChangeMonitor());
        asPass.add(new ChangeMonitor());
        asArea.add(new ChangeMonitor());

        setType(displayType);
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public IModel<String> getModel()
    {
        return (IModel<String>) getDefaultModel();
    }

    public IModel<String> getScratchPad()
    {
        return scratchPad;
    }

    public String getScratchValue()
    {
        return getScratchPad().getObject();
    }

    public String getValue()
    {
        return getModel().getObject();
    }

    public boolean isValid()
    {
        return getValue().equals( getScratchValue() );
//        return err.isValid();
    }


    //--------------------------------------------------------------------
    public LiveText setType(Type displayType)
    {
        assert displayType != null;

        if (type != displayType)
        {
            type = displayType;
            refreshType();
        }

        return this;
    }

    private void refreshType()
    {
        asLine    .setVisible(type == Type.LINE     );
        asPass    .setVisible(type == Type.PASSWORD );
        asArea    .setVisible(type == Type.AREA     );
        asReadOnly.setVisible(type == Type.READ_ONLY);
    }


    //--------------------------------------------------------------------
    public LiveText setAllowEdit(boolean allowEdit)
    {
        if (editable != allowEdit)
        {
            editable = allowEdit;

//            asLine    .setEnabled(editable);
//            asPass    .setEnabled(editable);
//            asArea    .setEnabled(editable);
//            asReadOnly.setEnabled(editable);

            if (editable)
            {
                asLine    .setEnabled(editable);
                asPass    .setEnabled(editable);
                asArea    .setEnabled(editable);
                asReadOnly.setVisible(false   );
            }
            else
            {
                asLine    .setVisible(editable);
                asPass    .setVisible(editable);
                asArea    .setVisible(editable);
                asReadOnly.setVisible(true   );
            }
        }

        return this;
    }


    //--------------------------------------------------------------------
//    @Override
    public LiveText add(Validation<String> validation)
    {
        publisher.add(validation);
        err.update(publisher.errors(), null);
        return this;
    }

    @Override
    public LiveText add(ValidationListener listener)
    {
        publisher.add(listener);
        return this;
    }


    //--------------------------------------------------------------------
    public class ChangeMonitor extends OnChangeAjaxBehavior
    {
        private static final long serialVersionUID = 1L;

        public ChangeMonitor()
        {
            setThrottleDelay( UiConfig.get().updateDelay() );
        }

        @Override protected void onUpdate(
                AjaxRequestTarget target)
        {
            err.update(
                    publisher.validate(target),
                    target);
        }
    }


    //--------------------------------------------------------------------
    public static enum Type
    {
        READ_ONLY, LINE, PASSWORD, AREA
    }
}
