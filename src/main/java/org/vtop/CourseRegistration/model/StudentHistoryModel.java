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
		
	/*@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;*/
		
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="COURSE_OPTION_MASTER_CODE")
	private String courseOptionCode;
	
	/*@ManyToOne
	@JoinColumn(name="COURSE_OPTION_MASTER_CODE", insertable = false, updatable = false)
	private CourseOptionModel courseOptionModel;*/
		
	@Column(name="GRADE")
	private String grade;
	
	@Column(name="CREDIT")
	private float credit;
	
	@Column(name="RESULT_DATE")
	@Temporal(TemporalType.DATE)
	private Date resultDate;
	
	@Column(name="COURSE_CODE")
	private String courseCode;
	
	public StudentHistoryPKModel getStudentHistoryPKId() {
		return studentHistoryPKId;
	}

	public void setStudentHistoryPKId(StudentHistoryPKModel studentHistoryPKId) {
		this.studentHistoryPKId = studentHistoryPKId;
	}

	/*public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
	}*/

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

	/*public CourseOptionModel getCourseOptionModel() {
		return courseOptionModel;
	}

	public void setCourseOptionModel(CourseOptionModel courseOptionModel) {
		this.courseOptionModel = courseOptionModel;
	}*/

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
		
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
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
				+ credit + ", resultDate=" + resultDate + ", courseCode=" + courseCode + "]";
	}		
}
