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
@Table(name="CLASS_GROUP_MASTER", schema="academics")
public class ClassGroupModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CLASS_GROUP_ID")
	private String classGroupId;
	
	@Column(name="DESCRIPTION")
	private String description;
		
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	
	
	public String getClassGroupId() {
		return classGroupId;
	}

	public void setClassGroupId(String classGroupId) {
		this.classGroupId = classGroupId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "ClassGroupModel [classGroupId=" + classGroupId + ", description=" + description 
				+ ", status=" + status + ", logUserId="
				+ logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
}
