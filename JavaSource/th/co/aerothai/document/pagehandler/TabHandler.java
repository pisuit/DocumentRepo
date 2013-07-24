package th.co.aerothai.document.pagehandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

@ManagedBean(name="tabHandler")
@ViewScoped
public class TabHandler {
	
	public void ontabChange(TabChangeEvent event){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("layoutWidget.toggle('west')");	
	}
}
