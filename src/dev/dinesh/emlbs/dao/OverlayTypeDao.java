package dev.dinesh.emlbs.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dev.dinesh.emlbs.persistence.AdminOverlayType;
import dev.dinesh.emlbs.persistence.OverlayType;
import dev.dinesh.emlbs.persistence.UserOverlayType;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

public class OverlayTypeDao {
	public static final String USEROVERLAY = "USERo";
	public static final String ADMINOVERLAY = "ADMINo";

	public List<AdminOverlayType> getAllAdminOverlayTypes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<AdminOverlayType> list = Useful.castList(AdminOverlayType.class,
				session.createCriteria(AdminOverlayType.class).list());
		session.close();
		return list;
	}
	public List<String> getAllUserOverlayTypeNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> names = Useful.castList(String.class, session.createCriteria(UserOverlayType.class).setProjection(Projections.property("name")).list());
		session.close();
		return names;
	}
	public void addOverlayType(String name, byte[] image,
			String overlayTypeConst, String color) {
		OverlayType overlayType = null;
		if (overlayTypeConst.equals(ADMINOVERLAY)) {
			overlayType = new AdminOverlayType(name, image, color);
		} else if (overlayTypeConst.equals(USEROVERLAY)) {
			overlayType = new UserOverlayType(name, image, color);
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(overlayType);
		tx.commit();
		session.close();
	}

	/**
	 * inside calls addOverlayType(String name,byte[] image,String
	 * overlayTypeConst) method
	 * 
	 * @param name
	 * @param imageFile
	 * @param overlayTypeConst
	 * @throws IOException
	 */
	public void addOverlayType(String name, File imageFile,
			String overlayTypeConst, String color) throws IOException {
		byte image[] = new byte[(int) imageFile.length()];
		FileInputStream inputStream = new FileInputStream(imageFile);
		inputStream.read(image);
		inputStream.close();
		addOverlayType(name, image, overlayTypeConst, color);
	}

	public boolean isPresent(String overlayName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		OverlayType overlayType = (OverlayType) session
				.createCriteria(OverlayType.class)
				.add(Restrictions.eq("name", overlayName)).uniqueResult();
		if (overlayType != null) {
			return true;
		}
		session.close();
		return false;
	}//overlayTypeId
	public UserOverlayType getUserOverlayType(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserOverlayType userOverlayType = (UserOverlayType) session.createCriteria(UserOverlayType.class).add(Restrictions.eq("overlayTypeId", id)).uniqueResult();
		return userOverlayType;
	}
}
