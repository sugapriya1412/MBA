package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="STUDENT_HISTORY", schema="ACADEMICS")
public class StudentHistoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private StudentHistoryPKModel studentHistoryPKId;
			
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="COURSE_OPTION_MASTER_CODE")
	private String courseOptionCode;
			
	@Column(name="GRADE")
	private String grade;
	
	@Column(name="CREDIT")
	private float credit;
	
	@Column(name="RESULT_DATE")
	@Temporal(TemporalType.DATE)
	private Date resultDate;
	
	@Column(name="EXAM_MONTH")
	@Temporal(TemporalType.DATE)
	private Date examMonth;
	
	@Column(name="COURSE_CODE")
	private String courseCode;
	
	@Column(name="COURSE_TITLE")
	private String courseTitle;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	public StudentHistoryPKModel getStudentHistoryPKId() {
		return studentHistoryPKId;
	}

	public void setStudentHistoryPKId(StudentHistoryPKModel studentHistoryPKId) {
		this.studentHistoryPKId = studentHistoryPKId;
	}
	
	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
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
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
		
	public Date getExamMonth() {
		return examMonth;
	}

	public void setExamMonth(Date examMonth) {
		this.examMonth = examMonth;
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

	public Date getLogTimeStamp() {
		return logTimeStamp;
	}

	public void setLogTimeStamp(Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentHistoryPKId == null) ? 0 : studentHistoryPKId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentHistoryModel other = (StudentHistoryModel) obj;
		if (studentHistoryPKId == null) {
			if (other.studentHistoryPKId != null)
				return false;
		} else if (!studentHistoryPKId.equals(other.studentHistoryPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentHistoryModel [studentHistoryPKId=" + studentHistoryPKId + ", courseTypeComponentModel="
				+ courseTypeComponentModel + ", courseOptionCode=" + courseOptionCode + ", grade=" + grade + ", credit="
				+ credit + ", resultDate=" + resultDate + ", examMonth=" + examMonth + ", courseCode=" + courseCode
				+ ", courseTitle=" + courseTitle + ", logTimeStamp=" + logTimeStamp + "]";
	}	
}
