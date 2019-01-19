package ao.ui.wicket.widget.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: aostrovsky
 * Date: 17-Aug-2009
 * Time: 1:11:45 PM
 */
public class MethodModel<T> extends AbstractReadOnlyModel<T>
{
	//--------------------------------------------------------------------
	private static final long   serialVersionUID = 1L;
	
	private static final Logger LOG              =
        	LoggerFactory.getLogger(MethodModel.class);
	
	
	//--------------------------------------------------------------------
    private transient       Method    getter;
    private           final String    getterName;
    private           final IModel<?> model;


    //--------------------------------------------------------------------
    public MethodModel(ListItem<?> modelParent, String methodName)
    {
        this(modelParent.getModel(), methodName);
    }
    public MethodModel(IModel<?> modelParent, String methodName)
    {
//        this(modelParent.getObject(), methodName);
        model      = modelParent;
        getterName = methodName;
    }
    public MethodModel(Serializable modelParent, String methodName)
    {
        this(new Model<Serializable>(modelParent), methodName);
    }

    
    //--------------------------------------------------------------------
    private Method getter() {
    	if (getter == null) {
            try {
				getter = model.getObject().getClass().getMethod(
				            getterName, new Class[0]);
			} catch (Exception e) {
				LOG.error("Could not resolve method", e);
			}
    	}
    	return getter;
    }
    
    
    //--------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    @Override public T getObject() {
        try {
			return (T) getter().invoke(model.getObject());
		} catch (Exception e) {
			LOG.error("Could not call method", e);
			return null;
		}
    }
}
