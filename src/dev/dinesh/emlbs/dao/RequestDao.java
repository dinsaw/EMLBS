/**
 * 
 */
package dev.dinesh.emlbs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

import dev.dinesh.emlbs.persistence.IssuedRequest;
import dev.dinesh.emlbs.persistence.RequestHandeler;
import dev.dinesh.emlbs.persistence.RequestStatus;
import dev.dinesh.emlbs.persistence.UserM;
import dev.dinesh.emlbs.persistence.UserOverlayType;
import dev.dinesh.emlbs.persistence.UserStatedOverlay;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

/**
 * @author dinesh
 * 
 */
public class RequestDao {
	public List<IssuedRequest> getAllRequests() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<IssuedRequest> list = Useful.castList(IssuedRequest.class, session
				.createCriteria(IssuedRequest.class).list());
		session.close();
		return list;
	}

	public void changeRequestStatus(Long requestId, String status) {
		System.out.println(requestId+"|"+status);
		Session session = HibernateUtil.getSessionFactory().openSession();
		IssuedRequest request = (IssuedRequest) session.createCriteria(
				IssuedRequest.class).add(
				Restrictions.eq("requestId", requestId)).uniqueResult();
		RequestStatusDao requestStatusDao = new RequestStatusDao();
		RequestStatus requestStatus = requestStatusDao.getRequestStatus(status);
		request.setRequestStatus(requestStatus);
		Transaction tx =session.beginTransaction();
		session.update(request);
		tx.commit();
		session.close();
	}

	public byte[] getImageById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		IssuedRequest issuedRequest = (IssuedRequest) session
				.createCriteria(IssuedRequest.class)
				.add(Restrictions.eq("requestId", id)).uniqueResult();
		session.close();
		return issuedRequest.getImage();
	}
	public IssuedRequest getRequest(long requestId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		IssuedRequest issuedRequest = (IssuedRequest) session
				.createCriteria(IssuedRequest.class)
				.add(Restrictions.eq("requestId", requestId)).uniqueResult();
		session.close();
		return issuedRequest;
	}
	public List<IssuedRequest> getRequests(String reqHandler, String status) {
		System.out.println("hand:" + reqHandler + "status" + status);
		Session session = HibernateUtil.getSessionFactory().openSession();
		RequestHandeler handeler = (RequestHandeler) session
				.createCriteria(RequestHandeler.class)
				.add(Restrictions.eq("name", reqHandler)).uniqueResult();
		List<IssuedRequest> requests = Useful.castList(IssuedRequest.class,
				session.createCriteria(IssuedRequest.class).addOrder(Order.desc("requestId")).list());
		List<IssuedRequest> filteredRequests = new ArrayList<IssuedRequest>();
		if (status == null) {
			for (IssuedRequest issuedRequest : requests) {
				if (issuedRequest.getOverlay().getUserOverlayType()
						.equals(handeler.getUserOverlayType())
						&& issuedRequest.getRequestStatus() == null) {
					filteredRequests.add(issuedRequest);
				}
			}
		} else {
			for (IssuedRequest issuedRequest : requests) {
				if (issuedRequest.getRequestStatus() != null) {
					if (issuedRequest.getOverlay().getUserOverlayType()
							.equals(handeler.getUserOverlayType())
							&& issuedRequest.getRequestStatus().getStatusName()
									.equals(status)) {
						filteredRequests.add(issuedRequest);
					}
				}
			}
		}
		session.close();
		return filteredRequests;
	}

	public List<IssuedRequest> getRequests(String reqHandler) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		RequestHandeler handeler = (RequestHandeler) session
				.createCriteria(RequestHandeler.class)
				.add(Restrictions.eq("name", reqHandler)).uniqueResult();
		List<IssuedRequest> requests = Useful.castList(IssuedRequest.class,
				session.createCriteria(IssuedRequest.class).addOrder(Order.desc("requestId")).list());
		List<IssuedRequest> filteredRequests = new ArrayList<IssuedRequest>();
		for (IssuedRequest issuedRequest : requests) {
			if (issuedRequest.getOverlay().getUserOverlayType()
					.equals(handeler.getUserOverlayType())) {
				filteredRequests.add(issuedRequest);
			}
		}
		session.close();
		return filteredRequests;
	}

	public List<IssuedRequest> getAllRequests(String handlername) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		RequestHandeler handeler = (RequestHandeler) session
				.createCriteria(RequestHandeler.class)
				.add(Restrictions.eq("name", handlername)).uniqueResult();
		List<IssuedRequest> list = Useful.castList(
				IssuedRequest.class,
				session.createCriteria(IssuedRequest.class)
						.add(Restrictions.eq("handeler", handeler)).list());
		session.close();
		return list;
	}

	public void addRequest(String uname, String overlayType, byte[] image,
			Double x, Double y, String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserM userM = (UserM) session.createCriteria(UserM.class)
				.add(Restrictions.eq("username", uname)).uniqueResult();
		Coordinate coordinate = new Coordinate(x, y);
		Point point = new Point(coordinate, new PrecisionModel(), 0);
		UserOverlayType userOverlayType = (UserOverlayType) session
				.createCriteria(UserOverlayType.class)
				.add(Restrictions.eq("name", overlayType)).uniqueResult();
		UserStatedOverlay statedOverlay = new UserStatedOverlay(userM, point,
				userOverlayType);
		RequestStatus defaultStatus = (RequestStatus) session
				.createCriteria(RequestStatus.class)
				.add(Restrictions.eq("defaultStatus", true)).uniqueResult();
		/*
		 * RequestHandeler requestHandeler = (RequestHandeler) session
		 * .createCriteria(RequestHandeler.class)
		 * .add(Restrictions.eq("userOverlayType", userOverlayType))
		 * .uniqueResult();
		 */
		IssuedRequest issuedRequest = new IssuedRequest(image, defaultStatus,
				statedOverlay, description);
		Transaction transaction = session.beginTransaction();
		session.save(issuedRequest);
		transaction.commit();
		session.close();
	}
}
