package ao.ui.wicket.widget.chart.jfree;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;

/**
 * Component that produces an image and associated image map from the given
 * JFreeChart chart. Uses the JFreeChart tooltip generator to provide tooltips
 * for the chart entities but does not use the JFreeChart URL generator, but
 * instead calls an Ajax callback function/
 * 
 * @author Jonny Wray
 *         <p/>
 *         see http://cwiki.apache.org/WICKET/
 *         jfreechart-with-clickable-imagemap.html
 */
public abstract class MappedChart extends Panel
{
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 4137002187344769160L;

	
	//-------------------------------------------------------------------------
	public MappedChart(
			String     panelId,
			JFreeChart chart,
			int        width,
			int        height)
	{
		super(panelId);
		ChartImage image = new ChartImage("image", chart, width, height);
		String mapName = getPath();
		image.add(new AttributeModifier("usemap", true, new Model<String>("#"
				+ mapName)));
		add(image);
		DynamicImageMap imageMap = constructImageMap(image, mapName);
		add(imageMap);
	}

	
	//-------------------------------------------------------------------------
	/**
	 * The callback method that is called when a specific image map entity is
	 * clicked on.
	 * 
	 * @param target
	 *            ajax handle
	 * @param entity
	 *            chart element
	 */
	protected abstract void onClickCallback(
			AjaxRequestTarget target, ChartEntity entity);
	
	
	//-------------------------------------------------------------------------
	private DynamicImageMap constructImageMap(
			ChartImage image,
			String     mapName)
	{
		DynamicImageMap  imageMap = new DynamicImageMap("imageMap", mapName);
		EntityCollection entities =
			image.getRenderingInfo().getEntityCollection();
		
		if (entities != null) {
			int count = entities.getEntityCount();
			for (int i = count - 1; i >= 0; i--) {
				final ChartEntity entity = entities.getEntity(i);
				imageMap.addArea(
						entity.getShapeType(),
						entity.getShapeCoords(),
						entity.getToolTipText(),
						new AjaxLink<Object>("link") {
							private static final long serialVersionUID = 1L;

							@Override
							public void onClick(AjaxRequestTarget target) {
								onClickCallback(target, entity);
							}
						});
			}
		}
		return imageMap;
	}
}