package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Table(name="PRG_SPLZTN_CURRICULUM_CREDITS", schema="ACADEMICS")
public class ProgrammeSpecializationCurriculumCreditModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProgrammeSpecializationCurriculumCreditPKModel pscccPkId;
	
	//@MapsId("specializationId")
	@ManyToOne
	@JoinColumn(name="PRGSPLZN_PRG_SPECIALIZATION_ID", referencedColumnName="PROGRAMME_SPECIALIZATION_ID", 
					insertable = false, updatable = false)
	private ProgrammeSpecializationModel programmeSpecializationModel;
	
	@Column(name="UNIVERSITY_CORE_CREDITS")
	private int universityCoreCredits;
	
	@Column(name="UNIVERSITY_ELECTIVE_CREDITS")
	private int universityElectiveCredits;
	
	@Column(name="PROGRAMME_CORE_CREDITS")
	private int programmeCoreCredits;
	
	@Column(name="PROGRAMME_ELECTIVE_CREDITS")
	private int programmeElectiveCredits;
	
	@Column(name="TOTAL_CREDITS")
	private int totalCredits;
	
	@Column(name="TOTAL_ALLOTTED_CREDIT")
	private int totalAllottedCredit;
	
	@Column(name="COURSE_SYSTEM")
	private String courseSystem;
	
	@Column(name="DISPLAY_STATUS")
	private int displayStatus;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	

	public ProgrammeSpecializationCurriculumCreditPKModel getPscccPkId() {
		return pscccPkId;
	}

	public void setPscccPkId(ProgrammeSpecializationCurriculumCreditPKModel pscccPkId) {
		this.pscccPkId = pscccPkId;
	}

	public ProgrammeSpecializationModel getProgrammeSpecializationModel() {
		return programmeSpecializationModel;
	}

	public void setProgrammeSpecializationModel(ProgrammeSpecializationModel programmeSpecializationModel) {
		this.programmeSpecializationModel = programmeSpecializationModel;
	}

	public int getUniversityCoreCredits() {
		return universityCoreCredits;
	}

	public void setUniversityCoreCredits(int universityCoreCredits) {
		this.universityCoreCredits = universityCoreCredits;
	}

	public int getUniversityElectiveCredits() {
		return universityElectiveCredits;
	}

	public void setUniversityElectiveCredits(int universityElectiveCredits) {
		this.universityElectiveCredits = universityElectiveCredits;
	}

	public int getProgrammeCoreCredits() {
		return programmeCoreCredits;
	}

	public void setProgrammeCoreCredits(int programmeCoreCredits) {
		this.programmeCoreCredits = programmeCoreCredits;
	}

	public int getProgrammeElectiveCredits() {
		return programmeElectiveCredits;
	}

	public void setProgrammeElectiveCredits(int programmeElectiveCredits) {
		this.programmeElectiveCredits = programmeElectiveCredits;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public int getTotalAllottedCredit() {
		return totalAllottedCredit;
	}

	public void setTotalAllottedCredit(int totalAllottedCredit) {
		this.totalAllottedCredit = totalAllottedCredit;
	}

	public String getCourseSystem() {
		return courseSystem;
	}

	public void setCourseSystem(String courseSystem) {
		this.courseSystem = courseSystem;
	}

	public int getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(int displayStatus) {
		this.displayStatus = displayStatus;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pscccPkId == null) ? 0 : pscccPkId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgrammeSpecializationCurriculumCreditModel other = (ProgrammeSpecializationCurriculumCreditModel) obj;
		if (pscccPkId == null) {
			if (other.pscccPkId != null)
				return false;
		} else if (!pscccPkId.equals(other.pscccPkId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProgrammeSpecializationCurriculumCreditModel [pscccPkId=" + pscccPkId
				+ ", programmeSpecializationModel=" + programmeSpecializationModel + ", universityCoreCredits="
				+ universityCoreCredits + ", universityElectiveCredits=" + universityElectiveCredits
				+ ", programmeCoreCredits=" + programmeCoreCredits + ", programmeElectiveCredits="
				+ programmeElectiveCredits + ", totalCredits=" + totalCredits + ", totalAllottedCredit="
				+ totalAllottedCredit + ", courseSystem=" + courseSystem + ", displayStatus=" + displayStatus
				+ ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}
}
