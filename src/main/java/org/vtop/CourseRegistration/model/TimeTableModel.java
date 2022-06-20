package org.vtop.CourseRegistration.model;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TIME_TABLE", schema="academics")
public class TimeTableModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SLOT_ID")
	private Long slotId;
	
	@Valid
	@Column(name="SLOT_NAME")
	private String slotName;
	
	@Column(name="NUMBER_OF_UNITS_PER_WEEK")
	private Integer noOfUnitPerWeek;
	
	@Column(name="TIMINGS")
	private String timing;
	
	@Column(name="SLOT_TYPE")
	private String slotType;
	
	@Column(name="ATTENDANCE_SLOT")
	private String attendanceSlot;
	
	@Column(name="CLASH_SLOT")
	private String clashSlot;
	
	@Column(name="PATTERN_ID")
	private Integer patternId;
	
	@NotNull(message="Choose Time Table Pattern")
	@MapsId("patternId")
	@ManyToOne
	@JoinColumn(name="PATTERN_ID", insertable = false, updatable = false)
	private TimeTablePatternModel timeTablePatternModel;	
		
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	
	@Column(name="TT_SESSION")
	private String ttSession;

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public Integer getNoOfUnitPerWeek() {
		return noOfUnitPerWeek;
	}

	public void setNoOfUnitPerWeek(Integer noOfUnitPerWeek) {
		this.noOfUnitPerWeek = noOfUnitPerWeek;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public String getAttendanceSlot() {
		return attendanceSlot;
	}

	public void setAttendanceSlot(String attendanceSlot) {
		this.attendanceSlot = attendanceSlot;
	}

	public String getClashSlot() {
		return clashSlot;
	}

	public void setClashSlot(String clashSlot) {
		this.clashSlot = clashSlot;
	}

	public Integer getPatternId() {
		return patternId;
	}

	public void setPatternId(Integer patternId) {
		this.patternId = patternId;
	}

	public TimeTablePatternModel getTimeTablePatternModel() {
		return timeTablePatternModel;
	}

	public void setTimeTablePatternModel(TimeTablePatternModel timeTablePatternModel) {
		this.timeTablePatternModel = timeTablePatternModel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public String getTtSession() {
		return ttSession;
	}

	public void setTtSession(String ttSession) {
		this.ttSession = ttSession;
	}

	@Override
	public String toString() {
		return "TimeTableModel [slotId=" + slotId + ", slotName=" + slotName + ", noOfUnitPerWeek=" + noOfUnitPerWeek
				+ ", timing=" + timing + ", slotType=" + slotType + ", attendanceSlot=" + attendanceSlot
				+ ", clashSlot=" + clashSlot + ", patternId=" + patternId + ", timeTablePatternModel="
				+ timeTablePatternModel + ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp="
				+ logTimestamp + ", logIpaddress=" + logIpaddress + ", ttSession=" + ttSession + "]";
	}
		
}

