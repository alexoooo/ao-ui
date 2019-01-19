package ao.ui.wicket.widget.valid.v1.model;

import java.util.ArrayList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

/**
 * User: aostrovsky
 * Date: 18-Aug-2009
 * Time: 10:49:45 AM
 */
public class ValidationDisplay extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;

	
	//--------------------------------------------------------------------
    public ValidationDisplay(String id) {
        this(id, new ListModel<String>(
                   new ArrayList<String>()));
    }

    
    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public ValidationDisplay(
            String id, final ListModel<String> model) {
        super(id);

        add(new Label("label", new Model<String>(){
            @Override public String getObject() {
                return model.getObject().isEmpty()
                       ? "" : "Errors:";
            }
        }));

        add(new ListView<String>("errors", model) {
            @Override protected void populateItem(
                    ListItem<String> item) {
                item.add(new Label(
                        "message", item.getModelObject()));
            }
        });
    }
}
