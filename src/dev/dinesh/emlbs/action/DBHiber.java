/**
 * 
 */
package dev.dinesh.emlbs.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dinesh
 *
 */
public class DBHiber extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {
		String classname="dev.dinesh.emlbs.util.HibernateUtil";
		Class.forName(classname);
		return SUCCESS;
	}
}
