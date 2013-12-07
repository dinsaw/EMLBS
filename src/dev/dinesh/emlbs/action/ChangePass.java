/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.SiteAdminDao;
import dev.dinesh.emlbs.persistence.SiteAdmin;
import dev.dinesh.emlbs.util.ProgramUtils;

/**
 * @author dinesh
 *
 */
public class ChangePass extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currPass;
	private String newPass;
	private String newPassAgain;
	private Map session;
	private String sessionUN;
	private boolean firsttime=false;
	@Override
	public String execute() throws Exception {
		if (firsttime) {
			SiteAdminDao dao = new SiteAdminDao();
			dao.addSiteAdmin(sessionUN, getNewPass(), (short) 0);
			ProgramUtils.removeWarning(getText("website.warning.defaultpass"));
		} else {
			SiteAdminDao dao = new SiteAdminDao();
			dao.changePassword(sessionUN, getNewPass());
		}
		return SUCCESS;
	}
	@Override
	public void validate() {
		SiteAdminDao adminDao = new SiteAdminDao();
		sessionUN = (String) ActionContext.getContext().getSession().get("userName");
		if (adminDao.getAdminCount()==0) {
			firsttime=true;
			if (!(sessionUN.equals(getText("website.admin.defuname")) && getCurrPass().equals(getText("website.admin.defpass")))) {
				addFieldError("currPass", getText("website.form.currentpasswrong"));
			} else {
				if (newPass.length()<8) {
					addFieldError("newPass", getText("website.form.passminsizewarn"));
				} else {
					if (!getNewPass().equals(getNewPassAgain())) {
						addFieldError("newPassAgain", getText("website.form.passagainwrong"));
					}
				}
			}
		} else {
			SiteAdmin admin = adminDao.getSiteAdmin(sessionUN);
			if (!admin.getPass().equals(getCurrPass())) {
				addFieldError("currPass", getText("website.form.currentpasswrong"));
			} else {
				if (newPass.length()<8) {
					addFieldError("newPass", getText("website.form.passminsizewarn"));
				} else {
					if (!getNewPass().equals(getNewPassAgain())) {
						addFieldError("newPassAgain", getText("website.form.passagainwrong"));
					}
				}
			}
		}	
	}
	public String getCurrPass() {
		return currPass;
	}
	public void setCurrPass(String currPass) {
		this.currPass = currPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getNewPassAgain() {
		return newPassAgain;
	}
	public void setNewPassAgain(String newPassAgain) {
		this.newPassAgain = newPassAgain;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	

}
