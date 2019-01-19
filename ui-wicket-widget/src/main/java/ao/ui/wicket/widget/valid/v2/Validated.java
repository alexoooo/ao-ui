package ao.ui.wicket.widget.valid.v2;

/**
 * User: aostrovsky
 * Date: 29-Dec-2009
 * Time: 6:09:33 PM
 */
public interface Validated<Chain>
{
    //--------------------------------------------------------------------
    public Chain add(ValidationListener listener);
}
