package ao.ui.wicket.widget.detail;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class DetailToggle
		extends    Panel
		implements ToggleListener
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
	private static final String HEADER_ID = "header";
	private static final String DETAIL_ID = "detail";
	
	
	//--------------------------------------------------------------------
	private final ToggleSupport toggleSupport;
	
	
	//--------------------------------------------------------------------
	public DetailToggle(
			String 			id,
			IModel<Boolean> isOpen,
			ToggleSupport   support)
	{
		super(id, isOpen);
		toggleSupport = support;
		
		setOutputMarkupId(true);
		
		reBuild();
	}
	
	
	//--------------------------------------------------------------------
	private void reBuild()
	{
		if (isOpen()) {
			addOrReplace(toggleSupport.openedHeader(HEADER_ID, this));
			addOrReplace(toggleSupport.body        (DETAIL_ID, this));
		} else {
			addOrReplace(toggleSupport.closedHeader(HEADER_ID, this));
			addOrReplace(new WebMarkupContainer(
								DETAIL_ID).setVisible(false));
		}
	}
	
	
	//--------------------------------------------------------------------
	public boolean isOpen() {
		return getModel().getObject();
	}
	
	@SuppressWarnings("unchecked")
	public IModel<Boolean> getModel() {
		return (IModel<Boolean>) getDefaultModel();
	}
	

	//--------------------------------------------------------------------
	@Override public void closed(AjaxRequestTarget target) {
		changeState(false, target);
	}

	@Override public void opened(AjaxRequestTarget target) {
		changeState(true, target);
	}

	@Override public void toggled(AjaxRequestTarget target) {
		changeState(! isOpen(), target);
	}
	
	private void changeState(
			boolean toOpen, AjaxRequestTarget target) {
		if (isOpen() == toOpen) return;
		getModel().setObject(toOpen);
		target.addComponent(this);
		reBuild();
	}
}
