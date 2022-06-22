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

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="REGISTRATION_PERMIT", schema="ACADEMICS")
public class RegistrationModelPermit implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private RegistrationModelPermitPK id;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID", referencedColumnName="SEMESTER_SUB_ID", insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="STDNTSLGNDTLS_REGISTER_NUMBER", referencedColumnName="REG_NO", insertable = false, updatable = false)
	private StudentsLoginDetailsModel studentsLoginDetails;
	
	@ManyToOne
	@JoinColumn(name="CLSSGRP_MASTER_CLASS_GROUP_ID", referencedColumnName="CLASS_GROUP_ID", insertable = false, updatable = false)
	private ClassGroupModel classGroupModel;
	
	@Column(name = "PERMIT_DATE", nullable=false)	
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date permitDate;
	
	@Column(name="FROM_TIME")
	private String fromTime;
	
	@Column(name="TO_TIME")
	private String toTime;
	
	@Column(name="LOCK_STATUS")
	private Integer status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public RegistrationModelPermitPK getId() {
		return id;
	}

	public void setId(RegistrationModelPermitPK id) {
		this.id = id;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public StudentsLoginDetailsModel getStudentsLoginDetails() {
		return studentsLoginDetails;
	}

	public void setStudentsLoginDetails(StudentsLoginDetailsModel studentsLoginDetails) {
		this.studentsLoginDetails = studentsLoginDetails;
	}

	public ClassGroupModel getClassGroupModel() {
		return classGroupModel;
	}

	public void setClassGroupModel(ClassGroupModel classGroupModel) {
		this.classGroupModel = classGroupModel;
	}

	public Date getPermitDate() {
		return permitDate;
	}

	public void setPermitDate(Date permitDate) {
		this.permitDate = permitDate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		result = prime * result + ((classGroupModel == null) ? 0 : classGroupModel.hashCode());
		result = prime * result + ((fromTime == null) ? 0 : fromTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logIpaddress == null) ? 0 : logIpaddress.hashCode());
		result = prime * result + ((logTimestamp == null) ? 0 : logTimestamp.hashCode());
		result = prime * result + ((logUserId == null) ? 0 : logUserId.hashCode());
		result = prime * result + ((permitDate == null) ? 0 : permitDate.hashCode());
		result = prime * result + ((semesterDetailsModel == null) ? 0 : semesterDetailsModel.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((studentsLoginDetails == null) ? 0 : studentsLoginDetails.hashCode());
		result = prime * result + ((toTime == null) ? 0 : toTime.hashCode());
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
		RegistrationModelPermit other = (RegistrationModelPermit) obj;
		if (classGroupModel == null) {
			if (other.classGroupModel != null)
				return false;
		} else if (!classGroupModel.equals(other.classGroupModel))
			return false;
		if (fromTime == null) {
			if (other.fromTime != null)
				return false;
		} else if (!fromTime.equals(other.fromTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logIpaddress == null) {
			if (other.logIpaddress != null)
				return false;
		} else if (!logIpaddress.equals(other.logIpaddress))
			return false;
		if (logTimestamp == null) {
			if (other.logTimestamp != null)
				return false;
		} else if (!logTimestamp.equals(other.logTimestamp))
			return false;
		if (logUserId == null) {
			if (other.logUserId != null)
				return false;
		} else if (!logUserId.equals(other.logUserId))
			return false;
		if (permitDate == null) {
			if (other.permitDate != null)
				return false;
		} else if (!permitDate.equals(other.permitDate))
			return false;
		if (semesterDetailsModel == null) {
			if (other.semesterDetailsModel != null)
				return false;
		} else if (!semesterDetailsModel.equals(other.semesterDetailsModel))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (studentsLoginDetails == null) {
			if (other.studentsLoginDetails != null)
				return false;
		} else if (!studentsLoginDetails.equals(other.studentsLoginDetails))
			return false;
		if (toTime == null) {
			if (other.toTime != null)
				return false;
		} else if (!toTime.equals(other.toTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistrationModelPermit [id=" + id + ", semesterDetailsModel=" + semesterDetailsModel
				+ ", studentsLoginDetails=" + studentsLoginDetails + ", classGroupModel=" + classGroupModel
				+ ", permitDate=" + permitDate + ", fromTime=" + fromTime + ", toTime=" + toTime + ", status=" + status
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
}
