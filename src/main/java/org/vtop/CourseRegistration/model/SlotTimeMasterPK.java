package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class SlotTimeMasterPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="TMTBLPATTERN_MASTER_PATTERN_ID")
	private Integer patternId;
	
	@Column(name="SLOT")
	private String slot;
	
	@Column(name="WEEK_DAY")
	private String weekdays;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)	
	@Column(name="SLOT_STARTING_TIME")
	private Date slotStartingTime;
	
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	@Column(name="SLOT_ENDING_TIME")
	private Date slotEndingTime;

	public Integer getPatternId() {
		return patternId;
	}

	public void setPatternId(Integer patternId) {
		this.patternId = patternId;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}

	public Date getSlotStartingTime() {
		return slotStartingTime;
	}

	public void setSlotStartingTime(Date slotStartingTime) {
		this.slotStartingTime = slotStartingTime;
	}

	public Date getSlotEndingTime() {
		return slotEndingTime;
	}

	public void setSlotEndingTime(Date slotEndingTime) {
		this.slotEndingTime = slotEndingTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patternId == null) ? 0 : patternId.hashCode());
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + ((slotEndingTime == null) ? 0 : slotEndingTime.hashCode());
		result = prime * result + ((slotStartingTime == null) ? 0 : slotStartingTime.hashCode());
		result = prime * result + ((weekdays == null) ? 0 : weekdays.hashCode());
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
		SlotTimeMasterPK other = (SlotTimeMasterPK) obj;
		if (patternId == null) {
			if (other.patternId != null)
				return false;
		} else if (!patternId.equals(other.patternId))
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		if (slotEndingTime == null) {
			if (other.slotEndingTime != null)
				return false;
		} else if (!slotEndingTime.equals(other.slotEndingTime))
			return false;
		if (slotStartingTime == null) {
			if (other.slotStartingTime != null)
				return false;
		} else if (!slotStartingTime.equals(other.slotStartingTime))
			return false;
		if (weekdays == null) {
			if (other.weekdays != null)
				return false;
		} else if (!weekdays.equals(other.weekdays))
			return false;
		return true;
	}
			
}
