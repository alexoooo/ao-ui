package ao.ui.wicket.widget.sample.home;

import ao.ui.wicket.widget.base.BasePage;
import ao.ui.wicket.widget.border.button.ButtonBorder;
import ao.ui.wicket.widget.border.title_box.TitleBorder;
import ao.ui.wicket.widget.chart.jfree.ChartImage;
import ao.ui.wicket.widget.chart.open.UpdatingChart;
import ao.ui.wicket.widget.date.UpdatingDateTime;
import ao.ui.wicket.widget.detail.simple.SimpleDetailToggle;
import ao.ui.wicket.widget.detail.table_demo.TableToggleDemo;
import ao.ui.wicket.widget.jgrowl.session.JGrowlBehavior;
import ao.ui.wicket.widget.live.text.LiveText;
import ao.ui.wicket.widget.live.text.table.LiveTextRow;
import ao.ui.wicket.widget.sample.form.CrudItem;
import ao.ui.wicket.widget.text.ReadWriteText;
import ao.ui.wicket.widget.valid.v2.Validation;
import ao.ui.wicket.widget.valid.v2.ValidationListener;
import ao.ui.wicket.widget.yui.auto_complete.LocaleUtils;
import ao.ui.wicket.widget.yui.auto_complete.YuiAutoComplete;
import ao.util.math.rand.Rand;
import ao.util.time.Sched;
import java.util.List;
import ro.nextreports.jofc2.model.Chart;
import ro.nextreports.jofc2.model.elements.BarChart;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.joda.time.DateTime;
import org.wicketstuff.artwork.niftycorners.NiftyCornersBehavior;
import org.wicketstuff.artwork.niftycorners.NiftyOption;

/**
 * Homepage
 */
@SuppressWarnings("serial")
public class UiWidgetIndex extends BasePage
{
	//--------------------------------------------------------------------
//	private static final long   serialVersionUID = 1L;

//	private static final Logger LOG              =
//        	LoggerFactory.getLogger(UiIndex.class);
	
	
	//--------------------------------------------------------------------
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 */
	public UiWidgetIndex()
	{
		setTitle("UI Test Page");

        add(new LiveTextRow("text-row", "Field:"));

        IModel<String> validLiveText = new Model<String>("123");
        final LiveText liveText = new LiveText(
                "live-text", LiveText.Type.LINE, validLiveText);
        liveText.add(new Validation<String>() {
                @Override public void validate(
                        String value, List<String> errors)
                {
                    if (value.trim().length() == 0) {
                        errors.add("Must fill out field");
                    } else if (! value.matches("\\d+")) {
                        errors.add("Must only use digits");
                    }
                }
            });
        liveText.add(new ValidationListener() {
            @Override public void hear(
                    List<String> errors, AjaxRequestTarget target) {
                System.out.println( errors + " :: " + liveText.isValid() );
            }});
        add(liveText);
//        liveText.setAllowEdit( false );

//        liveText
        
        add(new CrudItem("crud"));

        IModel<String> sampleText = new Model<String>("Click to edit");
        final Component labelDisplay = new Label("sample-text", sampleText);
        labelDisplay.setOutputMarkupId(true);
        add(labelDisplay);
        add(new AjaxEditableLabel<String>("sample-text-edit", sampleText) {
            @Override public void onSubmit(AjaxRequestTarget target) {
                target.addComponent( labelDisplay );
            }
        });

		add(new TableToggleDemo("table-toggle-demo"));
		
		add(new WebMarkupContainer("outer-round-border")
				.add(new WebMarkupContainer("round-border")
						.add(new NiftyCornersBehavior(
								NiftyOption.big, NiftyOption.fixedheight)))
				.add(new NiftyCornersBehavior(
							NiftyOption.top, NiftyOption.sameheight)));
		
//		add(new RoundBorder("round-border").setTransparentResolver(true));
		
		// Add the simplest type of label
		add(new Label("message", "Test"));

		IModel<String> model = new Model<String>("");
		YuiAutoComplete combo = new YuiAutoComplete("auto-complete", model) {
			@Override protected String[] getChoices(String query) {
				return LocaleUtils.getCountryNamesMatching(query);
			}
		};
		add(combo);

		IModel<String> biDirectional = new Model<String>("");
		ReadWriteText ioText = new ReadWriteText(biDirectional);
		add(ioText.displayWicket("read-end", true));
		add(ioText.displayWicket("write-end", false));

		add(new AjaxLink<String>("submit") {
			@Override public void onClick(AjaxRequestTarget target) {
				growl(this, target);

				Session.get().error("Test error");
				Session.get().warn("Test warning");
				Session.get().info("Test info");
				Session.get().getFeedbackMessages().add(
						new FeedbackMessage(null, "Test sticky info",
								JGrowlBehavior.INFO_STICKY));
			}
		});

		add(new ButtonBorder("button").add(
				new AjaxLink<Object>("click") {
					public void onClick(AjaxRequestTarget target) {
						growl(this, target);
						
						Session.get().info("Long running opp done.");
						Sched.sleep(5 * 1000);
					}
				}));

		add(new TitleBorder("title-border", "Hello World")
					.add( new Label("label", "Contents!!!") )
					.add( new TitleBorder("sub", "Hello World") ));

		add(new SimpleDetailToggle(
				"detail-toggle", "Header Test",
				new Label(SimpleDetailToggle.BODY_ID, "<Body>")));
		
//		add(new DetailBorder("toggle-border", "Toggle Header", true)
//				.add(new Label("toggle-detail", "toggled detail")));
		
		IModel<DateTime> dateModel = new Model<DateTime>(null);
        UpdatingDateTime dateField =
                new UpdatingDateTime("date-select", dateModel);
        add(dateField);
        
        addJFreeChart();
        addOpenChart();
	}
	
	
	//--------------------------------------------------------------------
    private void addOpenChart() {
        add(UpdatingChart.newInstance("open-chart", "100%", "300",
                new AbstractReadOnlyModel<Chart>() {
                    private Chart nextChart = generateRandomChart();
                    @Override public Chart getObject() {
                        if (Rand.nextBoolean(5)) {
                            nextChart = generateRandomChart();
                        }
                        return nextChart;
                    }
                }));
    }

    private Chart generateRandomChart() {
        BarChart data = new BarChart(
                BarChart.Style.GLASS);

        // todo: implement this
//        data.useAnimation(false);

        for (int i = 0; i < 32; i++) {
            data.addValues(Math.random() * 5);
        }

        final Chart chart = new Chart("Title");
        chart.addElements(data);
        return chart;
    }


    //--------------------------------------------------------------------
    private void addJFreeChart() {
        XYSeries series = new XYSeries("GC Time");
        for (int i = 0; i < 100; i++) {
//            series.add(i * 5 + Rand.nextDouble(100), i * 2 + 5);
//            series.add(i * 2 + 5, i * 5 + Rand.nextDouble(100));
            series.add(new XYDataItem(
                    i * 2 + 5,
                    i * 5 + Math.random() * 100
            ));
        }

        XYDataset data = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sample Chart Title",
                "X Axis Label",
                "Y Axis Label",
                data,
                PlotOrientation.VERTICAL,
                true, // show legend
                true, // tooltips
                true  // urls
        );

        add(new ChartImage("jfree-chart", chart, 600, 300));
    }
}
