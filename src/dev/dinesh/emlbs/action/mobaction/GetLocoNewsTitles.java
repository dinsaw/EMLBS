/**
 * 
 */
package dev.dinesh.emlbs.action.mobaction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.LocoNewsDao;

/**
 * @author dinesh
 *
 */
public class GetLocoNewsTitles extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> newsTitleList = new ArrayList<String>();
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		double x,y;
		x = Double.parseDouble(request.getParameter("x"));
		y = Double.parseDouble(request.getParameter("y"));
		LocoNewsDao locoNewsDao = new LocoNewsDao();
		newsTitleList=locoNewsDao.getNewsTitleList(x, y);
		return SUCCESS;
	}
	public List<String> getNewsTitleList() {
		return newsTitleList;
	}

	public void setNewsTitleList(List<String> newsTitleList) {
		this.newsTitleList = newsTitleList;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
}
