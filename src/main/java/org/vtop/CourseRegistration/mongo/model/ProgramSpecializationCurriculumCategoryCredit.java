package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="ProgramSpecializationCurriculumCategoryCredit")
public class ProgramSpecializationCurriculumCategoryCredit 
{
	@Id
	private String id;
	
	private int	progSpecializationId;
	private int	admissionYear;
	private float curriculumVersion;
	private String courseCategory;
	private String courseCategoryDescription;
	private int	courseCategoryOrderNo;
	private int	credit;
	private int	courseAllotStatus;
	private int	regCreditCalcStatus;
	
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
	public String getCourseCategoryDescription() {
		return courseCategoryDescription;
	}
	public void setCourseCategoryDescription(String courseCategoryDescription) {
		this.courseCategoryDescription = courseCategoryDescription;
	}
	public int getCourseCategoryOrderNo() {
		return courseCategoryOrderNo;
	}
	public void setCourseCategoryOrderNo(int courseCategoryOrderNo) {
		this.courseCategoryOrderNo = courseCategoryOrderNo;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCourseAllotStatus() {
		return courseAllotStatus;
	}
	public void setCourseAllotStatus(int courseAllotStatus) {
		this.courseAllotStatus = courseAllotStatus;
	}
	public int getRegCreditCalcStatus() {
		return regCreditCalcStatus;
	}
	public void setRegCreditCalcStatus(int regCreditCalcStatus) {
		this.regCreditCalcStatus = regCreditCalcStatus;
	}
	
	@Override
	public String toString() {
		return "ProgramSpecializationCurriculumCategoryCredit [id=" + id + ", progSpecializationId="
				+ progSpecializationId + ", admissionYear=" + admissionYear + ", curriculumVersion=" + curriculumVersion
				+ ", courseCategory=" + courseCategory + ", courseCategoryDescription=" + courseCategoryDescription
				+ ", courseCategoryOrderNo=" + courseCategoryOrderNo + ", credit=" + credit + ", courseAllotStatus="
				+ courseAllotStatus + ", regCreditCalcStatus=" + regCreditCalcStatus + "]";
	}
}
