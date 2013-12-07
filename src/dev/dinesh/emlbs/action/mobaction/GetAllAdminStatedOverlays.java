/**
 * 
 */
package dev.dinesh.emlbs.action.mobaction;

import java.util.ArrayList;
import java.util.List;

import sun.misc.FpUtils;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.AdminOverlayDao;
import dev.dinesh.emlbs.persistence.AdminStatedOverlay;
import dev.dinesh.emlbs.util.jsonoverlay.DisplayableMarker;

/**
 * @author dinesh
 * 
 */
public class GetAllAdminStatedOverlays extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DisplayableMarker> markers = new ArrayList<DisplayableMarker>();

	@Override
	public String execute() throws Exception {
		AdminOverlayDao adminOverlayDao = new AdminOverlayDao();
		List<AdminStatedOverlay> overlays = adminOverlayDao
				.getAllAdminOverlays();
		for (AdminStatedOverlay overlay : overlays) {
			DisplayableMarker marker = new DisplayableMarker(overlay
					.getAdminOverlayType().getName(), overlay.getLocPoint()
					.getX(), overlay.getLocPoint().getY(), "#"
					+ overlay.getAdminOverlayType().getColor());
			markers.add(marker);
		}
		return SUCCESS;
	}

	public List<DisplayableMarker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<DisplayableMarker> markers) {
		this.markers = markers;
	}

}
