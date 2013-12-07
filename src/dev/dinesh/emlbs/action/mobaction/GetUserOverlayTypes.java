/**
 * 
 */
package dev.dinesh.emlbs.action.mobaction;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.UserOverlayDao;

/**
 * @author dinesh
 *
 */
public class GetUserOverlayTypes extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> userOverlayTypes=new ArrayList<String>();
	@Override
	public String execute() throws Exception {
		UserOverlayDao userOverlayDao = new UserOverlayDao();
		userOverlayTypes = userOverlayDao.getAllOverlayNames();
		return SUCCESS;
	}
	public List<String> getUserOverlayTypes() {
		return userOverlayTypes;
	}
	public void setUserOverlayTypes(List<String> userOverlayTypes) {
		this.userOverlayTypes = userOverlayTypes;
	}

}
