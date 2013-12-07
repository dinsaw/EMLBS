package dev.dinesh.emlbs.action.customresults;

import com.opensymphony.xwork2.Action;

public interface CustomImageActionInterface extends Action{
	public String getCustomContentType();
	public byte[] getCustomImageInBytes();
}
