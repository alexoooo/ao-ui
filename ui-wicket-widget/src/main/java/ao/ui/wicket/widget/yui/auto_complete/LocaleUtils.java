package ao.ui.wicket.widget.yui.auto_complete;

import java.util.*;

/**
 * User: aostrovsky
 * Date: 28-Aug-2009
 * Time: 10:23:14 AM
 */
public class LocaleUtils
{
    //--------------------------------------------------------------------
    private LocaleUtils() {}


    //--------------------------------------------------------------------
    private static final Set<String> countries;

    static {
        final Locale[] locales = Locale.getAvailableLocales();
        countries = new TreeSet<String>();
        for(final Locale locale : locales) {
            countries.add(locale.getDisplayCountry());
        }
    }


    //--------------------------------------------------------------------
    public static String[] getCountryNamesMatching(String query) {
        List<String> list = new ArrayList<String>();
        for (final String country : countries) {
            if (country.toUpperCase().startsWith(query.toUpperCase())) {
                list.add(country);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
