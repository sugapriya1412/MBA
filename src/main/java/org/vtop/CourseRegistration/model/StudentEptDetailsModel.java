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
@Table(name="STUDENT_EPT_DETAILS", schema="academics")
public class StudentEptDetailsModel implements Serializable {
					
			private static final long serialVersionUID = 1L;

			@Id
			@Column(name="STDNTSLGNDTLS_REGISTER_NUMBER")
			private String registerNumber;
					
			@Column(name="MARK")
			private Integer mark;
					
			@Column(name="RESULT")
			private String result;
			
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

			public Integer getMark() {
				return mark;
			}

			public void setMark(Integer mark) {
				this.mark = mark;
			}

			public String getResult() {
				return result;
			}

			public void setResult(String result) {
				this.result = result;
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
				return "StudentEptDetailsModel [registerNumber=" + registerNumber + ", mark=" + mark + ", result="
						+ result + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress="
						+ logIpaddress + "]";
			}
					
}
