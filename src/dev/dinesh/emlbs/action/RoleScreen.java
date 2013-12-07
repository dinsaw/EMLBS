/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.RequestDao;
import dev.dinesh.emlbs.dao.RequestStatusDao;
import dev.dinesh.emlbs.persistence.IssuedRequest;

/**
 * @author dinesh
 *
 */
public class RoleScreen extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private List<String> requestStatusList = new ArrayList<String>();
	private List<Integer> requestsPerStatus = new ArrayList<Integer>();
	private List<IssuedRequest> issuedRequests = new ArrayList<IssuedRequest>();
	private String showForStatus = "All";
	

	private String role;
	@Override
	public String execute() throws Exception {
		prepareStatusList();
		return SUCCESS;
	}
	public String display() {
		prepareStatusList();
		return SUCCESS;
	}
	private void prepareStatusList() {
		System.out.println("Role:"+role);
		RequestStatusDao dao = new RequestStatusDao();
		System.out.println("dao");
		setRequestStatusList(dao.getAllStatusNames());
		System.out.println("getall");
		for (String status : getRequestStatusList()) {
			System.out.println(status);
		}
		
		RequestDao requestDao = new RequestDao();
		if (showForStatus.equals(RequestStatusDao.STATUSALL)) {
			issuedRequests = requestDao.getRequests(role);
		} else if (showForStatus.equals(RequestStatusDao.STATUSISSUE)) {
			issuedRequests = requestDao.getRequests(role, null);
		} else {
			issuedRequests = requestDao.getRequests(role, showForStatus);
		}
	}
	@Override
	public void setSession(Map session) { this.session = session; }
	public List<String> getRequestStatusList() {
		return requestStatusList;
	}
	public void setRequestStatusList(List<String> requestStatusList) {
		this.requestStatusList = requestStatusList;
	}
	public String getShowForStatus() {
		return showForStatus;
	}
	public void setShowForStatus(String showForStatus) {
		this.showForStatus = showForStatus;
	}
	public List<IssuedRequest> getIssuedRequests() {
		return issuedRequests;
	}
	public void setIssuedRequests(List<IssuedRequest> issuedRequests) {
		this.issuedRequests = issuedRequests;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
