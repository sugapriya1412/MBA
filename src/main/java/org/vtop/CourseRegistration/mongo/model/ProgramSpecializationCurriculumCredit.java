package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="ProgramSpecializationCurriculumCredit")	
public class ProgramSpecializationCurriculumCredit 
{
	@Id
	private String id;
	
	private int	progSpecializationId;
	private int	admissionYear;
	private float curriculumVersion;
	private int	totalCredits;
	private int	totalAllottedCredit;
	private String courseSystem;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProgSpecializationId() {
		return progSpecializationId;
	}
	public void setProgSpecializationId(int progSpecializationId) {
		this.progSpecializationId = progSpecializationId;
	}
	public int getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}
	public float getCurriculumVersion() {
		return curriculumVersion;
	}
	public void setCurriculumVersion(float curriculumVersion) {
		this.curriculumVersion = curriculumVersion;
	}
	public int getTotalCredits() {
		return totalCredits;
	}
	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}
	public int getTotalAllottedCredit() {
		return totalAllottedCredit;
	}
	public void setTotalAllottedCredit(int totalAllottedCredit) {
		this.totalAllottedCredit = totalAllottedCredit;
	}
	public String getCourseSystem() {
		return courseSystem;
	}
	public void setCourseSystem(String courseSystem) {
		this.courseSystem = courseSystem;
	}
	
	@Override
	public String toString() {
		return "ProgramSpecializationCurriculumCredit [id=" + id + ", progSpecializationId=" + progSpecializationId
				+ ", admissionYear=" + admissionYear + ", curriculumVersion=" + curriculumVersion + ", totalCredits="
				+ totalCredits + ", totalAllottedCredit=" + totalAllottedCredit + ", courseSystem=" + courseSystem
				+ "]";
	}
}
