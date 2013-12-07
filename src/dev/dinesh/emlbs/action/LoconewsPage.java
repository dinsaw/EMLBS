/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.dao.OverlayTypeDao;
import dev.dinesh.emlbs.persistence.AdminOverlayType;
import dev.dinesh.emlbs.persistence.App;

/**
 * @author dinesh
 *
 */
public class LoconewsPage extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path = "";
	private Double centerlat,centerlang;
	private Map session;
	private Date endDate;
	private Date today = new Date();
	private List<String> whowillseeList = new ArrayList<String>();
	private String defaultwhowillsee = "Everyone";
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
	public String display() {
		AppDao appDao = new AppDao();
		List<App> apps=appDao.getAllApps();
		Polygon polygon = apps.get(0).getArea();
		Point center = polygon.getCentroid();
		centerlat = center.getX();
		centerlang = center.getY();
		path=appDao.createJSONPoly(polygon);
		System.out.println("path:"+path);
		whowillseeList.add("Everyone");
		whowillseeList.add("Selected Area");
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
		
	}
	public List<String> getWhowillseeList() {
		return whowillseeList;
	}
	public void setWhowillseeList(List<String> whowillseeList) {
		this.whowillseeList = whowillseeList;
	}
	public String getDefaultwhowillsee() {
		return defaultwhowillsee;
	}
	public void setDefaultwhowillsee(String defaultwhowillsee) {
		this.defaultwhowillsee = defaultwhowillsee;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}

}
