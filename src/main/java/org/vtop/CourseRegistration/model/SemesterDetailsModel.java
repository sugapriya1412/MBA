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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SEMESTER_DETAILS", schema="academics")
public class SemesterDetailsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Valid
	@Column(name="SEMESTER_SUB_ID")
	private String semesterSubId;

	@Column(name="CAMPUS_CODE")
	private String campusCode;
	
	//@NotNull(message="This Filed is Reguired")
	@MapsId("campusCode")
	@ManyToOne
	@JoinColumn(name="CAMPUS_CODE", insertable = false, updatable = false)
	private Campus campusModel;
	
	@NotNull(message="This Filed is Required")
	@Column(name="TMTBLPATTERN_MASTER_PATTERN_ID")
	private Integer patternId;
	
	@Transient
	private String semesterYear;

	@MapsId("patternId")
	@ManyToOne
	@JoinColumn(name="TMTBLPATTERN_MASTER_PATTERN_ID", insertable = false, updatable = false)
	private TimeTablePatternModel timeTablePatternModel;
	
	@NotNull(message="This Filed is Reguired")
	@Column(name="SEMESTER_MASTER_SEMESTER_ID")
	private Integer semesterId;
	
	@MapsId("semesterId")
	@ManyToOne
	@JoinColumn(name="SEMESTER_MASTER_SEMESTER_ID", insertable = false, updatable = false)
	private SemesterMasterModel semesterMasterModel;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DESCRIPTION_SHORT")
	private String descriptionShort;
	
	@NotNull(message="This Filed is Reguired")
	@Column(name="NUMBER_OF_DAYS")
	private Integer noOfDays;
	
	@Column(name="LOCK_STATUS")
	private Integer status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;	
	
	@Column(name="START_DATE")	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	
	@Column(name="END_DATE")	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setTimeTablePatternModel(TimeTablePatternModel timeTablePatternModel) {
		this.timeTablePatternModel = timeTablePatternModel;
	}

	public String getSemesterYear() {
		return getDescriptionShort().substring(getDescriptionShort().length()-7, getDescriptionShort().length());
	}
		
	public void setSemesterYear(String semesterYear) {
		this.semesterYear = semesterYear;
	}
	
	public String getSemesterSubId() {
		return semesterSubId;
	}
	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}
	public Integer getPatternId() {
		return patternId;
	}
	
	public void setPatternId(Integer patternId) {
		this.patternId = patternId;
	}
	public Integer getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(Integer semesterId) {
		this.semesterId = semesterId;
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
	
	public TimeTablePatternModel getTimeTablePatternModel() {
		return timeTablePatternModel;
	}

	public void setTablePatternModel(TimeTablePatternModel timeTablePatternModel) {
		this.timeTablePatternModel = timeTablePatternModel;
	}
	
	public SemesterMasterModel getSemesterMasterModel() {
		return semesterMasterModel;
	}

	public void setSemesterMasterModel(SemesterMasterModel semesterMasterModel) {
		this.semesterMasterModel = semesterMasterModel;
	}
	
	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public Campus getCampusModel() {
		return campusModel;
	}

	public void setCampusModel(Campus campusModel) {
		this.campusModel = campusModel;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
		
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
		return "SemesterDetailsModel [semesterSubId=" + semesterSubId + ", campusCode=" + campusCode + ", campusModel="
				+ campusModel + ", patternId=" + patternId + ", semesterYear=" + semesterYear
				+ ", timeTablePatternModel=" + timeTablePatternModel + ", semesterId=" + semesterId
				+ ", semesterMasterModel=" + semesterMasterModel + ", description=" + description
				+ ", descriptionShort=" + descriptionShort + ", noOfDays=" + noOfDays + ", status=" + status
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
		
}
