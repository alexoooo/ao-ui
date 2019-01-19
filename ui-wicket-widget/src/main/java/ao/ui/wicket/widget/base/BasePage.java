package ao.ui.wicket.widget.base;

import ao.ui.wicket.widget.busy.BusyIndicator;
import ao.ui.wicket.widget.jgrowl.session.JGrowlBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author aostrovsky
 */
public class BasePage extends WebPage
{
	//--------------------------------------------------------------------
    private IModel<String> titleModel;
    private Component      growl;


    //--------------------------------------------------------------------
    public BasePage()
    {
        titleModel = new Model<String>(
        		"Change this title by using BasePage.setTitle()");

        add(new Label("title", titleModel));
        
        add(new BusyIndicator("busy-indicator"));

        initGrowlTarget();
    }


    //--------------------------------------------------------------------
    private void initGrowlTarget()
    {
        growl = new Label("growl", "");
        growl.setOutputMarkupId(true);
        growl.add( new JGrowlBehavior() );
        add(growl);
    }


    //--------------------------------------------------------------------
    protected void setTitle(String title)
    {
        titleModel.setObject(title);
    }


    //--------------------------------------------------------------------
    public Component growl()
    {
        return growl;
    }

    public static void growl(
            Component         from,
            AjaxRequestTarget target)
    {
        target.addComponent(
                ((BasePage) from.getPage()).growl());
    }
}

