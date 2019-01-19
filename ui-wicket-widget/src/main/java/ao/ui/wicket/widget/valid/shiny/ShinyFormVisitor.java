package ao.ui.wicket.widget.valid.shiny;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.ResourceModel;

/**
 * User: aostrovsky
 * Date: 24-Dec-2009
 * Time: 1:48:21 PM
 *
 * See http://code.google.com/p/elephas/source/browse/trunk/
 *          src/main/java/org/elephas/webapp/frontend/component/
 *          common/form/ShinyFormVisitor.java?r=73
 */
public class ShinyFormVisitor
        implements FormComponent.IVisitor, Serializable
{
    //--------------------------------------------------------------------
    private static final long serialVersionUID = 1L;


    //--------------------------------------------------------------------
    private Set<Component> visited = new HashSet<Component>();


    //--------------------------------------------------------------------
    private String getLabelId(Component c)
    {
        return c.getId() + "Label";
    }


    //--------------------------------------------------------------------
    public Object formComponent(
            IFormVisitorParticipant formComponent)
    {
        if (! isValidComponent(formComponent))
        {
            return Component.IVisitor.CONTINUE_TRAVERSAL;
        }

        FormComponent<?> component = (FormComponent) formComponent;
        if (! visited.contains(component))
        {
            visited.add(component);

            ShinyFormComponentLabel label =
                    new ShinyFormComponentLabel(
                            getLabelId(component), component);
            component.getParent().add(label);
            component.setLabel(new ResourceModel(component.getId()));
            component.add(new ErrorHighlightBehavior());
        }
        return Component.IVisitor.CONTINUE_TRAVERSAL;
    }

    
    //--------------------------------------------------------------------
    private boolean isValidComponent(IFormVisitorParticipant fc) {
        return   (fc instanceof FormComponent) &&
               ! (fc instanceof Button       ) &&
               ! (fc instanceof CheckBox     ) &&
               ! (fc instanceof RadioChoice  );
    }
}