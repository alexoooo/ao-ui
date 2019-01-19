package ao.ui.wicket.widget.valid.v1;

import java.io.Serializable;
import java.util.List;

/**
 * User: aostrovsky
 * Date: 2-Oct-2009
 * Time: 12:37:52 PM
 */
public interface Validator<T> extends Serializable
{
    //--------------------------------------------------------------------
    public T validate(String value, List<String> errors);


    //--------------------------------------------------------------------
    public static class Impl
    {
        private Impl() {}

        public static Validator<Integer> newIntegerValidator()
        {
            return newIntegerValidator(
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    0);
        }

        public static Validator<Integer> newIntegerValidator(
                final int from,
                final int toInclusive,
                final int defaultValue)
        {
            return new Validator<Integer>() {
				private static final long serialVersionUID = 1L;

				public Integer validate(
                        String value, List<String> errors) {
                    try {
                        int val = Integer.parseInt(value);
                        if (val < from) {
                            throw new Error("Has to be >= " + from);
                        }
                        if (val > toInclusive) {
                            throw new Error("Has to be < " + toInclusive);
                        }
                        return val;
                    } catch (Throwable t) {
                        errors.add( t.getMessage() );
                        errors.add( "Defaulting to " +  defaultValue);
                        return defaultValue;
                    }
                }
            };
        }
    }
}
