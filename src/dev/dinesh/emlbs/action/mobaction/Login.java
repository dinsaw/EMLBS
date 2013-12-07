/**
 * 
 */
package dev.dinesh.emlbs.action.mobaction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.UserDao;

/**
 * @author dinesh
 *
 */
public class Login extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String response;
	@Override
	public String execute() throws Exception {
		UserDao userDao = new UserDao();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		if (userDao.isAuthenticate(uname, pass)) {
			setResponse("AUTHSUCCESS");
		} else {
			setResponse("AUTHFAILURE");
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	

}
