package ao.ui.wicket.widget.jgrowl.session;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

/**
 * User: aostrovsky
 * Date: 4-Sep-2009
 * Time: 7:18:01 AM
 */
public class JGrowlTestPage extends WebPage
{
	//--------------------------------------------------------------------
    @SuppressWarnings("serial")
	public JGrowlTestPage()
    {
//        add(CSSPackageResource.getHeaderContribution(
//        		JGrowlTestPage.class, "JGrowlPage.css"));

        Form<?> form = new Form<Object>("form") {
            @Override protected void onSubmit() {
                Session.get().error("Test error");
                Session.get().warn("Test warning");
                Session.get().info("Test info");
                Session.get().getFeedbackMessages().add(
                        new FeedbackMessage(null, "Test sticky info",
                                JGrowlBehavior.INFO_STICKY));
            }
        };
        add(form);

        AjaxButton b = new AjaxButton("ajaxbutton", form) {
            @Override protected void onSubmit(
                    AjaxRequestTarget target, Form<?> form) {
                target.addComponent(form);
            }
        };
        form.add(b);
        form.add(new JGrowlBehavior());
    }
}