package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="SemesterDetail")
public class SemesterDetail 
{
	@Id
	private String id;
	
	private String semesterSubId;
	private int	semesterId;
	private String description;
	private String descriptionShort;
	private int	academicYear;
	private int	graduateYear;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSemesterSubId() {
		return semesterSubId;
	}
	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}
	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionShort() {
		return descriptionShort;
	}
	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}
	public int getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	public int getGraduateYear() {
		return graduateYear;
	}
	public void setGraduateYear(int graduateYear) {
		this.graduateYear = graduateYear;
	}
	
	@Override
	public String toString() {
		return "SemesterDetail [id=" + id + ", semesterSubId=" + semesterSubId + ", semesterId=" + semesterId
				+ ", description=" + description + ", descriptionShort=" + descriptionShort + ", academicYear="
				+ academicYear + ", graduateYear=" + graduateYear + "]";
	}
}
