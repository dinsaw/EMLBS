/**
 * 
 */
package dev.dinesh.emlbs.action.json;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.dao.RequestDao;
import dev.dinesh.emlbs.persistence.App;
import dev.dinesh.emlbs.persistence.IssuedRequest;
import dev.dinesh.emlbs.util.jsonoverlay.Group;
import dev.dinesh.emlbs.util.jsonoverlay.Item;
import dev.dinesh.emlbs.util.jsonoverlay.Location;

/**
 * @author dinesh
 * 
 */
public class ComplaintsJSON extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Group> groups = new ArrayList<Group>();
	private String dir = "setup";
	private String action = "getOverlayTypeIcon";
	private String role;
	private String path = "";
	private Double centerlat,centerlang;
	@Override
	public String execute() throws Exception {
		AppDao appDao = new AppDao();
		List<App> apps=appDao.getAllApps();
		Polygon polygon = apps.get(0).getArea();
		Point center = polygon.getCentroid();
		centerlat = center.getX();
		centerlang = center.getY();
		path=appDao.createJSONPoly(polygon);
		List<Item> items = new ArrayList<Item>();
		if (role == null) {
			RequestDao requestDao = new RequestDao();
			List<IssuedRequest> issuedRequests = requestDao.getAllRequests();
			
			for (IssuedRequest issuedRequest : issuedRequests) {
				Location location = new Location(issuedRequest.getOverlay()
						.getLocPoint().getX(), issuedRequest.getOverlay()
						.getLocPoint().getY());
				String iconURL = "?imageId=";
				Item item = new Item(location, iconURL
						+ issuedRequest.getOverlay().getUserOverlayType()
								.getOverlayTypeId(),
						issuedRequest.getDescription());
				items.add(item);
			}
		} else {

		}
		groups.add(new Group(items));
		return SUCCESS;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Double getCenterlang() {
		return centerlang;
	}

	public void setCenterlang(Double centerlang) {
		this.centerlang = centerlang;
	}

	public Double getCenterlat() {
		return centerlat;
	}

	public void setCenterlat(Double centerlat) {
		this.centerlat = centerlat;
	}

}
