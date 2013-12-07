/**
 * 
 */
package dev.dinesh.emlbs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dev.dinesh.emlbs.persistence.RequestHandeler;
import dev.dinesh.emlbs.persistence.UserOverlayType;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

/**
 * @author dinesh
 * 
 */
public class RequestHandlerDao {
	public List<RequestHandeler> getAllRequestsHandlers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<RequestHandeler> list = Useful.castList(RequestHandeler.class,
				session.createCriteria(RequestHandeler.class).list());
		session.close();
		return list;
	}

	public List<String> getAllRequestHandlerNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> names = Useful.castList(
				String.class,
				session.createCriteria(RequestHandeler.class)
						.setProjection(Projections.property("name")).list());
		session.close();
		return names;
	}

	public void addHandeler(String name, String description,
			UserOverlayType overlayType) {
		System.out.println("ot:"+overlayType.getName());
		RequestHandeler handeler = new RequestHandeler(name, description,
				overlayType);
		//System.out.println( "handler"+handeler.getUserOverlayType().getName());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(handeler);
		tx.commit();
		session.close();
	}

	public boolean isHandlerPresent(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		RequestHandeler handler = (RequestHandeler) session
				.createCriteria(RequestHandeler.class)
				.add(Restrictions.eq("name", name)).uniqueResult();
		if (handler != null) {
			return true;
		}
		session.close();
		return false;
	}

	public void addHandeler(String handlerName, String handlerDescription,
			String userOTypeString) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserOverlayType overlayType = (UserOverlayType) session
				.createCriteria(UserOverlayType.class)
				.add(Restrictions.eq("name", userOTypeString)).uniqueResult();
		System.out.println(overlayType.getName());
		addHandeler(handlerName, handlerDescription, overlayType);
		session.close();
	}
}
