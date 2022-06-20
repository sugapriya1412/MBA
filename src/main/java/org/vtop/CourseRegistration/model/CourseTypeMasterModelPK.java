package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;

public class CourseTypeMasterModelPK implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="CRS_CTLG_GENERIC_COURSE_TYPE")
	private String ccGenericType;
	
	@Column(name="CRS_TYP_CMPNT_MSTR_COURSE_TYPE")
	private String cCCourseType;

	public String getCcGenericType() {
		return ccGenericType;
	}

	public void setCcGenericType(String ccGenericType) {
		this.ccGenericType = ccGenericType;
	}

	public String getcCCourseType() {
		return cCCourseType;
	}

	public void setcCCourseType(String cCCourseType) {
		this.cCCourseType = cCCourseType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cCCourseType == null) ? 0 : cCCourseType.hashCode());
		result = prime * result + ((ccGenericType == null) ? 0 : ccGenericType.hashCode());
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
		CourseTypeMasterModelPK other = (CourseTypeMasterModelPK) obj;
		if (cCCourseType == null) {
			if (other.cCCourseType != null)
				return false;
		} else if (!cCCourseType.equals(other.cCCourseType))
			return false;
		if (ccGenericType == null) {
			if (other.ccGenericType != null)
				return false;
		} else if (!ccGenericType.equals(other.ccGenericType))
			return false;
		return true;
	}
	
	
}
