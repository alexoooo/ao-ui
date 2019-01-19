package ao.ui.wicket.widget.chart.open;

import org.apache.wicket.markup.html.WebPage;

/**
 * User: aostrovsky
 * Date: 19-Oct-2009
 * Time: 9:54:47 AM
 */
class OfcPage extends WebPage
{
    //--------------------------------------------------------------------
    public static final String CHART_ID = "open-flash-chart";


    //--------------------------------------------------------------------
    public OfcPage(OpenFlashChart chart) {
        add(chart);
    }
}
