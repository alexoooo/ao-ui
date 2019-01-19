package ao.ui.wicket.widget.proxy;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;

/**
 * User: aostrovsky
 * Date: 17-Aug-2009
 * Time: 12:52:04 PM
 */
public interface VisualProxy// extends Serializable
{
    //--------------------------------------------------------------------
    public Component displayWicket(String id);

    
    //--------------------------------------------------------------------
    public static class Impl
    {
        private Impl() {}

        public static VisualProxy newInstance(
                final VisualIoProxy visualProxy,
                final boolean       readOnly) {
            return new VisualProxy() {
                public Component displayWicket(String id) {
                    return visualProxy.displayWicket(id, readOnly);
                }
            };
        }
        
        public static VisualProxy newInstance(
        		final String label) {
        	return new VisualProxy() {
                public Component displayWicket(String id) {
                    return new Label(id, label);
                }
            };
        }
    }
}
