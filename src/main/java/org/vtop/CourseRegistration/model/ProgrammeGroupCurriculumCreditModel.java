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
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRGM_GROUP_CURRICULUM_CREDITS", schema="ACADEMICS")
public class ProgrammeGroupCurriculumCreditModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProgrammeGroupCurriculumCreditPKModel pgcccPkId;
	
	@MapsId("groupId")
	@ManyToOne
	@JoinColumn(name="PRGM_GROUP_PROGRAMME_GROUP_ID", insertable = false, updatable = false)
	private ProgrammeGroupModel programmeGroupModel;
	
	@Column(name="COUNCIL_COUNCIL_NUMBER")
	private int councilNumber;
	
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
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public ProgrammeGroupCurriculumCreditPKModel getPgcccPkId() {
		return pgcccPkId;
	}

	public void setPgcccPkId(ProgrammeGroupCurriculumCreditPKModel pgcccPkId) {
		this.pgcccPkId = pgcccPkId;
	}

	public ProgrammeGroupModel getProgrammeGroupModel() {
		return programmeGroupModel;
	}

	public void setProgrammeGroupModel(ProgrammeGroupModel programmeGroupModel) {
		this.programmeGroupModel = programmeGroupModel;
	}

	public int getCouncilNumber() {
		return councilNumber;
	}

	public void setCouncilNumber(int councilNumber) {
		this.councilNumber = councilNumber;
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
		result = prime * result + ((pgcccPkId == null) ? 0 : pgcccPkId.hashCode());
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
		ProgrammeGroupCurriculumCreditModel other = (ProgrammeGroupCurriculumCreditModel) obj;
		if (pgcccPkId == null) {
			if (other.pgcccPkId != null)
				return false;
		} else if (!pgcccPkId.equals(other.pgcccPkId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProgrammeGroupCurriculumCreditModel [pgcccPkId=" + pgcccPkId + ", programmeGroupModel="
				+ programmeGroupModel + ", councilNumber=" + councilNumber + ", universityCoreCredits="
				+ universityCoreCredits + ", universityElectiveCredits=" + universityElectiveCredits
				+ ", programmeCoreCredits=" + programmeCoreCredits + ", programmeElectiveCredits="
				+ programmeElectiveCredits + ", totalCredits=" + totalCredits + ", logUserId=" + logUserId
				+ ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
	
}
