/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.AppDao;

/**
 * @author dinesh
 *
 */
public class AppDetails extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private boolean appstatus;
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public String isSetupInitiated() {
		AppDao appDao = new AppDao();
		if (appDao.isAppSetupStarted()) {
			return SUCCESS;
		} else {
			return ERROR;
		}
		
	}
	public String toggleAppStatus() {
		AppDao appDao = new AppDao();
		setAppstatus(appDao.toggleAppStatus(appDao.getAllApps().get(0).getAppname()));
		//Map<String, Object> context = new HashMap<String, Object>();
		//context.put("appstatus", isAppstatus());
		//ActionContext.getContext().getValueStack().push(context);
		System.out.println("Application Stauts changed");
		return SUCCESS;
	}
	public boolean isAppstatus() {
		return appstatus;
	}
	public void setAppstatus(boolean appstatus) {
		this.appstatus = appstatus;
	}
}
