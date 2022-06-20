package org.vtop.CourseRegistration.model;

import java.util.Date;

public class HistoryCourseData
{
	private String  regno ;
	private String  courseCode;
	private String  courseType;
	private Float  credits ;
	private String  grade;
	private String  courseOption;
	private Date  examMonth;
	private String semester;
	
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public Float getCredits() {
		return credits;
	}
	public void setCredits(Float credits) {
		this.credits = credits;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCourseOption() {
		return courseOption;
	}
	public void setCourseOption(String courseOption) {
		this.courseOption = courseOption;
	}
	public Date getExamMonth() {
		return examMonth;
	}
	public void setExamMonth(Date examMonth) {
		this.examMonth = examMonth;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	@Override
	public String toString() {
		return "HistoryCourseData [regno=" + regno + ", courseCode=" + courseCode + ", courseType=" + courseType
				+ ", credits=" + credits + ", grade=" + grade + ", courseOption=" + courseOption + ", examMonth="
				+ examMonth + ", semester=" + semester + "]";
	}
}
