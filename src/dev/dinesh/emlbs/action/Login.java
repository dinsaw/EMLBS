/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.AdminOverlayDao;
import dev.dinesh.emlbs.dao.SiteAdminDao;
import dev.dinesh.emlbs.util.ProgramUtils;

/**
 * @author dinesh
 *
 */
public class Login extends ActionSupport implements SessionAware {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private Map session;
	public void setSession(Map session) { this.session = session; }
	public Login() {}
	@Override
	public String execute() { return SUCCESS; }
	@Override
	public void validate() {
		SiteAdminDao adminDao = new SiteAdminDao();
		if (getUserName().length() == 0) {
			addFieldError("userName", getText("website.form.username.required"));
		} else {
			if (adminDao.getAdminCount()==0) {
				if (!(getUserName().equals(getText("website.admin.defuname"))&&getPassword().equals(getText("website.admin.defpass")))) {
					addFieldError("userName", getText("website.form.username.wrong"));
				} else {
					ProgramUtils.addWarning(getText("website.warning.defaultpass"));
					System.out.println("warning added");
				} 
			} else {
				if (!adminDao.isValidAdmin(getUserName(), getPassword())) {
					addFieldError("userName", getText("website.form.username.wrong"));
				}
			}
		}
		if (getPassword().length() == 0) {
			addFieldError("password", getText("website.form.password.required"));
		}
		session.put("userName", getUserName());
		session.put("password", getPassword());
	}	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
