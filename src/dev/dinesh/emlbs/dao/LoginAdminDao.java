/**
 * 
 */
package dev.dinesh.emlbs.dao;

import dev.dinesh.emlbs.persistence.SiteAdmin;

/**
 * @author dinesh
 * 
 */
public class LoginAdminDao {
	public static Object validate(String username, String password) {
		SiteAdminDao adminDao = new SiteAdminDao();
		SiteAdmin admin = adminDao.getSiteAdmin(username);
		if (admin != null) {
			if (admin.getPass().equals(password)) {
				return admin;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
