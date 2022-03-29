package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COURSE_EQUIVALANCE_REG", schema="ACADEMICS")
public class CourseEquivalanceRegModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private CourseEquivalanceRegPKModel courseEquivalanceRegPKId;
	
	@Column(name="EQUIVALANCE_COURSE_ID")
	private String equivalanceCourseId;
	
	@Column(name="LOCK_STATUS")
	private Integer lockStatus;
		
	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public CourseEquivalanceRegPKModel getCourseEquivalanceRegPKId() {
		return courseEquivalanceRegPKId;
	}

	public void setCourseEquivalanceRegPKId(CourseEquivalanceRegPKModel courseEquivalanceRegPKId) {
		this.courseEquivalanceRegPKId = courseEquivalanceRegPKId;
	}

	public String getEquivalanceCourseId() {
		return equivalanceCourseId;
	}

	public void setEquivalanceCourseId(String equivalanceCourseId) {
		this.equivalanceCourseId = equivalanceCourseId;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
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
		result = prime * result + ((courseEquivalanceRegPKId == null) ? 0 : courseEquivalanceRegPKId.hashCode());
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
		CourseEquivalanceRegModel other = (CourseEquivalanceRegModel) obj;
		if (courseEquivalanceRegPKId == null) {
			if (other.courseEquivalanceRegPKId != null)
				return false;
		} else if (!courseEquivalanceRegPKId.equals(other.courseEquivalanceRegPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseEquivalanceRegModel [courseEquivalanceRegPKId=" + courseEquivalanceRegPKId
				+ ", equivalanceCourseId=" + equivalanceCourseId + ", logUserId=" + logUserId + ", logTimestamp="
				+ logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
	
}
