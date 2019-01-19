package ao.ui.wicket.widget.valid.v1.text;

import ao.ui.wicket.widget.valid.v1.Validator;
import java.util.ArrayList;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;


/**
 * User: aostrovsky
 * Date: 2-Oct-2009
 * Time: 12:21:52 PM
 */
public class ValidText<T> extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
    //--------------------------------------------------------------------
//    private final IModel<T>         targetModel;
//    private final Mapper<String, T> targetMapper;
//    private final Filter<T>         targetValidator;


	//--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public ValidText(
            final String       id,
            final IModel<T>    model,
            final Validator<T> validator)
    {
        super(id);

//        targetModel     = model;
//        targetMapper    = mapper;
//        targetValidator = validator;

        final IModel<ArrayList<String>> errorModel =
                new Model<ArrayList<String>>();
        final WebMarkupContainer errorWrap =
                new WebMarkupContainer("error-wrap");
        errorWrap.setOutputMarkupId(true);
        errorWrap.add(new ListView<String>("errors", errorModel) {
            @Override protected void populateItem(ListItem<String> item) {
                item.add(new Label("error", item.getModel()));
            }
            @Override public boolean isVisible() {
                return errorModel.getObject() != null &&
                        !errorModel.getObject().isEmpty();
            }
        });
        add(errorWrap);

        final IModel<String> value = new Model<String>(
                model.getObject() == null
                ? "" : String.valueOf(model.getObject()));
        add(new TextField<String>("value", value).add(
                new OnChangeAjaxBehavior() {{
                    setThrottleDelay(Duration.milliseconds(250));
                }

                @Override protected void onUpdate(
                        AjaxRequestTarget target) {
                    target.addComponent(errorWrap);

                    ArrayList<String> errors = new ArrayList<String>();
                    T val = validator.validate(
                            value.getObject(), errors);
                    errorModel.setObject(errors);
                    model.setObject(val);
                }
            }));
    }
}
