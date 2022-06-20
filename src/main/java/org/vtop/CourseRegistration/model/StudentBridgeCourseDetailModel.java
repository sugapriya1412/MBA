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
@Table(name="STUDENT_BRIDGE_COURSE_DETAIL", schema="academics")
public class StudentBridgeCourseDetailModel implements Serializable {
						
			private static final long serialVersionUID = 1L;

			@Id
			@Column(name="REGNO")
			private String regNo;
						
			@Column(name="HSC_GROUP")
			private String hscGroup;
						
			@Column(name="NATIVE_LANGUAGE")
			private String nativeLanguage;
				
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

			public String getHscGroup() {
				return hscGroup;
			}

			public void setHscGroup(String hscGroup) {
				this.hscGroup = hscGroup;
			}

			public String getNativeLanguage() {
				return nativeLanguage;
			}

			public void setNativeLanguage(String nativeLanguage) {
				this.nativeLanguage = nativeLanguage;
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
				return "StudentBridgeCourseDetailModel [regNo=" + regNo + ", hscGroup=" + hscGroup + ", nativeLanguage="
						+ nativeLanguage + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
						+ ", logIpaddress=" + logIpaddress + "]";
			}
		
}
