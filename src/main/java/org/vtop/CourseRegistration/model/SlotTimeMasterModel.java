package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="SLOT_TIME_MASTER", schema="ACADEMICS")
public class SlotTimeMasterModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private SlotTimeMasterPK stmPkId;
	
	@ManyToOne
	@JoinColumn(name="TMTBLPATTERN_MASTER_PATTERN_ID", insertable = false, updatable = false)
	private TimeTablePatternModel timeTablePatternModel;
	
	@Column(name="SEM_ID")
	private Integer semId;
	
	@MapsId("semId")
	@ManyToOne
	@JoinColumn(name="SEM_ID", insertable = false, updatable = false)
	private SemesterMasterModel semesterMasterModel;

	@Column(name="TT_SESSION")
	private String session;
	
	@Column(name="SLOT_TYPE")
	private String SlotType;
	
	@Column(name="NUMBER_OF_UNITS")
	private Integer numOfUnits;
	
	@Column(name="LOCK_STATUS")
	private Integer lockStatus;

	public SlotTimeMasterPK getStmPkId() {
		return stmPkId;
	}

	public void setStmPkId(SlotTimeMasterPK stmPkId) {
		this.stmPkId = stmPkId;
	}

	public TimeTablePatternModel getTimeTablePatternModel() {
		return timeTablePatternModel;
	}

	public void setTimeTablePatternModel(TimeTablePatternModel timeTablePatternModel) {
		this.timeTablePatternModel = timeTablePatternModel;
	}

	public Integer getSemId() {
		return semId;
	}

	public void setSemId(Integer semId) {
		this.semId = semId;
	}

	public SemesterMasterModel getSemesterMasterModel() {
		return semesterMasterModel;
	}

	public void setSemesterMasterModel(SemesterMasterModel semesterMasterModel) {
		this.semesterMasterModel = semesterMasterModel;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getSlotType() {
		return SlotType;
	}

	public void setSlotType(String slotType) {
		SlotType = slotType;
	}

	public Integer getNumOfUnits() {
		return numOfUnits;
	}

	public void setNumOfUnits(Integer numOfUnits) {
		this.numOfUnits = numOfUnits;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SlotType == null) ? 0 : SlotType.hashCode());
		result = prime * result + ((lockStatus == null) ? 0 : lockStatus.hashCode());
		result = prime * result + ((numOfUnits == null) ? 0 : numOfUnits.hashCode());
		result = prime * result + ((semId == null) ? 0 : semId.hashCode());
		result = prime * result + ((semesterMasterModel == null) ? 0 : semesterMasterModel.hashCode());
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		result = prime * result + ((stmPkId == null) ? 0 : stmPkId.hashCode());
		result = prime * result + ((timeTablePatternModel == null) ? 0 : timeTablePatternModel.hashCode());
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
		SlotTimeMasterModel other = (SlotTimeMasterModel) obj;
		if (SlotType == null) {
			if (other.SlotType != null)
				return false;
		} else if (!SlotType.equals(other.SlotType))
			return false;
		if (lockStatus == null) {
			if (other.lockStatus != null)
				return false;
		} else if (!lockStatus.equals(other.lockStatus))
			return false;
		if (numOfUnits == null) {
			if (other.numOfUnits != null)
				return false;
		} else if (!numOfUnits.equals(other.numOfUnits))
			return false;
		if (semId == null) {
			if (other.semId != null)
				return false;
		} else if (!semId.equals(other.semId))
			return false;
		if (semesterMasterModel == null) {
			if (other.semesterMasterModel != null)
				return false;
		} else if (!semesterMasterModel.equals(other.semesterMasterModel))
			return false;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		if (stmPkId == null) {
			if (other.stmPkId != null)
				return false;
		} else if (!stmPkId.equals(other.stmPkId))
			return false;
		if (timeTablePatternModel == null) {
			if (other.timeTablePatternModel != null)
				return false;
		} else if (!timeTablePatternModel.equals(other.timeTablePatternModel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SlotTimeMasterModel [stmPkId=" + stmPkId + ", timeTablePatternModel=" + timeTablePatternModel
				+ ", semId=" + semId + ", semesterMasterModel=" + semesterMasterModel + ", session=" + session
				+ ", SlotType=" + SlotType + ", numOfUnits=" + numOfUnits + ", lockStatus=" + lockStatus + "]";
	}
}
