package ao.ui.wicket.widget.chart.jfree;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.CancelEventIfNoAjaxDecorator;
import org.apache.wicket.ajax.markup.html.IAjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * A mapped area segment of an image map that adds an Ajax link to the area as
 * well as a regular tooltip.
 * 
 * @author Jonny Wray
 */
public class MapArea extends WebMarkupContainer
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
	private String shape;
	private String coords;
	private String tooltipText;
	
	
	//--------------------------------------------------------------------
	/**
	 * Construct the map area
	 * 
	 * @param id
	 *            Component identifier
	 * @param model
	 *            Model
	 * @param shape
	 *            The specific area shape
	 * @param coords
	 *            The coordinates of the area as a comma separated list
	 * @param tooltipText
	 *            The tooltip text, or null to not include it
	 * @param linkCallback
	 *            The link callback function called when the area is click, or
	 *            null to have no link functionality
	 */
	@SuppressWarnings("serial")
	public MapArea(
			final String    id,
			final IModel<?> model,
			final String    shape,
			final String    coords,
			final String    tooltipText,
			final IAjaxLink linkCallback)
	{
		super(id, model);
		
		this.shape = shape;
		this.coords = coords;
		this.tooltipText = tooltipText;
		
		if (linkCallback != null) {
			add(new AjaxEventBehavior("onclick") {
				@Override protected void onEvent(
						AjaxRequestTarget target) {
					linkCallback.onClick(target);
				}

				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return new CancelEventIfNoAjaxDecorator();
				}
			});
		}
		
		setOutputMarkupId(true);
	}

	/**
	 * Construct the map area
	 * 
	 * @param id
	 *            Component identifier
	 * @param shape
	 *            The specific area shape
	 * @param coords
	 *            The coordinates of the area as a comma separated list
	 * @param tooltipText
	 *            The tooltip text, or null to not include it
	 * @param linkCallback
	 *            The link callback function called when the area is click, or
	 *            null to have no link functionality
	 */
	public MapArea(
			String id,
			String shape,
			String coords,
			String tooltipText,
			IAjaxLink linkCallback) {
		this(id, null, shape, coords, tooltipText, linkCallback);
	}

	
	//--------------------------------------------------------------------
	@Override
	protected void onComponentTag(final ComponentTag tag) {
		super.onComponentTag(tag);
		assert tag.getName().equals("area");
		tag.put("shape", shape);
		tag.put("coords", coords);
		tag.put("href", "#");
		if (tooltipText != null && !tooltipText.isEmpty()) {
			tag.put("title", tooltipText);
		}
	}
}
