/**
 * 
 */
package dev.dinesh.emlbs.util;


import java.util.Vector;

import org.apache.struts2.json.JSONWriter;

import com.opensymphony.xwork2.ActionContext;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * @author dinesh
 *
 */
public class ProgramUtils {
	public static Vector<String> getWarnings() {
		Vector<String> warnings = new Vector<String>();
		if (ActionContext.getContext().getSession().containsKey("warnings")) {
			warnings = (Vector) ActionContext.getContext().getSession().get("warnings");
		} else {
			ActionContext.getContext().getSession().put("warnings", warnings);
		}
		return warnings;
	}
	public static void addWarning(String w) {
		Vector<String> warnings = getWarnings();
		if (!warnings.contains(w)) {
			warnings.addElement(w);
		}
	}
	public static void removeWarning(String w) {
		Vector<String> warnings = getWarnings();
		warnings.removeElement(w);
	}
	/**
	 * @param polypoints
	 * @return
	 */
	public static Coordinate[] getPolyCords(String polypoints){
		System.out.println(polypoints);
		StringBuffer polyStringBuffer = new StringBuffer(polypoints);
		int noofpoints = 0,flagat=0;
		while (flagat!=-1) {
			flagat=polyStringBuffer.indexOf("(", flagat+1);
			System.out.println("f"+flagat);
			noofpoints++;
		}
		System.out.println("after noofpoint"+noofpoints);
		Coordinate[] coordinates = new Coordinate[noofpoints+1];
		int end = 0;
		int i=0;
		
		System.out.println(polyStringBuffer.length()+":"+end);
		int nooffpoints=0;
		while (nooffpoints!=noofpoints) {
			int start = polyStringBuffer.indexOf("(", end);
			end = polyStringBuffer.indexOf(")",start);
			int middle = polyStringBuffer.indexOf(",",start);
			String x = polyStringBuffer.substring(start+1, middle);
			String y = polyStringBuffer.substring(middle+2, end); 
			System.out.println("x="+x+"y"+y);
			coordinates[i]=new Coordinate(Double.parseDouble(x), Double.parseDouble(y));
			i++;
			nooffpoints++;
		}
		coordinates[i]=coordinates[0];
		return coordinates;
	}
}
