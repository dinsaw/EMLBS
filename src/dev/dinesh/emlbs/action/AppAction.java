/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.util.ProgramUtils;

/**
 * @author dinesh
 *
 */
public class AppAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private String appName;
	private String polyPoints;
	@Override
	public String execute() throws Exception {
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate[] points = ProgramUtils.getPolyCords(getPolyPoints());
		LinearRing shell = geometryFactory.createLinearRing(points);
		Polygon p = new Polygon(shell, null, geometryFactory);
		AppDao appDao = new AppDao();
		appDao.createApp(getAppName(), p);
		return SUCCESS;
	}
	@Override
	public void validate() {
		if (getAppName().trim().isEmpty()) {
			addFieldError("appName", "Application Name can not be empty.");
		} 
		if (getPolyPoints()==null) {
			addFieldError("polyPoints", "Please select polygon area in Map.");
		} else {
			System.out.println("pp:"+getPolyPoints());
		}
		
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPolyPoints() {
		return polyPoints;
	}
	public void setPolyPoints(String polyPoints) {
		this.polyPoints = polyPoints;
	}
	
}
