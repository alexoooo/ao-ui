package ao.ui.wicket.widget.config;

import org.apache.wicket.util.time.Duration;

/**
 * User: aostrovsky
 * Date: 17-Jan-2010
 * Time: 4:04:54 PM
 */
public class UiConfig
{
    //--------------------------------------------------------------------
    private static class Singleton {
        private static final UiConfig INSTANCE = new UiConfig();
    }

    public static UiConfig get()
    {
        return Singleton.INSTANCE;
    }


    //--------------------------------------------------------------------
    private Duration updateDelay;


    //--------------------------------------------------------------------
    private UiConfig()
    {
        setUpdateDelay(Duration.milliseconds(250));
    }


    //--------------------------------------------------------------------
    public void setUpdateDelay(Duration duration)
    {
        updateDelay = duration;
    }

    public Duration updateDelay()
    {
        return updateDelay;
    }
}
