package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="CourseEquivalanceCatalog")
public class CourseEquivalanceCatalog
{
	@Id
	private String id;
	
	private String courseId;
	private String courseCode;
	private String equivalentCourseId;
	private String equivalentCourseCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getEquivalentCourseId() {
		return equivalentCourseId;
	}
	public void setEquivalentCourseId(String equivalentCourseId) {
		this.equivalentCourseId = equivalentCourseId;
	}
	public String getEquivalentCourseCode() {
		return equivalentCourseCode;
	}
	public void setEquivalentCourseCode(String equivalentCourseCode) {
		this.equivalentCourseCode = equivalentCourseCode;
	}
	
	@Override
	public String toString() {
		return "CourseEquivalanceCatalog [id=" + id + ", courseId=" + courseId + ", courseCode=" + courseCode
				+ ", equivalentCourseId=" + equivalentCourseId + ", equivalentCourseCode=" + equivalentCourseCode + "]";
	}
}
