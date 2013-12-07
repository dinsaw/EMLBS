/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.RequestStatusDao;

/**
 * @author dinesh
 *
 */
public class RequestStatusAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String requestStatusName;
	private String requestStatusDescription;
	private Map session;
	@Override
	public void validate() {
		if (requestStatusDescription.length()==0) {
			addFieldError(requestStatusDescription, "Description required.");
		} 
		if (requestStatusName.length()==0) {
			addFieldError(requestStatusName, "Name Required.");
		}
		RequestStatusDao requestStatusDao = new RequestStatusDao();
		if (requestStatusDao.getRequestStatus(requestStatusName)!=null) {
			addActionError("There is already one status with name : " +requestStatusName);
		}
		super.validate();
	}
	@Override
	public String execute() throws Exception {
		RequestStatusDao requestStatusDao = new RequestStatusDao();
		requestStatusDao.addRequestStatus(requestStatusName, requestStatusDescription);
		addActionMessage("New Status Added : "+requestStatusName);
		setRequestStatusDescription("");
		setRequestStatusName("");
		return INPUT;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public String getRequestStatusName() {
		return requestStatusName;
	}
	public void setRequestStatusName(String requestStatusName) {
		this.requestStatusName = requestStatusName;
	}
	public String getRequestStatusDescription() {
		return requestStatusDescription;
	}
	public void setRequestStatusDescription(String requestStatusDescription) {
		this.requestStatusDescription = requestStatusDescription;
	}
}
