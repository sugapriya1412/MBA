package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SEMESTER_CLASSGROUP_DETAIL", schema="ACADEMICS")
public class SemesterClassGroupDetail implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SemesterClassGroupDetailPk id;
	
	@ManyToOne
	@JoinColumn(name="SEMESTER_SUB_ID", referencedColumnName="SEMESTER_SUB_ID", insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="CLASS_GROUP_ID", referencedColumnName="CLASS_GROUP_ID", insertable = false, updatable = false)
	private ClassGroupModel classGroupModel;
	
	@Column(name="TIME_TABLE_PATTERN_ID")
	private Integer timeTablePatternId;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddress;

	public SemesterClassGroupDetailPk getId() {
		return id;
	}

	public void setId(SemesterClassGroupDetailPk id) {
		this.id = id;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public ClassGroupModel getClassGroupModel() {
		return classGroupModel;
	}

	public void setClassGroupModel(ClassGroupModel classGroupModel) {
		this.classGroupModel = classGroupModel;
	}

	public Integer getTimeTablePatternId() {
		return timeTablePatternId;
	}

	public void setTimeTablePatternId(Integer timeTablePatternId) {
		this.timeTablePatternId = timeTablePatternId;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public Date getLogTimeStamp() {
		return logTimeStamp;
	}

	public void setLogTimeStamp(Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SemesterClassGroupDetail other = (SemesterClassGroupDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SemesterClassGroupDetail [id=" + id + ", semesterDetailsModel=" + semesterDetailsModel
				+ ", classGroupModel=" + classGroupModel + ", timeTablePatternId=" + timeTablePatternId + ", logUserId="
				+ logUserId + ", logTimeStamp=" + logTimeStamp + ", logIpAddress=" + logIpAddress + "]";
	}
}
