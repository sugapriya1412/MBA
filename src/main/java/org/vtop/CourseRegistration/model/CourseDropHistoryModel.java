package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COURSE_DROP_HISTORY")
public class CourseDropHistoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private CourseDropHistoryPKModel courseDropHistoryPKId;
		
	@ManyToOne
	@JoinColumn(name="COURSE_ALLOCATION_CLASS_ID", insertable = false, updatable = false)
	private CourseAllocationModel courseAllocationModel;
		
	@Column(name="LOG_USERID")
	private String logUserId;
		
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public CourseDropHistoryPKModel getCourseDropHistoryPKId() {
		return courseDropHistoryPKId;
	}

	public void setCourseDropHistoryPKId(CourseDropHistoryPKModel courseDropHistoryPKId) {
		this.courseDropHistoryPKId = courseDropHistoryPKId;
	}

	public CourseAllocationModel getCourseAllocationModel() {
		return courseAllocationModel;
	}

	public void setCourseAllocationModel(CourseAllocationModel courseAllocationModel) {
		this.courseAllocationModel = courseAllocationModel;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogIpaddress() {
		return logIpaddress;
	}

	public void setLogIpaddress(String logIpaddress) {
		this.logIpaddress = logIpaddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseDropHistoryPKId == null) ? 0 : courseDropHistoryPKId.hashCode());
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
		CourseDropHistoryModel other = (CourseDropHistoryModel) obj;
		if (courseDropHistoryPKId == null) {
			if (other.courseDropHistoryPKId != null)
				return false;
		} else if (!courseDropHistoryPKId.equals(other.courseDropHistoryPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseDropHistoryModel [courseDropHistoryPKId=" + courseDropHistoryPKId + ", courseAllocationModel="
				+ courseAllocationModel + ", logUserId=" + logUserId + ", logIpaddress=" + logIpaddress + "]";
	}
	
}
