package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="ProgramSpecializationCurriculumDetail")
public class ProgramSpecializationCurriculumDetail 
{
	@Id
	private String id;
	
	private int	progSpecializationId;
	private int	admissionYear;
	private float curriculumVersion;
	private String courseCategory;
	private String catalogType;
	private String basketCategory;
	private int	basketCredit;
	private String courseId;
	private String courseCode;
	private String courseTitle;
	
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
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public String getCatalogType() {
		return catalogType;
	}
	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}
	public String getBasketCategory() {
		return basketCategory;
	}
	public void setBasketCategory(String basketCategory) {
		this.basketCategory = basketCategory;
	}
	public int getBasketCredit() {
		return basketCredit;
	}
	public void setBasketCredit(int basketCredit) {
		this.basketCredit = basketCredit;
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
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	@Override
	public String toString() {
		return "ProgramSpecializationCurriculumDetail [id=" + id + ", progSpecializationId=" + progSpecializationId
				+ ", admissionYear=" + admissionYear + ", curriculumVersion=" + curriculumVersion + ", courseCategory="
				+ courseCategory + ", catalogType=" + catalogType + ", basketCategory=" + basketCategory
				+ ", basketCredit=" + basketCredit + ", courseId=" + courseId + ", courseCode=" + courseCode
				+ ", courseTitle=" + courseTitle + "]";
	}
}
