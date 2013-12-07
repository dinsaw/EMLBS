/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.vividsolutions.jts.geom.Point;

/**
 * @author dinesh
 *
 */
@Entity
@PrimaryKeyJoinColumn(name="overlayId")
public class UserStatedOverlay extends Overlay {
	private UserM user;
	private UserOverlayType userOverlayType;
	public UserStatedOverlay() {
	}
	public UserStatedOverlay(UserM userM,Point locPoint,UserOverlayType userOverlayType) {
		super(locPoint);
		this.user = userM;
		this.userOverlayType = userOverlayType;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	public UserM getUser() {
		return user;
	}

	public void setUser(UserM user) {
		this.user = user;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public UserOverlayType getUserOverlayType() {
		return userOverlayType;
	}
	public void setUserOverlayType(UserOverlayType userOverlayType) {
		this.userOverlayType = userOverlayType;
	}
	
}
