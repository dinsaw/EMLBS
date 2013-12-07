package dev.dinesh.emlbs.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RequestStatus {
	private Short statusId;
	private String statusName;
	private String statusDescription;
	private boolean defaultStatus;
	
	public RequestStatus() {
	}
	public RequestStatus(String statusName, String statusDescription) {
		this.statusName = statusName;
		this.statusDescription = statusDescription;
	}
	@Id
	@GeneratedValue
	public Short getStatusId() {
		return statusId;
	}
	public void setStatusId(Short statusId) {
		this.statusId = statusId;
	}
	
	@Column(nullable=false,unique=true)
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public boolean isDefaultStatus() {
		return defaultStatus;
	}
	public void setDefaultStatus(boolean defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
}
