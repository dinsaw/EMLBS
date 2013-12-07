/**
 * 
 */
package dev.dinesh.emlbs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dev.dinesh.emlbs.persistence.RequestStatus;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

/**
 * @author dinesh
 * 
 */
public class RequestStatusDao {
	public static final String STATUSALL = "All";
	public static final String STATUSISSUE = "Issued";

	public List<String> getAllStatusNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> names = Useful.castList(
				String.class,
				session.createCriteria(RequestStatus.class)
						.addOrder(Order.asc("statusId")).setProjection(Projections.property("statusName"))
						.list());
		names.add(0, STATUSISSUE);
		session.close();
		return names;
	}

	public List<Integer> getAllNumberOfRequestPerStatus() {
		return null;
	}

	public RequestStatus getRequestStatus(String statusname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		RequestStatus requestStatus = (RequestStatus) session.createCriteria(
				RequestStatus.class).add(Restrictions.eq("statusName", statusname)).uniqueResult();
		session.close();
		return requestStatus;
	}
	public void addRequestStatus(String name,String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		RequestStatus requestStatus = new RequestStatus(name, description);
		Transaction transaction = session.beginTransaction();
		session.save(requestStatus);
		transaction.commit();
		session.close();
	}
}
