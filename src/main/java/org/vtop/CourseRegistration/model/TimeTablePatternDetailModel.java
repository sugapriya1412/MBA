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

@Entity
@Table(name="TIME_TABLE_PATTERN_DETAIL", schema="academics")
public class TimeTablePatternDetailModel implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		
		@EmbeddedId
	    private TimeTablePatternDetailPK ttpdPkId;
		
		@ManyToOne
		@JoinColumn(name="PATTERN_ID", insertable = false, updatable = false)
		private TimeTablePatternModel timeTablePatternModel;
		
		@Column(name="NUM_TH_SLOTS")
		private Integer numThSlots;
		
		@Column(name="NUM_LAB_SLOTS")
		private Integer numLabSlots;
		
		@Column(name="LOCK_STATUS")
		private int status;
		
		@Column(name="LOG_USERID")
		private String logUserId;
		
		@Column(name="LOG_TIMESTAMP")
		@Temporal(TemporalType.TIMESTAMP)
		private Date logTimestamp;
		
		@Column(name="LOG_IPADDRESS")
		private String logIpaddress;

		public TimeTablePatternDetailPK getTtpdPkId() {
			return ttpdPkId;
		}

		public void setTtpdPkId(TimeTablePatternDetailPK ttpdPkId) {
			this.ttpdPkId = ttpdPkId;
		}

		public TimeTablePatternModel getTimeTablePatternModel() {
			return timeTablePatternModel;
		}

		public void setTimeTablePatternModel(TimeTablePatternModel timeTablePatternModel) {
			this.timeTablePatternModel = timeTablePatternModel;
		}

		public Integer getNumThSlots() {
			return numThSlots;
		}

		public void setNumThSlots(Integer numThSlots) {
			this.numThSlots = numThSlots;
		}

		public Integer getNumLabSlots() {
			return numLabSlots;
		}

		public void setNumLabSlots(Integer numLabSlots) {
			this.numLabSlots = numLabSlots;
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

		@Override
		public String toString() {
			return "TimeTablePatternDetailModel [ttpdPkId=" + ttpdPkId + ", timeTablePatternModel="
					+ timeTablePatternModel + ", numThSlots=" + numThSlots + ", numLabSlots=" + numLabSlots
					+ ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
					+ ", logIpaddress=" + logIpaddress + "]";
		}
}
