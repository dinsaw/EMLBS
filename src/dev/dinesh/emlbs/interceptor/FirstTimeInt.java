/**
 * 
 */
package dev.dinesh.emlbs.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author dinesh
 *
 */
public class FirstTimeInt implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("_____________first time destroy___________________");

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
		System.out.println("_____________first time start ___________________");
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("_____________first time ___________________");
		if (invocation.getInvocationContext().getSession().get("firsttimeadmin").toString().equals("true")) {
			return "changepass";
		}
		return invocation.invoke();
	}

}
