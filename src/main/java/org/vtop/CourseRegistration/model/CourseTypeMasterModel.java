package org.vtop.CourseRegistration.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COURSE_TYPE_MASTER", schema="ACADEMICS")
public class CourseTypeMasterModel 
{
	@EmbeddedId
	private CourseTypeMasterModelPK ctmpk;
	
	@ManyToOne
	@JoinColumn(name="CRS_CTLG_GENERIC_COURSE_TYPE",insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@ManyToOne
	@JoinColumn(name="CRS_TYP_CMPNT_MSTR_COURSE_TYPE",insertable = false, updatable = false)
	private CourseTypeComponentModel courseComponentModel;

	public CourseTypeMasterModelPK getCtmpk() {
		return ctmpk;
	}

	public void setCtmpk(CourseTypeMasterModelPK ctmpk) {
		this.ctmpk = ctmpk;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public CourseTypeComponentModel getCourseComponentModel() {
		return courseComponentModel;
	}

	public void setCourseComponentModel(CourseTypeComponentModel courseComponentModel) {
		this.courseComponentModel = courseComponentModel;
	}

	@Override
	public String toString() {
		return "CourseTypeMasterModel [ctmpk=" + ctmpk + ", courseTypeComponentModel=" + courseTypeComponentModel
				+ ", courseComponentModel=" + courseComponentModel + "]";
	}

	
	
}
