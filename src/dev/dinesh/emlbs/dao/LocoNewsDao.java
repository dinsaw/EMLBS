/**
 * 
 */
package dev.dinesh.emlbs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

import dev.dinesh.emlbs.persistence.LocoNews;
import dev.dinesh.emlbs.util.HibernateUtil;
import dev.dinesh.emlbs.util.Useful;

/**
 * @author dinesh
 * 
 */
public class LocoNewsDao {
	public void addLocoNews(String newsTitle, String news, Date endDate,
			Polygon area) {
		LocoNews locoNews = new LocoNews(newsTitle, news, endDate, area);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(locoNews);
		session.getTransaction().commit();
		session.close();
	}

	public String getLocoNewsByTitle(String newsTitle) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		LocoNews locoNews = (LocoNews) session.createCriteria(LocoNews.class)
				.add(Restrictions.eq("newstitle", newsTitle)).uniqueResult();
		session.close();
		return locoNews.getNews();
	}

	public List<String> getNewsTitleList(Point point) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> newsTitles = new ArrayList<String>();
		List<LocoNews> allLocoNewsList = Useful.castList(LocoNews.class,
				session.createCriteria(LocoNews.class).list());
		Date today = new Date();
		for (LocoNews locoNews : allLocoNewsList) {
			System.out.println(locoNews.getNewstitle() + "contains "+locoNews.getPolygon().contains(point)+" comp:"
					+ locoNews.getEndDate().compareTo(today));
			if (locoNews.getPolygon().contains(point)
					&& locoNews.getEndDate().compareTo(today) >= 0) {
				System.out.println(locoNews.getNewstitle() + "in IF");
				newsTitles.add(locoNews.getNewstitle());
			}
		}
		session.close();
		return newsTitles;
	}

	public List<String> getNewsTitleList(double x, double y) {
		Coordinate coordinate = new Coordinate(x, y);
		Point point = new Point(coordinate, new PrecisionModel(), 0);
		return getNewsTitleList(point);
	}
}
