package ao.ui.wicket.widget.detail;

import org.apache.wicket.ajax.AjaxRequestTarget;

public interface ToggleListener
{
	public void opened (AjaxRequestTarget target);
	
	public void closed (AjaxRequestTarget target);
	
	public void toggled(AjaxRequestTarget target);
}
