package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="REGISTRATION_SCHEDULE", schema="academics")
public class RegistrationScheduleModel implements Serializable {
					
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="REGNO")
		private String regNo;
		
		@Column(name="REG_DATE")	
		@Temporal(TemporalType.DATE)
		private Date regDate;
					
		@Column(name="FROM_TIME")
		private String fromTime;
		
		@Column(name="TO_TIME")
		private String toTime;
		
		@Column(name="STATUS")
		private Integer status;
			
		@Column(name="LOG_USERID")
		private String logUserId;
			
		@Column(name="LOG_TIMESTAMP")
		@Temporal(TemporalType.TIMESTAMP)
		private Date logTimestamp;
			
		@Column(name="LOG_IPADDRESS")
		private String logIpaddress;

		public String getRegNo() {
			return regNo;
		}

		public void setRegNo(String regNo) {
			this.regNo = regNo;
		}

		public Date getRegDate() {
			return regDate;
		}

		public void setRegDate(Date regDate) {
			this.regDate = regDate;
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

		public String getLogTimestamp() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		    if(this.logTimestamp==null) {
		    	return "";
		    } else {
		      return dateFormat.format(logTimestamp); 
		    }
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
			return "RegistrationScheduleModel [regNo=" + regNo + ", regDate=" + regDate + ", fromTime=" + fromTime
					+ ", toTime=" + toTime + ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp="
					+ logTimestamp + ", logIpaddress=" + logIpaddress + "]";
		}		
}
