/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Polygon;

/**
 * @author dinesh
 *
 */
@Entity
public class App {
	private Long id;
	private String appname;
	private boolean active=false;
	private Polygon area;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the appname
	 */
	@Column(unique = true, nullable = false)
	public String getAppname() {
		return appname;
	}
	/**
	 * @param appname the appname to set
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}
	/**
	 * @return the active
	 */
	@Column(nullable = false)
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	@Type(type="org.hibernate.spatial.GeometryType")
	public Polygon getArea() {
		return area;
	}
	public void setArea(Polygon area) {
		this.area = area;
	}
}
