package ao.ui.wicket.widget.valid.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.model.IModel;

/**
 * User: aostrovsky
 * Date: 8-Jan-2010
 * Time: 9:36:02 AM
 */
public interface Validation<T>
        extends Serializable
{
    //--------------------------------------------------------------------
    public void validate(
            T            value,
            List<String> errors);


    //--------------------------------------------------------------------
    public static class Util
    {
        private Util() {}

        public static <T> boolean isValid(
                Validation<T> validation, IModel<T> value)
        {
            return isValid(validation, value.getObject());
        }

        public static <T> boolean isValid(
                Validation<T> validation, T value)
        {
            List<String> errors = new ArrayList<String>();
            validation.validate(value, errors);
            return errors.isEmpty();
        }
    }
}
