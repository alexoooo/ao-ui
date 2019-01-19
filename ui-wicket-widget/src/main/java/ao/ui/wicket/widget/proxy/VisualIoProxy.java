package ao.ui.wicket.widget.proxy;

import org.apache.wicket.Component;

/**
 * User: aostrovsky
 * Date: 1-Sep-2009
 * Time: 9:46:44 AM
 */
public interface VisualIoProxy extends VisualProxy
{
    //--------------------------------------------------------------------
    public Component displayWicket(String id, boolean readOnly);


    //--------------------------------------------------------------------
    public static class Impl
    {
        private Impl() {}

        public static VisualIoProxy newInstance(
                final VisualProxy visualProxy) {
            return new VisualIoProxy() {
                public Component displayWicket(String id) {
                    return visualProxy.displayWicket(id);
                }
                public Component displayWicket(
                        String id, boolean readOnly) {
                    return visualProxy.displayWicket(id);
                }
            };
        }
    }
}
