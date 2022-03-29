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
@Table(name="REGISTRATION_LOG", schema="academics")
public class RegistrationLogModel implements Serializable {
						
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="REGNO")
		private String regNo;
		
		@Column(name="LOG_STATUS")
		private Integer logstatus;
		
		@Column(name="LOGIN_TIMESTAMP" , nullable=false)
		@Temporal(TemporalType.TIMESTAMP)
		//@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") //DD/MM/YYYY HH24:MI:SS
		private Date loginTimestamp;
				
		@Column(name="LOGIN_IPADDRESS")
		private String loginIpaddress;
		
		@Column(name="ACTIVE_TIMESTAMP", nullable=false)		
		@Temporal(TemporalType.TIMESTAMP)
		//@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
		private Date activeTimestamp;
				
		@Column(name="LOGOUT_TIMESTAMP" , nullable=false)
		@Temporal(TemporalType.TIMESTAMP)
		//@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
		private Date logoutTimestamp;
				
		@Column(name="LOGOUT_IPADDRESS")
		private String logoutIpaddress;

		public String getRegNo() {
			return regNo;
		}

		public void setRegNo(String regNo) {
			this.regNo = regNo;
		}

		public Integer getLogstatus() {
			return logstatus;
		}

		public void setLogstatus(Integer logstatus) {
			this.logstatus = logstatus;
		}

		public String getLoginTimestamp() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		    if(this.loginTimestamp==null) {
		    	return "";
		    } else {
		      return dateFormat.format(loginTimestamp); 
		    }
		}

		public void setLoginTimestamp(Date loginTimestamp) {
			this.loginTimestamp = loginTimestamp;
		}

		public String getLoginIpaddress() {
			return loginIpaddress;
		}

		public void setLoginIpaddress(String loginIpaddress) {
			this.loginIpaddress = loginIpaddress;
		}
		
		public String getActiveTimestamp() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		    if(this.activeTimestamp==null) {
		    	return "";
		    } else {
		      return dateFormat.format(activeTimestamp); 
		    }
		}

		public void setActiveTimestamp(Date activeTimestamp) {
			this.activeTimestamp = activeTimestamp;
		}
		
		public String getLogoutTimestamp() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		    if(this.logoutTimestamp==null) {
		    	return "";
		    } else {
		      return dateFormat.format(logoutTimestamp); 
		    }
		}

		public void setLogoutTimestamp(Date logoutTimestamp) {
			this.logoutTimestamp = logoutTimestamp;
		}

		public String getLogoutIpaddress() {
			return logoutIpaddress;
		}

		public void setLogoutIpaddress(String logoutIpaddress) {
			this.logoutIpaddress = logoutIpaddress;
		}

		@Override
		public String toString() {
			return "RegistrationLogModel [regNo=" + regNo + ", logstatus=" + logstatus + ", loginTimestamp="
					+ loginTimestamp + ", loginIpaddress=" + loginIpaddress + ", activeTimestamp=" + activeTimestamp
					+ ", logoutTimestamp=" + logoutTimestamp + ", logoutIpaddress=" + logoutIpaddress + "]";
		}		
}
