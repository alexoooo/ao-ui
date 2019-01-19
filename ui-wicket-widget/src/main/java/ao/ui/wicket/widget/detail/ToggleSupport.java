package ao.ui.wicket.widget.detail;

import org.apache.wicket.Component;

public interface ToggleSupport
{
	public Component closedHeader(
			String id, ToggleListener listener);
	
	public Component openedHeader(
			String id, ToggleListener listener);
	
	public Component body(
			String id, ToggleListener listener);
}
