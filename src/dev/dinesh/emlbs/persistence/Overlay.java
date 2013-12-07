/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

/**
 * @author dinesh
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Overlay {
	private Long overlayId;
	private Point locPoint;
	
	public Overlay() {
	}
	public Overlay(Point locPoint){
		this.locPoint = locPoint;
	}
	@Id
	@GeneratedValue
	public Long getOverlayId() {
		return overlayId;
	}
	public void setOverlayId(Long overlayId) {
		this.overlayId = overlayId;
	}
	
	@Column(nullable = false)
	@Type(type = "org.hibernate.spatial.GeometryType")
	public Point getLocPoint() {
		return locPoint;
	}
	public void setLocPoint(Point locPoint) {
		this.locPoint = locPoint;
	}
}
