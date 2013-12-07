/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.persistence.App;

/**
 * @author dinesh
 *
 */
public class AppAreaJSONAction extends ActionSupport implements SessionAware{
	private Map session;
	private List<HashMap<String, Double>> markers= new ArrayList<HashMap<String,Double>>();
	@Override
	public String execute() throws Exception {
		AppDao appDao = new AppDao();
		App app = appDao.getAllApps().get(0);
		Polygon polygon = app.getArea();
		Coordinate[] coordinates=polygon.getCoordinates();
		for (int i = 0; i < coordinates.length; i++) {
			HashMap<String, Double> hashMap = new HashMap<String, Double>();
			hashMap.put("latitude", new Double(coordinates[i].x));
			hashMap.put("longitude", new Double(coordinates[i].y));
			getMarkers().add(hashMap);
			System.out.println("aadddesd");
		}
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public List<HashMap<String, Double>> getMarkers() {
		return markers;
	}
	public void setMarkers(List<HashMap<String, Double>> markers) {
		this.markers = markers;
	}

}
