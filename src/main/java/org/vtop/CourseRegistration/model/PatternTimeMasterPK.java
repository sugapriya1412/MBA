package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;

public class PatternTimeMasterPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="TMTBLPATTERN_MASTER_PATTERN_ID")	
	private Integer timeTablePatternModel;
	
	@Column(name="SLOT_TYPE")
	private String slotType;
	
	@Column(name="SLOT_NAME")
	private String slotName;

	public Integer getTimeTablePatternModel() {
		return timeTablePatternModel;
	}

	public void setTimeTablePatternModel(Integer timeTablePatternModel) {
		this.timeTablePatternModel = timeTablePatternModel;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((slotName == null) ? 0 : slotName.hashCode());
		result = prime * result + ((slotType == null) ? 0 : slotType.hashCode());
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
		PatternTimeMasterPK other = (PatternTimeMasterPK) obj;
		if (slotName == null) {
			if (other.slotName != null)
				return false;
		} else if (!slotName.equals(other.slotName))
			return false;
		if (slotType == null) {
			if (other.slotType != null)
				return false;
		} else if (!slotType.equals(other.slotType))
			return false;
		if (timeTablePatternModel == null) {
			if (other.timeTablePatternModel != null)
				return false;
		} else if (!timeTablePatternModel.equals(other.timeTablePatternModel))
			return false;
		return true;
	}

	

}