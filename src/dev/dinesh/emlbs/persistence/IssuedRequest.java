/**
 * 
 */
package dev.dinesh.emlbs.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author dinesh
 * 
 */
@Entity
public class IssuedRequest {
	private Long requestId;
	private byte[] image;
	private UserStatedOverlay overlay;
	private RequestStatus requestStatus;
	private String description;

	public IssuedRequest() {
	}

	public IssuedRequest(byte[] image, RequestStatus requestStatus,
			UserStatedOverlay overlay, String description) {
		this.image = image;
		this.requestStatus = requestStatus;
		this.overlay = overlay;
		this.description = description;
	}

	@Id
	@GeneratedValue
	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	@Column(nullable = false)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public UserStatedOverlay getOverlay() {
		return overlay;
	}

	public void setOverlay(UserStatedOverlay overlay) {
		this.overlay = overlay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
