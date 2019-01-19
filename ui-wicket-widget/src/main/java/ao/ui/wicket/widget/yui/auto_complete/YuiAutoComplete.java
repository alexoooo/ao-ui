package ao.ui.wicket.widget.yui.auto_complete;

import ao.ui.wicket.widget.yui.JsonUtils;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 28-Aug-2009
 * Time: 10:59:02 AM
 *
 * See http://ptrthomas.wordpress.com/2009/08/12
 *      /wicket-tutorial-yui-autocomplete-using-json-and-ajax/
 * Modified to work with ajax (model auto-updating) and
 *  made it into a dropdown as per
 *  http://developer.yahoo.com/yui/examples/autocomplete/ac_combobox.html  
 */
public abstract class YuiAutoComplete
        extends FormComponentPanel<String>
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private TextField<String>  textField;
    private WebMarkupContainer container;
    private SelectionListener  listener;
    private Component          toggle;
    
    
    //--------------------------------------------------------------------
    public YuiAutoComplete(String id, IModel<String> model) {
        this(id, model, new SelectionListener.Silent());
    }
    
    public YuiAutoComplete(
            String            id,
            IModel<String>    model,
            SelectionListener selectionListener) {
        super(id);

        textField = new TextField<String>("text", model);
        textField.setOutputMarkupId(true);
        add(textField);

        container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        add(container);

        toggle = new Label("toggle", "");
        toggle.setOutputMarkupId(true);
        add(toggle);
//        toggle.setVisible(false);

        add(new YuiAutoCompleteBehavior());

        listener = selectionListener;
    }
    
    
    //--------------------------------------------------------------------
    @Override public void updateModel() {
        textField.updateModel();
    }


    //--------------------------------------------------------------------
    private String getJsVarName() {
        return "YAHOO_widget_" + textField.getMarkupId();
    }


    //--------------------------------------------------------------------
    protected abstract String[] getChoices(String query);


    //--------------------------------------------------------------------
    private class YuiAutoCompleteBehavior
            extends AbstractDefaultAjaxBehavior
    {
		private static final long serialVersionUID = 1L;

		@Override public void renderHead(IHeaderResponse response) {
            super.renderHead(response);

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/yahoo-dom-event/yahoo-dom-event.js"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/yuiloader/yuiloader-min.js"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/animation/animation-min.js"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/element/element-min.js"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/button/button-min.js"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/datasource/datasource-min.js"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "../res/autocomplete/autocomplete-min.js"));

            response.renderCSSReference(
                    new CompressedResourceReference(YuiAutoComplete.class,
                            "../res/autocomplete/assets/" +
                                    "skins/sam/autocomplete.css"));

            response.renderJavascriptReference(
                    new JavascriptResourceReference(YuiAutoComplete.class,
                            "YuiAutoComplete.js"));

            response.renderCSSReference(
                    new CompressedResourceReference(YuiAutoComplete.class,
                            "YuiAutoComplete.css"));

            response.renderJavascript(
                    "var " + getJsVarName() + ";", getJsVarName());

            response.renderOnDomReadyJavascript(
                    getJsVarName() + " = " +
                    		"new YAHOO.widget.WicketAutoComplete(" +
                        "'" + textField.getMarkupId() + "'," +
                        "'" + toggle.getMarkupId()  + "'," +
                        "'" + getCallbackUrl() + "'," +
                        "'" + container.getMarkupId() + "');");

            response.renderOnDomReadyJavascript(
                    "autoCompleteSelectSendback(" +
                            getJsVarName() + "," +
                            "'" + getCallbackUrl() + "')");
        }

        @Override protected void respond(AjaxRequestTarget target) {
//            System.out.println("s: " + getRequest().getParameter("s"));
//            System.out.println("q: " + getRequest().getParameter("q"));
            
            String pick  = getRequest().getParameter("s");
            if (pick != null) {
                textField.getModel().setObject(pick);
                listener.itemClicked(target);
                return;
            }

            String   query      = getRequest().getParameter("q");
            String[] result     = getChoices(query);
            String   jsonResult = JsonUtils.marshal(result);
            target.appendJavascript(getJsVarName() +
                    ".dataSource.responseArray = " + jsonResult + ";");
            textField.getModel().setObject(query);
        }
    }


    //--------------------------------------------------------------------
    public static interface SelectionListener extends Serializable
    {
        public void itemClicked(AjaxRequestTarget target);

        @SuppressWarnings("serial")
		public static class Silent implements SelectionListener {
            public void itemClicked(AjaxRequestTarget target) {}
        }
    }
}
