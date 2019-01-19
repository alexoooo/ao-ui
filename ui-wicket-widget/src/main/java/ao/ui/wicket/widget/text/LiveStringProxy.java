package ao.ui.wicket.widget.text;

import ao.ui.wicket.widget.proxy.VisualProxy;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

/**
 * User: aostrovsky
 * Date: 17-Aug-2009
 * Time: 2:40:35 PM
 */
public class LiveStringProxy implements VisualProxy, Serializable
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private final boolean        multiLine;
    private final IModel<String> model;


    //--------------------------------------------------------------------
    public LiveStringProxy() {
        this("");
    }

    public LiveStringProxy(String initialValue) {
        this(initialValue, false);
    }
    public LiveStringProxy(String initialValue, boolean multiLine) {
        this(new Model<String>(initialValue), multiLine);
    }

    public LiveStringProxy(
            IModel<String> model,
            boolean        multiLine)
    {
        this.model     = model;
        this.multiLine = multiLine;
    }


    //--------------------------------------------------------------------
    public void setText(String text) {
        model.setObject( text );
    }

    public void appendText(String text) {
        model.setObject( model.getObject() + text );
    }


    //--------------------------------------------------------------------
    public Component displayWicket(String id) {
        return multiLine
               ? new Multiline(id)
               : new Label(id, model)
                    .add(new AjaxSelfUpdatingTimerBehavior(
                            Duration.seconds(1)));
    }


    //--------------------------------------------------------------------
    private class Multiline extends Panel {
		private static final long serialVersionUID = 1L;

		public Multiline(String id) {
            super(id);

            setOutputMarkupId(true);
            add(new AjaxSelfUpdatingTimerBehavior(
                        Duration.seconds(1)));

            add(new ListView<String>("lines",
                    new AbstractReadOnlyModel<List<String>>(){
            			private static final long serialVersionUID = 1L;
                        public List<String> getObject() {
                            return Arrays.asList(
                                    model.getObject().split("\n"));
                        }
                    }){
            	private static final long serialVersionUID = 1L;
				@Override protected void populateItem(
                        ListItem<String> item) {
                    item.add(new Label("line", item.getModelObject()));
                }
            });
        }
    }
}
