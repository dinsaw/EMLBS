/**
 * 
 */
package dev.dinesh.emlbs.action.mobaction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.RequestDao;
import dev.dinesh.emlbs.dao.UserDao;
import dev.dinesh.emlbs.util.base.Base64;

/**
 * @author dinesh
 * 
 */
public class SendRequest extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String reply;

	@Override
	public String execute() throws Exception {
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		setReply("Got It");
		// System.out.println("got data :"+imageString+useroverlaytype+password+username+latitude+longitude+desc);
		UserDao userDao = new UserDao();
		if (userDao.isAuthenticate(username, password)) {
			String imageString = request.getParameter("image");
			String useroverlaytype = request.getParameter("useroverlaytype");
			String desc = request.getParameter("description");
			double latitude = Double.parseDouble(request
					.getParameter("latitude"));
			double longitude = Double.parseDouble(request
					.getParameter("longitude"));
			byte[] imageBytes = Base64.decode(imageString);
			RequestDao requestDao = new RequestDao();
			requestDao.addRequest(username, useroverlaytype, imageBytes,
					latitude, longitude, desc);
			setReply("Issued");
		} else {
			setReply("Failed");
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}
