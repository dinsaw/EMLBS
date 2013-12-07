/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.OverlayTypeDao;
import dev.dinesh.emlbs.dao.RequestHandlerDao;
import dev.dinesh.emlbs.persistence.RequestHandeler;
import dev.dinesh.emlbs.persistence.UserOverlayType;

/**
 * @author dinesh
 *
 */
public class HandlerAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private String handlerName;
	private String handlerDescription;
	private String userOTypeString;
	private RequestHandlerDao dao = new RequestHandlerDao();
	private List<String> userOverlayTypes = new ArrayList<String>();
	public String display() {
		prepareOverlayTypeList();
		return SUCCESS;
	}
	private void prepareOverlayTypeList() {
		OverlayTypeDao overlayTypeDao = new OverlayTypeDao();
		userOverlayTypes = overlayTypeDao.getAllUserOverlayTypeNames();
	}
	@Override
	public void validate() {
		super.validate();
		System.out.println("validate"+handlerName);
		if (dao.isHandlerPresent(handlerName)) {
			addFieldError(handlerName, "A handler with this name already present");
		}
		prepareOverlayTypeList();
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	public String getHandlerDescription() {
		return handlerDescription;
	}
	public void setHandlerDescription(String handlerDescription) {
		this.handlerDescription = handlerDescription;
	}
	@Override
	public String execute() {
		prepareOverlayTypeList();
		System.out.println("method");
		dao.addHandeler(handlerName, handlerDescription, userOTypeString);
		addActionMessage("New Handeler Added : "+handlerName);
		setHandlerName("");
		setHandlerDescription("");
		return INPUT;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public String getUserOTypeString() {
		return userOTypeString;
	}
	public void setUserOTypeString(String userOTypeString) {
		this.userOTypeString = userOTypeString;
	}
	public List<String> getUserOverlayTypes() {
		return userOverlayTypes;
	}
	public void setUserOverlayTypes(List<String> userOverlayTypes) {
		this.userOverlayTypes = userOverlayTypes;
	}
	
}
