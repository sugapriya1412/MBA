package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PROGRAMME_SPECIALIZATION", schema="vtopmaster")
public class ProgrammeSpecializationModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PROGRAMME_SPECIALIZATION_ID")
	private Integer specializationId;
	
	@Column(name="PRGRM_GROUP_PROGRAMME_GROUP_ID")
	private Integer groupId;
	
	@MapsId("groupId")
	@ManyToOne
	@JoinColumn(name="PRGRM_GROUP_PROGRAMME_GROUP_ID", insertable = false, updatable = false)
	private ProgrammeGroupModel programmeGroupModel;
					
	@Column(name="CODE")
	private String code;
		
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DESCRIPTION_SHORT")
	private String descriptionShort;
		
	@Column(name="COUNCIL_COUNCIL_NUMBER")
	private long councilNumber;
	
	@Column(name="START_YEAR")
	private String startYear;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public ProgrammeGroupModel getProgrammeGroupModel() {
		return programmeGroupModel;
	}

	public void setProgrammeGroupModel(ProgrammeGroupModel programmeGroupModel) {
		this.programmeGroupModel = programmeGroupModel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public long getCouncilNumber() {
		return councilNumber;
	}

	public void setCouncilNumber(long councilNumber) {
		this.councilNumber = councilNumber;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
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
		return "ProgrammeSpecializationModel [specializationId=" + specializationId + ", groupId=" + groupId
				+ ", programmeGroupModel=" + programmeGroupModel + ", code=" + code + ", description=" + description
				+ ", descriptionShort=" + descriptionShort + ", councilNumber=" + councilNumber + ", startYear="
				+ startYear + ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}
	
}
