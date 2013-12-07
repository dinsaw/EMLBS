/**
 * 
 */
package dev.dinesh.emlbs.util.jsonoverlay;

import java.util.List;

/**
 * @author dinesh
 *
 */
public class Group {
	private List<Item> items;

	public Group(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
