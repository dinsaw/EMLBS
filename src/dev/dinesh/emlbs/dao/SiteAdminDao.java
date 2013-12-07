/**
 * 
 */
package dev.dinesh.emlbs.dao;

import org.hibernate.Session;

import dev.dinesh.emlbs.persistence.SiteAdmin;
import dev.dinesh.emlbs.util.HibernateUtil;

/**
 * @author dinesh
 * 
 */
public class SiteAdminDao {
	public void addSiteAdmin(String name, String pass, Short level) {
		SiteAdmin admin = new SiteAdmin();
		admin.setName(name);
		admin.setPass(pass);
		admin.setLevel(level);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(admin);
		session.getTransaction().commit();
		session.close();
	}

	public SiteAdmin getSiteAdmin(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SiteAdmin admin = (SiteAdmin) session
				.createQuery("from SiteAdmin as sa where name = ?")
				.setString(0, username).uniqueResult();
		session.close();
		return admin;
	}

	public Long getAdminCount() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long c = (Long) session.createQuery("select count(*) from SiteAdmin")
				.uniqueResult();
		session.close();
		return c;
	}

	public void changePassword(String username, String pass) {
		SiteAdmin admin = getSiteAdmin(username);
		admin.setPass(pass);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(admin);
		session.getTransaction().commit();
		session.close();
	}

	public boolean isValidAdmin(String un, String pass) {
		SiteAdmin admin = getSiteAdmin(un);
		if (admin == null) {
			return false;
		} else if (!admin.getPass().equals(pass)) {
			return false;
		} else {
			return true;
		}
	}
}
