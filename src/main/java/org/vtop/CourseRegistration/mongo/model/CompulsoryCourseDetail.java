package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="CompulsoryCourseDetail")
public class CompulsoryCourseDetail
{
	@Id
	private String id;
	
	private String semesterSubId;
	private int	progGroupId;
	private int	studentBatch;
	private String courseId;
	private int	offerType;
	private String programSpecialization;
	private int	preRequisiteType;
	private String preRequisiteParam;
	private int	priorityNo;
	private int	status;
	
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
	public int getProgGroupId() {
		return progGroupId;
	}
	public void setProgGroupId(int progGroupId) {
		this.progGroupId = progGroupId;
	}
	public int getStudentBatch() {
		return studentBatch;
	}
	public void setStudentBatch(int studentBatch) {
		this.studentBatch = studentBatch;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getOfferType() {
		return offerType;
	}
	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}
	public String getProgramSpecialization() {
		return programSpecialization;
	}
	public void setProgramSpecialization(String programSpecialization) {
		this.programSpecialization = programSpecialization;
	}
	public int getPreRequisiteType() {
		return preRequisiteType;
	}
	public void setPreRequisiteType(int preRequisiteType) {
		this.preRequisiteType = preRequisiteType;
	}
	public String getPreRequisiteParam() {
		return preRequisiteParam;
	}
	public void setPreRequisiteParam(String preRequisiteParam) {
		this.preRequisiteParam = preRequisiteParam;
	}
	public int getPriorityNo() {
		return priorityNo;
	}
	public void setPriorityNo(int priorityNo) {
		this.priorityNo = priorityNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CompulsoryCourseDetail [id=" + id + ", semesterSubId=" + semesterSubId + ", progGroupId=" + progGroupId
				+ ", studentBatch=" + studentBatch + ", courseId=" + courseId + ", offerType=" + offerType
				+ ", programSpecialization=" + programSpecialization + ", preRequisiteType=" + preRequisiteType
				+ ", preRequisiteParam=" + preRequisiteParam + ", priorityNo=" + priorityNo + ", status=" + status
				+ "]";
	}
}
