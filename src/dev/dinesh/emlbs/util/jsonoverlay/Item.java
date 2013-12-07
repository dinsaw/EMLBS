/**
 * 
 */
package dev.dinesh.emlbs.util.jsonoverlay;

/**
 * @author dinesh
 *
 */
public class Item {
	public Item(Location location, String icon, String name) {
		this.location = location;
		this.icon = icon;
		this.name = name;
	}
	private Location location;
	private String icon;
	private String name;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
