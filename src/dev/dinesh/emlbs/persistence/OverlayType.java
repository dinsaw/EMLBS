/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author dinesh
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "OT")
public class OverlayType {
	private Long overlayTypeId;
	private String name;
	private byte[] image;
	private String color;

	public OverlayType() {
	}

	public OverlayType(String name, byte[] image,String color) {
		this.name = name;
		this.image = image;
		this.color = color;
	}

	@Id
	@GeneratedValue
	public Long getOverlayTypeId() {
		return overlayTypeId;
	}

	public void setOverlayTypeId(Long overlayTypeId) {
		this.overlayTypeId = overlayTypeId;
	}

	@Column(unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
