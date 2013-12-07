/**
 * 
 */
package dev.dinesh.emlbs.action.mobaction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dev.dinesh.emlbs.dao.LocoNewsDao;

/**
 * @author dinesh
 *
 */
public class GetLocoNewsByTitle extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	public String news;
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		String newsTitle = request.getParameter("newsTitle");
		LocoNewsDao locoNewsDao = new LocoNewsDao();
		System.out.println("t:"+newsTitle);
		news = locoNewsDao.getLocoNewsByTitle(newsTitle);
		System.out.println("n:"+news);
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
}
