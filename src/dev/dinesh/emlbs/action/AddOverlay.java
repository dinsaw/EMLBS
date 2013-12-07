/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AdminOverlayDao;
import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.dao.OverlayTypeDao;
import dev.dinesh.emlbs.persistence.AdminOverlayType;
import dev.dinesh.emlbs.persistence.App;
import dev.dinesh.emlbs.persistence.OverlayType;

/**
 * @author dinesh
 *
 */
public class AddOverlay extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path = "";
	private Double centerlat,centerlang;
	private Map session;
	private Double lat,lang;
	private String overlayType;
	private List<String> overlayTypeList=new ArrayList<String>();
	@Override
	public void validate() {
		ini();
		System.out.println("in ________________validate");
		super.validate();
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		AdminOverlayDao adminOverlayDao = new AdminOverlayDao();
		String adminName = (String) session.get("userName");
		adminOverlayDao.addAdminOverlay(adminName, lat, lang, overlayTypeList.get(overlayTypeList.indexOf(overlayType)));
		addActionMessage("New Overlay Added:["+lat+","+lang+","+getOverlayType()+"]");
		return INPUT;
	}
	private void ini() {
		AppDao appDao = new AppDao();
		List<App> apps=appDao.getAllApps();
		Polygon polygon = apps.get(0).getArea();
		Point center = polygon.getCentroid();
		centerlat = center.getX();
		centerlang = center.getY();
		path=appDao.createJSONPoly(polygon);
		System.out.println("path:"+path);
		OverlayTypeDao overlayTypeDao = new OverlayTypeDao();
		List<AdminOverlayType> list = overlayTypeDao.getAllAdminOverlayTypes();
		for (AdminOverlayType adminOverlayType : list) {
			overlayTypeList.add(adminOverlayType.getName());
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
	public String display() {
		
		return SUCCESS;
	}
	@Override
	public void setSession(Map session) { this.session = session; }
	public List<String> getOverlayTypeList() {
		return overlayTypeList;
	}
	public void setOverlayTypeList(List<String> overlayTypeList) {
		this.overlayTypeList = overlayTypeList;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLang() {
		return lang;
	}
	public void setLang(Double lang) {
		this.lang = lang;
	}
	public String getOverlayType() {
		return overlayType;
	}
	public void setOverlayType(String overlayType) {
		this.overlayType = overlayType;
	}
}
