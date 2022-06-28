package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="StudentHistory")
public class StudentHistory 
{
	@Id
	private String id;
	
	private String registerNumber;
	private String courseId;
	private String courseCode;
	private String courseTitle;
	private String courseType;
	private String courseOptionCode;
	private String grade;
	private float Credit;
	private String resultDate;
	private String examMonth;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
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
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseOptionCode() {
		return courseOptionCode;
	}
	public void setCourseOptionCode(String courseOptionCode) {
		this.courseOptionCode = courseOptionCode;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public float getCredit() {
		return Credit;
	}
	public void setCredit(float credit) {
		Credit = credit;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getExamMonth() {
		return examMonth;
	}
	public void setExamMonth(String examMonth) {
		this.examMonth = examMonth;
	}
	
	@Override
	public String toString() {
		return "StudentHistory [id=" + id + ", registerNumber=" + registerNumber + ", courseId=" + courseId
				+ ", courseCode=" + courseCode + ", courseTitle=" + courseTitle + ", courseType=" + courseType
				+ ", courseOptionCode=" + courseOptionCode + ", grade=" + grade + ", Credit=" + Credit + ", resultDate="
				+ resultDate + ", examMonth=" + examMonth + "]";
	}
}
