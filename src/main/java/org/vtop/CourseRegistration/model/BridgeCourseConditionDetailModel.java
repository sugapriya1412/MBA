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
@Table(name="BRIDGE_COURSE_CONDN_DETAIL", schema="academics")
public class BridgeCourseConditionDetailModel implements Serializable {
							
	private static final long serialVersionUID = 1L;

	@Column(name="COURSE_ID")
	private String courseId;
					
	@Column(name="BRIDGE_COURSE_CODE")
	private String bridgeCourseCode;
					
	@Column(name="BRIDGE_COURSE_STATUS")
	private String bridgeCourseStatus;
	
	@Id
	@Column(name="PROGRAM_GROUP_ID")
	private Integer programGroupId;
			
	@Column(name="PROGRAM_SPECIALIZATION_ID")
	private Integer programSpecializationId;
			
	@Column(name="STATUS")
	private Integer status;
					
	@Column(name="LOG_USERID")
	private String logUserId;
			
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
			
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getBridgeCourseCode() {
		return bridgeCourseCode;
	}

	public void setBridgeCourseCode(String bridgeCourseCode) {
		this.bridgeCourseCode = bridgeCourseCode;
	}

	public String getBridgeCourseStatus() {
		return bridgeCourseStatus;
	}

	public void setBridgeCourseStatus(String bridgeCourseStatus) {
		this.bridgeCourseStatus = bridgeCourseStatus;
	}

	public Integer getProgramGroupId() {
		return programGroupId;
	}

	public void setProgramGroupId(Integer programGroupId) {
		this.programGroupId = programGroupId;
	}

	public Integer getProgramSpecializationId() {
		return programSpecializationId;
	}

	public void setProgramSpecializationId(Integer programSpecializationId) {
		this.programSpecializationId = programSpecializationId;
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
		return "BridgeCourseConditionDetailModel [courseId=" + courseId + ", bridgeCourseCode="
					+ bridgeCourseCode + ", bridgeCourseStatus=" + bridgeCourseStatus + ", programGroupId="
					+ programGroupId + ", programSpecializationId=" + programSpecializationId + ", status=" + status
					+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress="
					+ logIpaddress + "]";
	}
}
