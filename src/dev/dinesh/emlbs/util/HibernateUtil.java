/**
 * 
 */
package dev.dinesh.emlbs.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import dev.dinesh.emlbs.persistence.AdminOverlayType;
import dev.dinesh.emlbs.persistence.AdminStatedOverlay;
import dev.dinesh.emlbs.persistence.App;
import dev.dinesh.emlbs.persistence.IssuedRequest;
import dev.dinesh.emlbs.persistence.LocoNews;
import dev.dinesh.emlbs.persistence.Overlay;
import dev.dinesh.emlbs.persistence.OverlayType;
import dev.dinesh.emlbs.persistence.RequestHandeler;
import dev.dinesh.emlbs.persistence.RequestStatus;
import dev.dinesh.emlbs.persistence.SiteAdmin;
import dev.dinesh.emlbs.persistence.UserM;
import dev.dinesh.emlbs.persistence.UserOverlayType;
import dev.dinesh.emlbs.persistence.UserStatedOverlay;

/**
 * @author dinesh
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
	try {
		Configuration config = new Configuration();
		config.addAnnotatedClass(AdminOverlayType.class);
		config.addAnnotatedClass(AdminStatedOverlay.class);
		config.addAnnotatedClass(App.class);
		config.addAnnotatedClass(IssuedRequest.class);
		config.addAnnotatedClass(LocoNews.class);
		config.addAnnotatedClass(Overlay.class);
		config.addAnnotatedClass(OverlayType.class);
		config.addAnnotatedClass(RequestHandeler.class);
		config.addAnnotatedClass(RequestStatus.class);
		config.addAnnotatedClass(SiteAdmin.class);
		config.addAnnotatedClass(UserM.class);
		config.addAnnotatedClass(UserStatedOverlay.class);
		config.addAnnotatedClass(UserOverlayType.class);
		config.configure("hibernate.cfg.xml");
		// First unit of work
		config.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//ProgramUtils.getPolyCords("(154, -1010),(-11111, 1111)");
	//Configuration cfg = new Configuration().configure();
	/*SchemaExport schemaExport = new SchemaExport(config);
	schemaExport.create(true, true);*/
		//AppDao appDao = new AppDao();
		//appDao.printApp();
	} catch (Throwable ex) {
	throw new ExceptionInInitializerError(ex);
	}
	}
	public static SessionFactory getSessionFactory() {
	// Alternatively, you could look up in JNDI here
	return sessionFactory;
	}
	public static void shutdown() {
	// Close caches and connection pools
	getSessionFactory().close();
	}

}
