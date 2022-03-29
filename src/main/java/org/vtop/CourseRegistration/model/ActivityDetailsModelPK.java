package org.vtop.CourseRegistration.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActivityDetailsModelPK implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Column(name="SEMSTR_DETAILS_SEMESTER_SUB_ID")
	private String semSubId;
	
	@Column(name="ACTIVITY_MASTER_ACTIVITY_ID")
	private Integer activityId;

	public String getSemSubId() {
		return semSubId;
	}

	public void setSemSubId(String semSubId) {
		this.semSubId = semSubId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Override
	public String toString() {
		return "ActivityDetailsModelPK [semSubId=" + semSubId + ", activityId=" + activityId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
		result = prime * result + ((semSubId == null) ? 0 : semSubId.hashCode());
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
		ActivityDetailsModelPK other = (ActivityDetailsModelPK) obj;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		if (semSubId == null) {
			if (other.semSubId != null)
				return false;
		} else if (!semSubId.equals(other.semSubId))
			return false;
		return true;
	}
	
	
	

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
		result = prime * result + ((semSubId == null) ? 0 : semSubId.hashCode());
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
		ActivityDetailsModelPK other = (ActivityDetailsModelPK) obj;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		if (semSubId == null) {
			if (other.semSubId != null)
				return false;
		} else if (!semSubId.equals(other.semSubId))
			return false;
		return true;
	}*/

	
	
}
