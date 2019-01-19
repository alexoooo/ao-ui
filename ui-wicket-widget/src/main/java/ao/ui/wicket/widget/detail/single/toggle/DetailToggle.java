package ao.ui.wicket.widget.detail.single.toggle;

import java.io.Serializable;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 26-Aug-2009
 * Time: 1:20:43 PM
 */
public class DetailToggle implements Serializable
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
    //--------------------------------------------------------------------
    private static final String CLOSED_IMG_PATH = "u.gif";
    private static final String OPENED_IMG_PATH = "d.gif";
    

    //--------------------------------------------------------------------
    private final String          javaScriptId;
    private final IModel<Boolean> isOpen;

//    private transient List<Component> labels;
//    private transient List<Component> details;


    //--------------------------------------------------------------------
    public DetailToggle()
    {
        this( false );
    }
    public DetailToggle(boolean displayInitially)
    {
        this(new Model<Boolean>(displayInitially));
    }

    public DetailToggle(IModel<Boolean> detailIsOpen)
    {
        javaScriptId = Long.toHexString(Math.abs(Math.round(
                Long.MAX_VALUE * Math.random())));

        isOpen = detailIsOpen;
    }


    //--------------------------------------------------------------------
//    private List<Component> labels() {
//        if (labels == null) {
//            labels = new ArrayList<Component>();
//        }
//        return labels;
//    }

//    private List<Component> details() {
//        if (details == null) {
//            details = new ArrayList<Component>();
//        }
//        return details;
//    }


    //--------------------------------------------------------------------
    public Border labelBorder(String id)
    {
        Border toggle = new Toggle(id);
//        labels().add(toggle);
        return toggle;
    }


    public Border detailBorder(String id)
    {
        Detail detail = new Detail(id);
//        details().add(detail);
        return detail;
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public class Toggle extends Border
    {
        public Toggle(String id) {
            super(id);
            
            add(JavascriptPackageResource.getHeaderContribution(
            		InlineDetailToggle.class, "detail.js"));
            
            add( new WebComponent("toggle")
                    .add(new SimpleAttributeModifier(
                            "name",
                            "img_" + javaScriptId))
                    .add(new AttributeModifier("src",
                    		new Model<String>(){
                                public String getObject() {
                                    return isOpen.getObject()
                                           ? OPENED_IMG_PATH
                                           : CLOSED_IMG_PATH;
                                }
                            }))
            );

            add(new AjaxEventBehavior("onclick") {
                @Override protected void onEvent(
                        AjaxRequestTarget target) {
                    isOpen.setObject(
                            ! isOpen.getObject());

//                    for (Component detail : details()) {
//                        target.addComponent( detail );
//                    }
                }
            });

            add(new AttributeAppender(
                    "onclick",
                    new Model<String>(
                            "toggleDetail('" + javaScriptId + "')"),
                    ";"));
        }
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
    public class Detail extends Border
    {
        public Detail(String id) {
            super(id);
//            setOutputMarkupId(true);
//
//            Component wrap = new WebMarkupContainer("wrap") {
//                @Override public boolean isVisible() {
//                    return isOpen.getObject();
//                }
//            };
//            add(wrap);

            add(new SimpleAttributeModifier(
                    "id", javaScriptId));

            add(new AttributeModifier("style", true,
                    new Model<String>(){
                        public String getObject() {
                            return isOpen.getObject()
                                   ? "" : "display:none;";
                        }
                    }));
        }

//        @Override public boolean isVisible() {
//            return isOpen.getObject();
//        }
    }
}
