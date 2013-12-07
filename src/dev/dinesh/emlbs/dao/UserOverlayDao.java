package dev.dinesh.emlbs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

import dev.dinesh.emlbs.persistence.UserM;
import dev.dinesh.emlbs.persistence.UserOverlayType;
import dev.dinesh.emlbs.persistence.UserStatedOverlay;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

public class UserOverlayDao {
	public List<UserStatedOverlay> getAllUserOverlays() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<UserStatedOverlay> list = Useful.castList(UserStatedOverlay.class,
				session.createCriteria(UserStatedOverlay.class).list());
		session.close();
		return list;
	}

	public void addUserOverlay(String uname, Double x, Double y,
			String overlaytypename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserM userM = (UserM) session.createCriteria(UserM.class)
				.add(Restrictions.eq("username", uname)).uniqueResult();
		UserOverlayType userOverlayType = (UserOverlayType) session
				.createCriteria(UserOverlayType.class)
				.add(Restrictions.eq("name", overlaytypename)).uniqueResult();
		Coordinate coordinate = new Coordinate(x, y);
		Point point = new Point(coordinate, new PrecisionModel(), 0);
		Transaction tx = session.beginTransaction();
		UserStatedOverlay overlay = new UserStatedOverlay(userM, point,
				userOverlayType);
		overlay.setUserOverlayType(userOverlayType);
		session.saveOrUpdate(overlay);
		tx.commit();
		session.close();
	}

	public List<UserOverlayType> getAllUserOverlayTypes() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<UserOverlayType> userOverlayTypes = Useful.castList(
				UserOverlayType.class,
				session.createCriteria(UserOverlayType.class).list());
		return userOverlayTypes;
	}

	public List<String> getAllOverlayNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> ovrlayNames = Useful.castList(
				String.class,
				session.createCriteria(UserOverlayType.class)
						.setProjection(Projections.property("name")).list());
		return ovrlayNames;
	}
}
