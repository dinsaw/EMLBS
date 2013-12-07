/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import dev.dinesh.emlbs.dao.AppDao;
import dev.dinesh.emlbs.dao.LocoNewsDao;
import dev.dinesh.emlbs.persistence.App;
import dev.dinesh.emlbs.util.ProgramUtils;

/**
 * @author dinesh
 *
 */
public class AddLocoNews extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private String newsTitle,news;
	private String whowillsee;
	private Date endDate;
	private String area;
	private String path = "";
	private Date today = new Date();
	private Double centerlat,centerlang;
	private List<String> whowillseeList=new ArrayList<String>();
	private String defaultwhowillsee = "Everyone";
	@Override
	public void validate() {
		super.validate();
		System.out.println("validated");
		ini();
	}
	@Override
	public String execute() throws Exception {
		Polygon polygon;
		if (whowillsee.equals(defaultwhowillsee)) {
			polygon =new AppDao().getAllApps().get(0).getArea();
		} else {
			GeometryFactory geometryFactory = new GeometryFactory();
			Coordinate[] points = ProgramUtils.getPolyCords(getArea());
			LinearRing shell = geometryFactory.createLinearRing(points);
			polygon = new Polygon(shell, null, geometryFactory);
		}
		LocoNewsDao locoNewsDao = new LocoNewsDao();
		locoNewsDao.addLocoNews(newsTitle, news, endDate, polygon);
		addActionMessage("New LOCO news Saved : "+newsTitle);
		return INPUT;
	}
	public Double getCenterlat() {
		return centerlat;
	}
	public void setCenterlat(Double centerlat) {
		this.centerlat = centerlat;
	}
	public Double getCenterlang() {
		return centerlang;
	}
	public void setCenterlang(Double centerlang) {
		this.centerlang = centerlang;
	}
	public List<String> getWhowillseeList() {
		return whowillseeList;
	}
	public void setWhowillseeList(List<String> whowillseeList) {
		this.whowillseeList = whowillseeList;
	}
	public String getDefaultwhowillsee() {
		return defaultwhowillsee;
	}
	public void setDefaultwhowillsee(String defaultwhowillsee) {
		this.defaultwhowillsee = defaultwhowillsee;
	}
	private void ini() {
		System.out.println(endDate);
		AppDao appDao = new AppDao();
		List<App> apps=appDao.getAllApps();
		Polygon polygon = apps.get(0).getArea();
		Point center = polygon.getCentroid();
		centerlat = center.getX();
		centerlang = center.getY();
		path=appDao.createJSONPoly(polygon);
		System.out.println("path:"+path);
		whowillseeList.add("Everyone");
		whowillseeList.add("Selected Area");
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getWhowillsee() {
		return whowillsee;
	}
	public void setWhowillsee(String whowillsee) {
		this.whowillsee = whowillsee;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setSession(Map session) { this.session = session; }
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
}
