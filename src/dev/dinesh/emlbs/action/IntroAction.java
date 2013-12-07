/**
 * 
 */
package dev.dinesh.emlbs.action;

import dev.dinesh.emlbs.dao.AppDao;

/**
 * @author dinesh
 *
 */
public class IntroAction {
	public String execute() throws Exception {
		Class.forName("dev.dinesh.emlbs.util.HibernateUtil");
		AppDao applicationDao = new AppDao();
		if (applicationDao.getAllApps().isEmpty()) {
			return "FAILURE";
		} else {
			return "SUCCESS";
		}
	}
}
