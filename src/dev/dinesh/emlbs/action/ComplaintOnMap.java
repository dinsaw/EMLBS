/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.dao.RequestDao;
import dev.dinesh.emlbs.dao.RequestStatusDao;
import dev.dinesh.emlbs.persistence.App;
import dev.dinesh.emlbs.persistence.IssuedRequest;

/**
 * @author dinesh
 *
 */
public class ComplaintOnMap extends ActionSupport implements SessionAware,ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private long requestId;
	private IssuedRequest issuedRequest;
	private HttpServletRequest httpServletRequest;
	private List<String> requestStatusList = new ArrayList<String>();
	private double centerlat;
	private double centerlang;
	private String path;
	private String role;
	private void prepareStatusList() {
		System.out.println("Role:"+getRole());
		RequestStatusDao dao = new RequestStatusDao();
		System.out.println("dao");
		setRequestStatusList(dao.getAllStatusNames());
		System.out.println("getall");
		for (String status : getRequestStatusList()) {
			System.out.println(status);
		}
		
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	@Override
	public String execute() throws Exception {
		prepareStatusList();
		AppDao appDao = new AppDao();
		List<App> apps=appDao.getAllApps();
		Polygon polygon = apps.get(0).getArea();
		setPath(appDao.createJSONPoly(polygon));
		System.out.println("path:"+getPath());
		
		RequestDao requestDao =  new RequestDao();
		setRequestId(Long.parseLong(httpServletRequest.getParameter("id")));
		setIssuedRequest(requestDao.getRequest(getRequestId()));
		
		setCenterlat(issuedRequest.getOverlay().getLocPoint().getCoordinate().x);
		setCenterlang(issuedRequest.getOverlay().getLocPoint().getCoordinate().y);
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.httpServletRequest = request;
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public IssuedRequest getIssuedRequest() {
		return issuedRequest;
	}
	public void setIssuedRequest(IssuedRequest issuedRequest) {
		this.issuedRequest = issuedRequest;
	}
	public double getCenterlat() {
		return centerlat;
	}
	public void setCenterlat(double centerlat) {
		this.centerlat = centerlat;
	}
	public double getCenterlang() {
		return centerlang;
	}
	public void setCenterlang(double centerlang) {
		this.centerlang = centerlang;
	}
	public List<String> getRequestStatusList() {
		return requestStatusList;
	}
	public void setRequestStatusList(List<String> requestStatusList) {
		this.requestStatusList = requestStatusList;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
