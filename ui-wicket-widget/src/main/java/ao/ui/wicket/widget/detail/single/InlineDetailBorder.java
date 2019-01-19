package ao.ui.wicket.widget.detail.single;

import ao.ui.wicket.widget.detail.single.toggle.InlineDetailToggle;
import ao.ui.wicket.widget.proxy.VisualProxy;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * User: aostrovsky
 * Date: 8-Sep-2009
 * Time: 9:25:38 AM
 */
@SuppressWarnings("serial")
public class InlineDetailBorder
        extends Border
        implements Serializable
{
    //--------------------------------------------------------------------
    public static final String TITLE_ID = "header";
    

    //--------------------------------------------------------------------
    public InlineDetailBorder(
            String id,
            String title) {
        this(id, new Label(TITLE_ID, title));
    }
    public InlineDetailBorder(
            String  id,
            String  title,
            boolean initiallyOpen) {
        this(id, new Label(TITLE_ID, title), initiallyOpen);
    }
    public InlineDetailBorder(
            String          id,
            String          title,
            IModel<Boolean> isOpen) {
        this(id, new Label(TITLE_ID, title), isOpen);
    }
    public InlineDetailBorder(
            String      id,
            VisualProxy headerProxy) {
        this(id, headerProxy.displayWicket(TITLE_ID), false);
    }
    public InlineDetailBorder(
            String          id,
            VisualProxy     headerProxy,
            IModel<Boolean> isOpen) {
        this(id, headerProxy.displayWicket(TITLE_ID), isOpen);
    }
    public InlineDetailBorder(
            String    id,
            Component header) {
        this(id, header, false);
    }
    public InlineDetailBorder(
            String    id,
            Component header,
            boolean   initialIsOpen) {
        this(id, header, new Model<Boolean>(initialIsOpen));
    }


    //--------------------------------------------------------------------
    public InlineDetailBorder(
            String          id,
            Component       header,
            IModel<Boolean> isOpen)
    {
        super(id);

        InlineDetailToggle toggle =
                new InlineDetailToggle(isOpen);

        add(toggle.labelBorder("toggle")
                  .add(header));

        add(toggle.detailBorder("detail")
                  .setTransparentResolver(true));
    }
}