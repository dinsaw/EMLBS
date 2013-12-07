/**
 * 
 */
package dev.dinesh.emlbs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateFilter;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequenceComparator;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryComponentFilter;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.GeometryFilter;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

import dev.dinesh.emlbs.persistence.AdminOverlayType;
import dev.dinesh.emlbs.persistence.AdminStatedOverlay;
import dev.dinesh.emlbs.persistence.SiteAdmin;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

/**
 * @author dinesh
 * 
 */
public class AdminOverlayDao {
	public List<AdminStatedOverlay> getAllAdminOverlays() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<AdminStatedOverlay> list = Useful.castList(
				AdminStatedOverlay.class,
				session.createCriteria(AdminStatedOverlay.class).list());
		session.close();
		return list;
	}

	public void addAdminOverlay(String adminName, Double x, Double y,
			String overlaytypename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SiteAdmin siteAdmin = (SiteAdmin) session
				.createCriteria(SiteAdmin.class)
				.add(Restrictions.eq("name", adminName)).uniqueResult();
		AdminOverlayType adminOverlayType = (AdminOverlayType) session
				.createCriteria(AdminOverlayType.class)
				.add(Restrictions.eq("name", overlaytypename)).uniqueResult();
		Coordinate coordinate = new Coordinate(x, y);
		Point point=new Point(coordinate, new PrecisionModel(), 0);
/*		Point point = new Point((CoordinateSequence) new Coordinate(x, y),
				new GeometryFactory());*/
		AdminStatedOverlay overlay = new AdminStatedOverlay(siteAdmin, point);
		overlay.setAdminOverlayType(adminOverlayType);
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(overlay);
		tx.commit();
		session.close();
	}
}
