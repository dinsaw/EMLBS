/**
 * 
 */
package dev.dinesh.emlbs.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dev.dinesh.emlbs.persistence.UserM;
import dev.dinesh.emlbs.util.HibernateUtil;

/**
 * @author dinesh
 * 
 */
public class UserDao {
	public void addUser(String uname, String pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		UserM u = new UserM();
		u.setUsername(uname);
		u.setPassword(pass);
		session.save(u);
		tx.commit();
		session.close();
	}
	public boolean isAuthenticate(String uname,String pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserM userM = (UserM) session.createCriteria(UserM.class).add(Restrictions.eq("username", uname)).uniqueResult();
		if (userM!=null) {
			if (userM.getPassword().equals(pass)) {
				return true;
			}
		} 
		session.close();
		return false;
	}
}
