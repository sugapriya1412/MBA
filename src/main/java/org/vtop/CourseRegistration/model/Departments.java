package org.vtop.CourseRegistration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "DEPARTMENTS", schema = "hrms")
public class Departments {
	
	@Id
	@Column (name = "DEPARTMENT_ID")
	private Long departmentId;
	
		
	@Column (name = "DESCRIPTION")
	private String description;


	public Long getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Departments [departmentId=" + departmentId + ", description=" + description + "]";
	}
	
	

}
