package dev.dinesh.emlbs.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Polygon;

@Entity
public class LocoNews {
	private long id;
	private String newstitle;
	private String news;
	private Date endDate;
	private Polygon polygon;
	
	public LocoNews() {
	}
	
	public LocoNews(String newstitle, String news, Date endDate, Polygon polygon) {
		this.newstitle = newstitle;
		this.news = news;
		this.endDate = endDate;
		this.polygon = polygon;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Lob
	@Column(nullable=false)
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	@Column(nullable=false)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Type(type="org.hibernate.spatial.GeometryType")
	public Polygon getPolygon() {
		return polygon;
	}
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	@Column(nullable=false,unique=true)
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
}
