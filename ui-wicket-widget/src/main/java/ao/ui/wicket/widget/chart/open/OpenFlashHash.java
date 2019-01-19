package ao.ui.wicket.widget.chart.open;

import ao.util.math.crypt.MD5;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Resource;
import org.apache.wicket.markup.html.WebResource;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.util.resource.AbstractStringResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

/**
* User: aostrovsky
* Date: 15-Oct-2009
* Time: 2:15:48 PM
*/
class OpenFlashHash
        extends Panel
        implements IResourceListener
{
    //--------------------------------------------------------------------
//    private static final Logger LOG =
//            LoggerFactory.getLogger(OpenFlashHash.class);

	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private final Resource deltaResource;


    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
    public OpenFlashHash(String id, final IModel<String> jsonModel) {
        super(id);

        final IResourceStream hash =
                new AbstractStringResourceStream("text/plain") {
                    @Override public String getString() {
                        String model = jsonModel.getObject();
                        return model == null
                               ? "n/a" : MD5.hexDigest(model);
                    }};
                    
        deltaResource = new WebResource() {
                @Override public IResourceStream getResourceStream() {
                    return hash;
                }
        }.setCacheable(false);
    }


    //--------------------------------------------------------------------
    public String hashUrl() {
        CharSequence dataPath = RequestCycle.get().urlFor(
                this, IResourceListener.INTERFACE);
        return RequestUtils.toAbsolutePath(dataPath.toString());
    }
//    public String hashUrlEncoded() {
//        CharSequence dataPath = RequestCycle.get().urlFor(
//                this, IResourceListener.INTERFACE);
//
//        // see http://www.nabble.com/
//        //       linking-to-a-text-ResourceReference-td19753402.html
//        try {
//            dataPath = URLEncoder.encode(
//                    dataPath.toString(), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            LOG.error("Error encoding dataPath for " +
//                        "Chart Json Hash data file.", e);
//        }
//
//        LOG.info("encoded path {}", dataPath);
//        return RequestUtils.toAbsolutePath(dataPath.toString());
//    }


    //--------------------------------------------------------------------
    public void onResourceRequested() {
        deltaResource.onResourceRequested();
    }
}
