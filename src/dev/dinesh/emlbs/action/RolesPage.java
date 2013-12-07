/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.RequestHandlerDao;

/**
 * @author dinesh
 *
 */
public class RolesPage extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private List<String> reqHandlerList = new ArrayList<String>();
	
	public String display() {
		RequestHandlerDao requestHandlerDao = new RequestHandlerDao();
		setReqHandlerList(requestHandlerDao.getAllRequestHandlerNames());
		return SUCCESS;
	}
	@Override
	public void setSession(Map session) { this.session = session; }
	public List<String> getReqHandlerList() {
		return reqHandlerList;
	}
	public void setReqHandlerList(List<String> reqHandlerList) {
		this.reqHandlerList = reqHandlerList;
	}

	
}
