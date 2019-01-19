package ao.ui.wicket.widget.chart.open;

import ro.nextreports.jofc2.model.Chart;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 19-Oct-2009
 * Time: 10:28:41 AM
 */
@SuppressWarnings("serial")
public class UpdatingChart extends Panel
{
    //--------------------------------------------------------------------
    public static UpdatingChart newInstance(
            String id, String width, String height, Chart chart) {
        return new UpdatingChart(id, width, height,
                new Model<String>(chart.toString()));
    }

    public static UpdatingChart newInstance(
            String id, String width, String height, String json) {
        return new UpdatingChart(id, width, height,
                new Model<String>(json));
    }

    public static UpdatingChart newInstance(
            String id, String width, String height,
            final IModel<Chart> chart) {
        return new UpdatingChart(id, width, height,
                new AbstractReadOnlyModel<String>() {
                    @Override public String getObject() {
                        Chart chartObject = chart.getObject();
                        return (chartObject == null
                               ? null : chartObject.toString());
                    }
                });
    }


    //--------------------------------------------------------------------
    public UpdatingChart(
            String         id,
            String         width,
            String         height,
            IModel<String> jsonModel)
    {
        super(id);

//        OpenFlashChart chart = new OpenFlashChart(
//                OfcPage.CHART_ID, width, height, jsonModel);
        OpenFlashChart chart = new OpenFlashChart(
                OfcPage.CHART_ID, "100%", "98%", jsonModel);

        InlineFrame ofcFrame =
                new InlineFrame("ofc-frame", new OfcPage(chart));

        ofcFrame.add(new SimpleAttributeModifier("width",  width));
        ofcFrame.add(new SimpleAttributeModifier("height", height));

        add(ofcFrame);
    }
}
