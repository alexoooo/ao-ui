package ao.ui.wicket.widget.sample.form;

import ao.ui.wicket.widget.valid.v2.impl.ValidatedFormComponent;
import ao.ui.wicket.widget.valid.v2.message.ValidationMessage;
import java.util.List;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 24-Dec-2009
 * Time: 10:44:36 AM
 */
public class CrudItem
        extends Panel
{
    //--------------------------------------------------------------------
    private final IModel<String> writeLine;
    private final IModel<String> lineField;
    private final IModel<String> areaField;


    //--------------------------------------------------------------------
    public CrudItem(String id)
    {
        super(id);

        writeLine = new Model<String>("anybody can edit me");
        lineField = new Model<String>("line field");
        areaField = new Model<String>("multi<br/>line<br/>text");
//    }
//
//
//    //--------------------------------------------------------------------
//    @Override public void onBeforeRender()
//    {
        //final Form form = new ShinyForm("form");
        final Form form = new Form("form");
        addOrReplace(form);

        final FormComponent<String> writeField =
                new TextField<String>("write", writeLine);
        form.add(new ValidationMessage("write-error",
                new ValidatedFormComponent<String>(writeField) {
                    @Override
                    public void validate(
                            String value, List<String> errors)
                    {
                        if (! value.matches("\\D*")) {
                            errors.add("only non-digits allowed");
                        }
                    }
                }));

        form.add(writeField);

//        form.add(new AjaxEditableLabel<String>("line", lineField));

//        form.add(new InPlaceEditComponent("area", areaField)
//                .add(new IValidator() {
//            @Override public void validate(IValidatable iValidatable) {
//                if (! areaField.getObject().matches("\\D*")) {
//                    iValidatable.error(new ValidationError()
//                        .setMessage("can't have digits!"));
//                }
//            }
//        }));

//        form.add(new AjaxFormValidatingBehavior(form, "onblur"));

//        super.onBeforeRender();
    }
}
