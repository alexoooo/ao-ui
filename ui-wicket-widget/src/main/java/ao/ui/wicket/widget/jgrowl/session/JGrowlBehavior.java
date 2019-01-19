package ao.ui.wicket.widget.jgrowl.session;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

// See http://javathoughts.capesugarbird.com/2009/06/
//              replacing-wickets-feedbackpanel-with.html

/**
* Attach to any component to display jGrowl messages.
*
* Displays only session-level messages. If you need component-level
* messages, see http://pastebin.com/f6db2ec0e for an example. Basically,
* instead of Session.get().getFeedbackMessages(), you would call
* getComponent().getFeedbackMessage().
*
* Requires the following be included: "jquery.js", "jquery.ui.all.js",
* "jquery.jgrowl.js", "jquery.jgrowl.css". These can be downloaded
* from http://plugins.jquery.com/files/jGrowl-1.2.0.tgz.
*
* @author jsinai
* Based on an example by Alex Objelean, see the above link.
*/
public class JGrowlBehavior extends AbstractDefaultAjaxBehavior
{
	//--------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    
    //--------------------------------------------------------------------
    /**
     * Displays an info message that is sticky. The default is non-sticky.
     * Sample usage:
     *  session.getFeedbackMessages()
     *      .add(new FeedbackMessage(
     *             null, "my message", JGrowlBehavior.INFO_STICKY));
     */
    public static final int INFO_STICKY = 250;
    
    
    //--------------------------------------------------------------------
    @Override
    protected void respond(AjaxRequestTarget target) {
        final String feedbackMsg = renderFeedback();
        if (! StringUtils.isEmpty(feedbackMsg)) {
            target.appendJavascript(feedbackMsg);
        }
    }
    
    
    //--------------------------------------------------------------------
    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.renderJavascriptReference(
                new JavascriptResourceReference(JGrowlBehavior.class,
                        "../res/jquery-1.3.2.js"));

        response.renderJavascriptReference(
                new JavascriptResourceReference(JGrowlBehavior.class,
                        "../res/examples/jquery.ui.all.js"));

        response.renderJavascriptReference(
                new JavascriptResourceReference(JGrowlBehavior.class,
                        "../res/jquery.jgrowl.js"));

        response.renderCSSReference(
                new CompressedResourceReference(JGrowlBehavior.class,
                        "../res/jquery.jgrowl.css"));

        response.renderCSSReference(
                new CompressedResourceReference(JGrowlBehavior.class,
                        "JGrowlBehavior.css"));

        final String feedbackMsg = renderFeedback();
        if (!StringUtils.isEmpty(feedbackMsg)) {
            response.renderOnDomReadyJavascript(feedbackMsg);
        }
    }
    
    
    //--------------------------------------------------------------------
    private String renderFeedback() {
        this.getComponent().getFeedbackMessage();
        FeedbackMessages fm = Session.get().getFeedbackMessages();

//        ObjectMapper jsonMapper = new ObjectMapper();
        Iterator<FeedbackMessage> iter = fm.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            FeedbackMessage message = iter.next();
            if ((message.getReporter() != null) || message.isRendered()) {
                // If a component-level message, don't show it
                continue;
            }
            String cssClassSuffix =
                    (message.getLevel() == INFO_STICKY)
                     ? "INFO" : message.getLevelAsString();
            Serializable serializable = message.getMessage();
            String msg =
                    (serializable == null)
                    ? StringUtils.EMPTY
                    : serializable.toString();
//            jsonMapper.writeValue(sb, "test");

            sb.append("$.jGrowl(\"").append(
                            escape(msg)
                        ).append('\"');
            sb.append(", {");
            sb.append("theme: \'jgrowl-")
                    .append(cssClassSuffix).append("\'");
            if (message.getLevel() > FeedbackMessage.INFO) {
                sb.append(", sticky: true");
            }
            sb.append("}");
            sb.append(");");
            message.markRendered();
        }
        return sb.toString();
    }

    
    //--------------------------------------------------------------------
    private static String escape(String text) {
        return text.replaceAll("\n", "\\r")
                   .replaceAll("\"", "\\\\\"");
    }
}