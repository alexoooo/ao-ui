package ao.ui.wicket.widget.detail.table_demo;

import ao.ui.wicket.widget.detail.DetailToggle;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class TableToggleDemo
		extends Panel
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
	@SuppressWarnings("serial")
	public TableToggleDemo(String id)
	{
		super(id);
		
//		add(JavascriptPackageResource.getHeaderContribution(
//        		SimpleDetailToggle.class, "xthf-cmp.js"));
//		
//		add(new AbstractBehavior() {
//			@Override public void renderHead(IHeaderResponse response) {
//	            super.renderHead(response);
//	            
//	            response.renderOnLoadJavascript(
//	                    "new xTableHeaderFixed(" +
//	                    	"'freeze-pane', window, true);");
//			}
//		});
		
		List<TableToggleRecord> records =
			new ArrayList<TableToggleRecord>();
		for (int i = 0; i < 10; i++) {
			records.add(new TableToggleRecord());
		}
		
		add(new ListView<TableToggleRecord>("records", records) {
			@Override protected void populateItem(
					ListItem<TableToggleRecord> item) {
				item.setRenderBodyOnly(true);
				item.add(new DetailToggle(
							"record", new Model<Boolean>(false),
							item.getModelObject()
						));
			}
		});//.setRenderBodyOnly(true)
	}
}
