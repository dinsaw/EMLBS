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
public class AuthenticationInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("___________Auth Interceptor Ended_________");

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
		try {
			Class.forName("dev.dinesh.emlbs.util.HibernateUtil");
		} catch (ClassNotFoundException e) {
			System.out.println("hibernetutil not loaded");
			e.printStackTrace();
		}
		System.out.println("___________Auth Interceptor Started_________");
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation inv) throws Exception {
		System.out.println("___________Auth Interceptor EX1_________");
		
		if (inv.getInvocationContext().getParameters().get("LOGINATTEMPT")==null) {
			if (inv.getInvocationContext().getSession().get("userName")==null) {
				System.out.println("___________Auth Interceptor EX sessoin null_________");
				return "index";
			} else {
				System.out.println("___________Auth Interceptor EX sessoin present_________");
				return inv.invoke();
			}
		} else {
			System.out.println("___________Auth Interceptor LOGIN ATTEMPT_________");
			return inv.invoke();
		}
	}

}
