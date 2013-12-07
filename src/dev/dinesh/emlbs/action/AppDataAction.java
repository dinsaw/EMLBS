/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.persistence.App;

/**
 * @author dinesh
 *
 */
public class AppDataAction extends ActionSupport implements SessionAware,ModelDriven<App>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private App app = new App();
	private AppDao appDao = new AppDao();
	private List<App> apps = new ArrayList<App>();
	private List<String> appstatuslist = new ArrayList<String>();
	private String path = "";
	private Double centerlat,centerlang;
	@Override
	public String execute() throws Exception {
		List<App> apps=appDao.getAllApps();
		Polygon polygon = apps.get(0).getArea();
		Point center = polygon.getCentroid();
		centerlat = center.getX();
		centerlang = center.getY();
		path=appDao.createJSONPoly(polygon);
		System.out.println("path:"+path);
		return SUCCESS;
	}
	@Override
	public App getModel() {
		return getApp();
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
		
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public List<App> getApps() {
		return apps;
	}
	public void setApps(List<App> apps) {
		this.apps = apps;
	}
	public String listApps() {
		apps = appDao.getAllApps();
		appstatuslist.add("On");
		appstatuslist.add("Off");
		System.out.println("on off ppppppppppppppppp");
		return SUCCESS;
	}
	public List<String> getAppstatuslist() {
		return appstatuslist;
	}
	public void setAppstatuslist(List<String> appstatuslist) {
		this.appstatuslist = appstatuslist;
	}
	public String getDefaultAppStatus() {
		System.out.println("on off dddddddddddddddddddddd");
		if (appDao.getAllApps().get(0).isActive()) {
			return "On";
		} else {
			return "Off";
		}
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public Double getCenterlat() {
		return centerlat;
	}
	public void setCenterlat(Double centerlat) {
		this.centerlat = centerlat;
	}
	public Double getCenterlang() {
		return centerlang;
	}
	public void setCenterlang(Double centerlang) {
		this.centerlang = centerlang;
	}
}
