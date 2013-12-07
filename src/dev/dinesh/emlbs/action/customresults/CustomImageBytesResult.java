/**
 * 
 */
package dev.dinesh.emlbs.action.customresults;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

import dev.dinesh.emlbs.action.RequestImageAction;

/**
 * @author dinesh
 *
 */
public class CustomImageBytesResult implements Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(ActionInvocation ai) throws Exception {
		CustomImageActionInterface action = (CustomImageActionInterface) ai.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType(action.getCustomContentType());
		response.getOutputStream().write(action.getCustomImageInBytes());
		response.getOutputStream().flush();
	}

}
