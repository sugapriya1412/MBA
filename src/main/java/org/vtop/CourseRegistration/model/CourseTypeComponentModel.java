package org.vtop.CourseRegistration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURSE_TYPE_COMPONENT_MASTER", schema="ACADEMICS")
public class CourseTypeComponentModel {
	
	@Id
	@Column(name="COURSE_TYPE")
	private String courseType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="COMPONENT")
	private int component;

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}

	@Override
	public String toString() {
		return "CourseTypeComponentModel [courseType=" + courseType + ", description=" + description + ", component="
				+ component + "]";
	}
	
}
