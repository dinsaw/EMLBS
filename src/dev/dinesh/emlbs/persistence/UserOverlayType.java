/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import java.awt.Color;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author dinesh
 * 
 */
@Entity
@DiscriminatorValue(value = "UOT")
public class UserOverlayType extends OverlayType {
	public UserOverlayType() {
	}

	public UserOverlayType(String name, byte[] image, String color) {
		super(name, image, color);
	}

}
