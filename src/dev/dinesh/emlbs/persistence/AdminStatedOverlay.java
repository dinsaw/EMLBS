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
public class AdminStatedOverlay extends Overlay {
	
	private SiteAdmin siteAdmin;
	private AdminOverlayType adminOverlayType;
	public AdminStatedOverlay() {
	}
	public AdminStatedOverlay(SiteAdmin admin,Point locPoint) {
		super(locPoint);
		this.siteAdmin = admin;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public SiteAdmin getSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(SiteAdmin siteAdmin) {
		this.siteAdmin = siteAdmin;
	}
	
	@ManyToOne(cascade= CascadeType.ALL)
	public AdminOverlayType getAdminOverlayType() {
		return adminOverlayType;
	}
	public void setAdminOverlayType(AdminOverlayType adminOverlayType) {
		this.adminOverlayType = adminOverlayType;
	}
}
