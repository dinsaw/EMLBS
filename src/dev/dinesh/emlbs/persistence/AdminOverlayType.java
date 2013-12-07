/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author dinesh
 * 
 */
@Entity
@DiscriminatorValue(value = "AOT")
public class AdminOverlayType extends OverlayType {

	public AdminOverlayType() {
	} 
	public AdminOverlayType(String name, byte[] image, String color) {
		super(name, image, color);
	}

}
