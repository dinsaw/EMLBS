/**
 * 
 */
package dev.dinesh.emlbs.action;

/**
 * @author dinesh
 *
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public class Logout extends ActionSupport {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
public String execute() throws Exception {
Map session = ActionContext.getContext().getSession();
session.clear();
return SUCCESS;
}
}
