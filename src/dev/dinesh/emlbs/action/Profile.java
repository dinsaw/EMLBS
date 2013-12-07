/**
 * 
 */
package dev.dinesh.emlbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * @author dinesh
 *
 */
public class Profile {
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String s = (String) session.getAttribute("login");
		if (s!=null && !s.equals("")) {
			return "SUCCESS";
		} else {
			return "FAILURE";
		}
	}
}
