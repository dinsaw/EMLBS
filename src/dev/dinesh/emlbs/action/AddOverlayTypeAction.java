/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.io.File;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.OverlayTypeDao;

/**
 * @author dinesh
 *
 */
public class AddOverlayTypeAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map session;
	private String overlayName;
	private String overlayDescription;
	private String whoState;
	private File overlayImage;
	private String overlayImageContentType;
	private String overlayImageFileName;
	private String colorString;
	private OverlayTypeDao typeDao = new OverlayTypeDao();
	@Override
	public void validate() {
		super.validate();
		if (typeDao.isPresent(overlayName)) {
			addFieldError(overlayName, "Already one OverlayType with this name exist.");
		}
	}
	@Override
	public String execute() throws Exception {
		System.out.println(overlayImageContentType+overlayImageFileName);
		if (whoState.equals("Admin")) {
			typeDao.addOverlayType(overlayName, overlayImage, OverlayTypeDao.ADMINOVERLAY,colorString);
			addActionMessage("New Admin OverlayType Added : "+overlayName);
		} else if (whoState.equals("User")) {
			typeDao.addOverlayType(overlayName, overlayImage, OverlayTypeDao.USEROVERLAY,colorString);
			addActionMessage("New User OverlayType Added : "+overlayName);
		} 
		return SUCCESS;
	}
	public String getOverlayName() {
		return overlayName;
	}
	public void setOverlayName(String overlayName) {
		this.overlayName = overlayName;
	}
	public String getOverlayDescription() {
		return overlayDescription;
	}
	public void setOverlayDescription(String overlayDescription) {
		this.overlayDescription = overlayDescription;
	}
	public String getWhoState() {
		return whoState;
	}
	public void setWhoState(String whoState) {
		this.whoState = whoState;
	}
	public File getOverlayImage() {
		return overlayImage;
	}
	public void setOverlayImage(File overlayImage) {
		this.overlayImage = overlayImage;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = session;
	}
	public String getOverlayImageFileName() {
		return overlayImageFileName;
	}
	public void setOverlayImageFileName(String overlayImageFileName) {
		this.overlayImageFileName = overlayImageFileName;
	}
	public String getOverlayImageContentType() {
		return overlayImageContentType;
	}
	public void setOverlayImageContentType(String overlayImageContentType) {
		this.overlayImageContentType = overlayImageContentType;
	}
	public String getColorString() {
		return colorString;
	}
	public void setColorString(String colorString) {
		this.colorString = colorString;
	}
}
