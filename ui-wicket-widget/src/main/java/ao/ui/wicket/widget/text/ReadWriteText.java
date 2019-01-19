package ao.ui.wicket.widget.text;

import ao.ui.wicket.widget.proxy.VisualIoProxy;
import ao.ui.wicket.widget.wrap.InputWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 3-Sep-2009
 * Time: 9:07:03 AM
 * 
 * todo: breaks when used in conjunction with UpdatingTestField
 */
public class ReadWriteText implements VisualIoProxy, Serializable
{
	//--------------------------------------------------------------------
//	private static final Logger LOG =
//			LoggerFactory.getLogger(ReadWriteText.class);
	
	private static final long serialVersionUID = 1L;
	
	
    //--------------------------------------------------------------------
    private final     IModel<String>                text;
    private transient Collection<Component>         viewers;
    private transient Collection<UpdatingTextField> editors;


    //--------------------------------------------------------------------
    public ReadWriteText(String initialValue)
    {
        this(new Model<String>(initialValue));
    }
    public ReadWriteText(IModel<String> model)
    {
        text    = model;
    }


    //--------------------------------------------------------------------
    private Collection<Component> viewers() {
        if (viewers == null) {
            viewers = new ArrayList<Component>();
        }
        return viewers;
    }
    private Collection<UpdatingTextField> editors() {
        if (editors == null) {
            editors = new ArrayList<UpdatingTextField>();
        }
        return editors;
    }


    //--------------------------------------------------------------------
    public Component displayWicket(String id, boolean readOnly)
    {
        if (readOnly)
        {
            Component viewer = new Label(id, text);
            viewer.setOutputMarkupId(true);
//            viewer.add(new AjaxSelfUpdatingTimerBehavior(
//                        Duration.seconds(1)));

//            LOG.info("Editors: " + editors().size());
            for (UpdatingTextField editor : editors()) {
                editor.addDependant(viewer);
            }

            viewers().clear();
            viewers().add( viewer );
            return viewer;
        }
        else
        {
            UpdatingTextField editor =
                    new UpdatingTextField("wrap", text);
            editor.addDependant(viewers());

            editors().clear();
            editors().add( editor );
            return new InputWrapper(id, editor);
        }
    }

    public Component displayWicket(String id) {
        return displayWicket(id, true);
    }


    //--------------------------------------------------------------------
    @Override public String toString() {
        return text.getObject();
    }
}
