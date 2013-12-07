/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.RequestDao;

/**
 * @author dinesh
 *
 */
public class ChangeRequestStatus extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private String requestStatus;
	private String requestId;
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public String changeRequestStatus() {
		RequestDao requestDao = new RequestDao();
		requestDao.changeRequestStatus(Long.parseLong(getRequestId()), requestStatus);
		return SUCCESS;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
}
