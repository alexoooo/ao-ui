package ao.ui.wicket.widget.detail.table_demo;

import ao.ui.wicket.widget.detail.ToggleListener;
import ao.ui.wicket.widget.detail.ToggleSupport;
import ao.util.math.rand.Rand;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class TableToggleRecord
		implements ToggleSupport, Serializable
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
	private final int A = Rand.nextInt();
	private final int B = Rand.nextInt();
	private final int C = Rand.nextInt();
	
	
	
	//--------------------------------------------------------------------
	
	
	
	
	//--------------------------------------------------------------------
	@Override public Component body(
			String id, ToggleListener listener) {
		return new Body(id, listener);
	}
	
	@Override public Component closedHeader(
			String id, ToggleListener listener) {
		return new Closed(id, listener);
	}
	
	@Override public Component openedHeader(
			String id, ToggleListener listener) {
		return new Opened(id, listener);
	}
	
	
	//--------------------------------------------------------------------
	public class Body extends Panel {
		private static final long serialVersionUID = 1L;
		public Body(String id, final ToggleListener listener) {
			super(id);
			setRenderBodyOnly(true);
		}
	}
	
	@SuppressWarnings("serial")
	public class Closed extends Panel {
		private static final long serialVersionUID = 1L;
		public Closed(String id, final ToggleListener listener) {
			super(id);
			setRenderBodyOnly(true);
			
			add(new Label("x", "x: " + A));
			add(new Label("y", "y: " + B));
			add(new Label("z", "z: " + C)
				.add(new AjaxEventBehavior("onclick") {
					@Override protected void onEvent(
							AjaxRequestTarget target) {
						listener.toggled(target);
					}
				}));
		}
	}
	
	@SuppressWarnings("serial")
	public class Opened extends Panel {
		private static final long serialVersionUID = 1L;
		public Opened(String id, final ToggleListener listener) {
			super(id);
			setRenderBodyOnly(true);
			
			add(new Label("x", "x: " + A));
			add(new Label("y", "y: " + B));
			add(new Label("z", "z: " + C)
				.add(new AjaxEventBehavior("onclick") {
					@Override protected void onEvent(
							AjaxRequestTarget target) {
						listener.toggled(target);
					}
				}));
		}
	}
}
