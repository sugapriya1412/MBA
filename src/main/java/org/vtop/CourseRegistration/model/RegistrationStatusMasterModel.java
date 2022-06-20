package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRATION_STATUS_MASTER", schema="ACADEMICS")
public class RegistrationStatusMasterModel implements Serializable{

	private static final long serialVersionUID = 1L;

@Id
@Column(name="STATUS_NUMBER")
private int statusNumber;

@Column(name="DESCRIPTION")
private String description;

@Column(name="FONT_COLOUR")
private String color;

@Column(name="APPROVED_STATUS")
private int status;

public int getStatusNumber() {
	return statusNumber;
}

public void setStatusNumber(int statusNumber) {
	this.statusNumber = statusNumber;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

@Override
public String toString() {
	return "RegistrationStatusMasterModel [statusNumber=" + statusNumber + ", description=" + description + ", color="
			+ color + ", status=" + status + "]";
}




}
