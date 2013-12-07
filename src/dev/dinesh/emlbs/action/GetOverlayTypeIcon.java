/**
 * 
 */
package dev.dinesh.emlbs.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.action.customresults.CustomImageActionInterface;
import dev.dinesh.emlbs.dao.OverlayTypeDao;

/**
 * @author dinesh
 *
 */
public class GetOverlayTypeIcon extends ActionSupport implements ServletRequestAware,CustomImageActionInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imageId;
	private byte[] imageInByte=null;
	private HttpServletRequest servletRequest;
	@Override
	public String execute() throws Exception {
		System.out.println("icon imageid"+imageId);
		OverlayTypeDao overlayTypeDao = new OverlayTypeDao();
		System.out.println("icon imageid"+imageId);
		imageInByte = overlayTypeDao.getUserOverlayType(Long.parseLong(imageId)).getImage();
		return SUCCESS;
	}
	public String getCustomContentType() {
		return "image/png";
	}

	public byte[] getCustomImageInBytes() {
		System.out.println("img id:"+getImageId());
		BufferedImage orgImage;
		try {
			orgImage = ImageIO.read(getImageFile(this.imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(orgImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			System.out.println("kk");
		}
		return imageInByte;
	}

	private File getImageFile(String imageId) {
		String path = servletRequest.getSession().getServletContext().getRealPath("/");
		File file = new File(path+"/image/","overlayIcon"+imageId);
		System.out.println(file.toString());
		return file;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public byte[] getImageInByte() {
		return imageInByte;
	}

	public void setImageInByte(byte[] imageInByte) {
		this.imageInByte = imageInByte;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		servletRequest = request;
	}
}
