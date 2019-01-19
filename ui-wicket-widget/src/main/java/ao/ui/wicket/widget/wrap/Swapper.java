package ao.ui.wicket.widget.wrap;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: aostrovsky
 * Date: 19-Aug-2009
 * Time: 4:19:43 PM
 */
public class Swapper extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
    public static final String SWAP_ID = "swap";


    //--------------------------------------------------------------------
    private final Component defaultSwap;


    //--------------------------------------------------------------------
    public Swapper(String id, Component defaultSwapComponent) {
        super(id);
        setOutputMarkupId(true);

        defaultSwap = defaultSwapComponent;
        add(defaultSwap);
    }

    
    //--------------------------------------------------------------------
    public void revert()
    {
        replace(defaultSwap);
    }


    //--------------------------------------------------------------------
    public void set(Component swapComponent)
    {
        replace(swapComponent);
    }
}
