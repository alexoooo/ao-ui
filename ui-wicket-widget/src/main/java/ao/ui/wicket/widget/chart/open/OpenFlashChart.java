package ao.ui.wicket.widget.chart.open;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.util.collections.MiniMap;
import org.apache.wicket.util.resource.AbstractStringResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.template.TextTemplateHeaderContributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Panel for showing a Flash Chart
 * See http://cwiki.apache.org/WICKET/
 * open-flash-chart-and-wicket.html
 */
class OpenFlashChart
        extends Panel
        implements IResourceListener
{
	//--------------------------------------------------------------------
    private static final Logger            LOG              =
            LoggerFactory.getLogger(OpenFlashChart.class);

    private static final long              serialVersionUID = 1L;
    
    private static final ResourceReference SWF_RESOURCE     =
            new ResourceReference(OpenFlashChart.class,
                                  "open-flash-chart2.swf");
    
    private static final int			   TIMEOUT          = 5000;
    
    
    //--------------------------------------------------------------------
    private final Resource      jsonResource;
    private final SWFObject     swf;
    private final OpenFlashHash delta;


    //--------------------------------------------------------------------
    public OpenFlashChart(
            final String         id,
            final String         width,
            final String         height,
            final IModel<String> jsonModel) {
        super(id);
        setOutputMarkupId(true);

        jsonResource = new WebResource() {
				private static final long serialVersionUID = 1L;
				
				@SuppressWarnings("serial")
				private final IResourceStream json =
                        new AbstractStringResourceStream("text/plain") {
                            @Override public String getString() {
                                return jsonModel.getObject();
                            }};
                
                @Override public IResourceStream getResourceStream() {
                    return json;
                }
        }.setCacheable(false);

        delta = new OpenFlashHash("delta", jsonModel);
        add(delta);

        String swfURL = RequestUtils.toAbsolutePath(
                urlFor(SWF_RESOURCE).toString());

        add(swf = new SWFObject(swfURL, width, height, "9.0.0"));

        add(JavascriptPackageResource.getHeaderContribution(
                OpenFlashChart.class, "find_swf.js"));
        add(JavascriptPackageResource.getHeaderContribution(
                OpenFlashChart.class, "fetch.js"));

        IModel<Map<String, Object>> varMap =
                new AbstractReadOnlyModel<Map<String, Object>>() {
					private static final long serialVersionUID = 1L;
					
					private Map<String, Object> vars;
					
                    @Override public Map<String, Object> getObject() {
                        if (vars == null) {
                            vars = new MiniMap<String, Object>(4);
                            vars.put("chartId"     , getMarkupId());
                            vars.put("chartJsonUrl", jsonUrl());
                            vars.put("chartHashUrl", delta.hashUrl());
                            vars.put("timeout"     , TIMEOUT);
                        }
                        return vars;
                    }
                };
        
        add(TextTemplateHeaderContributor.forJavaScript(
                OpenFlashChart.class, "chart_reload.js", varMap));
    }

    
    //--------------------------------------------------------------------
    private String jsonUrl() {
        CharSequence dataPath = RequestCycle.get().urlFor(
                this, IResourceListener.INTERFACE);
        return RequestUtils.toAbsolutePath(dataPath.toString());
    }
    private String jsonUrlEncoded() {
        CharSequence dataPath = RequestCycle.get().urlFor(
                this, IResourceListener.INTERFACE);

        // see http://www.nabble.com/
        //          linking-to-a-text-ResourceReference-td19753402.html
        try {
            dataPath = URLEncoder.encode(dataPath.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error encoding dataPath for " +
                        "Chart Json data file.", e);
        }

        return RequestUtils.toAbsolutePath(dataPath.toString());
    }


    //--------------------------------------------------------------------
    @Override protected void onBeforeRender() {
        swf.addParameter("data-file", jsonUrlEncoded());
//        swf.addParameter("loading", "");
        super.onBeforeRender();
    }


    //--------------------------------------------------------------------
    /**
     * Actually handle the request
     */
    public void onResourceRequested() {
        jsonResource.onResourceRequested();
    }
}