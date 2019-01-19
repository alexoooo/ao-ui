package ao.ui.wicket.widget.date;

//import ao.util.data.Coll;

import ao.util.data.Colls;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * User: aostrovsky
 * Date: 30-Sep-2009
 * Time: 9:09:11 AM
 */
public class UpdatingDateTime extends Panel
{
	//--------------------------------------------------------------------
    private static final DateTimeFormatter yearMonthDateFormatter =
            DateTimeFormat.forPattern("m/d/YYYY");
//            DateTimeFormat.forPattern("YYYY-mm-dd");

    private static final long serialVersionUID = 1L;
    

    //--------------------------------------------------------------------
    private final IModel<LocalDate>  dateModel;
    private final IModel<LocalTime>  timeModel;
    private final IModel<String>     dateErrorModel;
    private final IModel<String>     timeErrorModel;


    private final WebMarkupContainer errorWrapper;


    //--------------------------------------------------------------------
    public UpdatingDateTime(
            String id, IModel<DateTime> model)
    {
        super(id, model);

        dateModel      = new Model<LocalDate>(null);
        timeModel      = new Model<LocalTime>(null);
        dateErrorModel = new Model<String>(null);
        timeErrorModel = new Model<String>(null);

        errorWrapper   = addErrorFeedback();

        addDateSelector();
        addTimeSelector();
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
	private WebMarkupContainer addErrorFeedback()
    {
        final WebMarkupContainer errorWrapper =
                new WebMarkupContainer("feedback-wrap");
        errorWrapper.setOutputMarkupId(true);

        final Label errorField = new Label("feedback",
                new AbstractReadOnlyModel<String>() {
                    @Override public String getObject() {
                        List<String> errors = new ArrayList<String>();
                        if (dateErrorModel.getObject() != null) {
                            errors.add( dateErrorModel.getObject() );
                        }
                        if (timeErrorModel.getObject() != null) {
                            errors.add( timeErrorModel.getObject() );
                        }
                        return Colls.join(errors, " | \n");
                    }}) {
            @Override public boolean isVisible() {
                return ! getDefaultModelObjectAsString().isEmpty();
            }
        };
        errorField.setOutputMarkupId(true);
        add(errorWrapper.add(errorField));

        return errorWrapper;
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
	private void addDateSelector()
    {
        final IModel<String> model = new Model<String>(null);
        TextField<String> dateField = new TextField<String>(
                "date-select", model);
        dateField.add(new OnChangeAjaxBehavior() {
            @Override protected void onUpdate(AjaxRequestTarget target) {
                updateDate(model, target);
            }});
        dateField.add(new AjaxFormComponentUpdatingBehavior("onblur") {
            @Override protected void onUpdate(AjaxRequestTarget target) {
                updateDate(model, target);
            }});
        dateField.add(new DatePicker());
        add(dateField);
    }

    private void updateDate(
            IModel<String>    model,
            AjaxRequestTarget target)
    {
        target.addComponent( errorWrapper );

        try {
            dateModel.setObject(
                    yearMonthDateFormatter
                            .parseDateTime( model.getObject() )
                            .toLocalDate());

            updateDateTimeModel();
            dateErrorModel.setObject(null);
        } catch (Throwable t) {
            dateModel.setObject(null);
            dateErrorModel.setObject( t.getMessage() );
        }
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("serial")
	private void addTimeSelector()
    {
        final IModel<String> model = new Model<String>(null);
        TextField<String> timeField = new TextField<String>(
                "time-select", model);

        timeField.add(new OnChangeAjaxBehavior() {
            @Override protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent( errorWrapper );

                try {
                    timeModel.setObject(
                            parseTime(model.getObject()));

                    if (timeModel.getObject() != null &&
                            dateModel.getObject() == null) {
                        throw new Error("Can't have time without date");
                    }

                    updateDateTimeModel();
                    timeErrorModel.setObject(null);
                } catch (Throwable t) {
                    timeModel.setObject( null );
                    timeErrorModel.setObject( t.getMessage() );
                }
            }});

        add(timeField);
    }

    private LocalTime parseTime(String time) {
        if (time == null || time.trim().isEmpty()) return null;

        String[] parts   = time.split(":");
        int      hours   = Integer.parseInt(parts[0]);
        int      minutes = 0;
        int      seconds = 0;

        if (parts.length > 1) {
            minutes = Integer.parseInt(parts[1]);

            if (parts.length > 2) {
                seconds = Integer.parseInt(parts[2]);

                if (parts.length > 3) {
                    throw new Error("Too many ':'s");
                }
            }
        }

        return new LocalTime(hours, minutes, seconds);
    }


    //--------------------------------------------------------------------
    private void updateDateTimeModel()
    {
        LocalDate date = dateModel.getObject();
        LocalTime time = timeModel.getObject();

        if (date == null) {
            setDefaultModelObject(null);
        } else {
            if (time == null) {
                setDefaultModelObject(
                        date.toDateTimeAtStartOfDay());
            } else {
                setDefaultModelObject(
                        date.toDateTime( time ));
            }
        }
    }
}
