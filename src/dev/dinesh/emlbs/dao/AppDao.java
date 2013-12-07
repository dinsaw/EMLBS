/**
 * 
 */
package dev.dinesh.emlbs.dao;

import java.util.List;

import org.hibernate.Session;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.persistence.App;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

/**
 * @author dinesh
 *
 */
public class AppDao {
	public List<App> getAllApps() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<App> list = Useful.castList(App.class,session.createQuery("from App").list());
		session.close();
		return list;
	}
	public void createApp(String appname,Polygon areaPolygon) {
		App app = new App();
		app.setAppname(appname);
		app.setArea(areaPolygon);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(app);
		session.getTransaction().commit();
		session.close();
	}
	public boolean toggleAppStatus(String appname) {
		boolean status;
		Session session = HibernateUtil.getSessionFactory().openSession();
		App app = (App) session.createQuery("from App where appname = ?").setString(0, appname).uniqueResult();
		if (app.isActive()) {
			app.setActive(false);
			status=false;
		} else {
			app.setActive(true);
			status=true;
		}
		session.beginTransaction();
		session.update(app);
		session.getTransaction().commit();
		session.close();
		return status;
	}
	public void setAppStatus(String appname,boolean status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		App app = (App) session.createQuery("from App where appname = ?").setString(0, appname).uniqueResult();
		app.setActive(status);
		session.close();
	}
	
	public boolean isAppSetupStarted() {
		List<App> apps = getAllApps();
		if (apps.isEmpty()) {
			return false;
		}
		return true;
	}
	public void printApp() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		App app = (App) session.createQuery("from App").uniqueResult();
		System.out.println("App poly : "+app.getArea().toString());
		session.close();
	}
	public String createJSONPoly(Polygon polygon) {
		String polyString="[";
		Coordinate[] coordinates=polygon.getCoordinates();
		for (int i = 0; i < coordinates.length; i++) {
			if (i>0) {
				polyString=polyString+",";
			}
			Double x = coordinates[i].x;
			Double y = coordinates[i].y;
			String point = "["+x+","+y+"]";
			polyString=polyString+point;
		}
		polyString=polyString+"]";
		return polyString;
	}
}
