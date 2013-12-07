package dev.dinesh.emlbs.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RequestHandeler {
	private Short id;
	private String name;
	private String description;
	private UserOverlayType userOverlayType;
	public RequestHandeler(){
	}
	public RequestHandeler(String name, String description, UserOverlayType userOverlayType) {
		this.name = name;
		this.description = description;
		this.setUserOverlayType(userOverlayType);
	}
	
	@Id
	@GeneratedValue
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	@Column(unique=true,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public UserOverlayType getUserOverlayType() {
		return userOverlayType;
	}
	public void setUserOverlayType(UserOverlayType userOverlayType) {
		this.userOverlayType = userOverlayType;
	}	
	
}
