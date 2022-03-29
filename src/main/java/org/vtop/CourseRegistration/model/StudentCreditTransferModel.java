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
@Table(name="STUDENT_CREDIT_TRANSFER", schema="academics")
public class StudentCreditTransferModel implements Serializable {
				
		private static final long serialVersionUID = 1L;

		@Id
		@Column(name="STDNTSLGNDTLS_REGISTER_NUMBER")
		private String registerNumber;
				
		@Column(name="OLD_REGISTER_NUMBER")
		private String oldRegisterNumber;
				
		@Column(name="REMARKS")
		private String remarks;
		
		@Column(name="LOG_USERID")
		private String logUserId;
		
		@Column(name="LOG_TIMESTAMP")
		@Temporal(TemporalType.TIMESTAMP)
		private Date logTimestamp;
		
		@Column(name="LOG_IPADDRESS")
		private String logIpaddress;

		public String getRegisterNumber() {
			return registerNumber;
		}

		public void setRegisterNumber(String registerNumber) {
			this.registerNumber = registerNumber;
		}

		public String getOldRegisterNumber() {
			return oldRegisterNumber;
		}

		public void setOldRegisterNumber(String oldRegisterNumber) {
			this.oldRegisterNumber = oldRegisterNumber;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
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
			return "StudentCreditTransferModel [registerNumber=" + registerNumber + ", oldRegisterNumber="
					+ oldRegisterNumber + ", remarks=" + remarks + ", logUserId=" + logUserId + ", logTimestamp="
					+ logTimestamp + ", logIpaddress=" + logIpaddress + "]";
		}
			
}
