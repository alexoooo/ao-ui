package ao.ui.wicket.widget.detail.simple;

import ao.ui.wicket.widget.detail.DetailToggle;
import ao.ui.wicket.widget.detail.ToggleListener;
import ao.ui.wicket.widget.detail.ToggleSupport;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SimpleDetailToggle
		extends    Panel
		implements ToggleSupport
{
	//--------------------------------------------------------------------
	private static final long   serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
	public static final String BODY_ID = "body";
	
	
    //--------------------------------------------------------------------
    private final String    headerText;
    private final Component detailComponent;
    
    
    //--------------------------------------------------------------------
    public SimpleDetailToggle(
            String    id,
            String    header,
            Component body) {
        this(id, header, body, false);
    }
    
    public SimpleDetailToggle(
            String    id,
            String    header,
            Component body,
            boolean   initialIsOpen) {
        this(id, header, body, new Model<Boolean>(initialIsOpen));
    }


    //--------------------------------------------------------------------
    public SimpleDetailToggle(
            String          id,
            String          header,
            Component       body,
            IModel<Boolean> isOpen)
    {
        super(id);
        
        headerText      = header;
        detailComponent = body;
        
        add(CSSPackageResource.getHeaderContribution(
        		SimpleDetailToggle.class, "SimpleDetailToggle.css"));
        
        add(new DetailToggle(
        		"toggle", isOpen, this));
//        add(new Label("toggle", "test"));
    }
    
    
    //--------------------------------------------------------------------
	@Override public Component body(
			String id, ToggleListener listener) {
		return new Fragment(id, "body", this)
					.add(detailComponent);
	}
	
	@Override public Component closedHeader(
			String id, final ToggleListener listener) {
		return toggleHeader(id, listener, "header-closed");
	}
	
	@Override public Component openedHeader(
			String id, ToggleListener listener) {
		return toggleHeader(id, listener, "header-opened");
	}
	
	
	@SuppressWarnings("serial")
	private Component toggleHeader(
			final String         id,
			final ToggleListener listener,
			final String         fragmentId) {
		return new Fragment(id, fragmentId, this)
				.add(new Label("title", headerText)
					.add(new AjaxEventBehavior("onclick") {
						@Override protected void onEvent(
								AjaxRequestTarget target) {
							listener.toggled(target);
						}
					})
				);
	}
}
