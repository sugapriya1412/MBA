package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="PRGSPL_CURR_CATEGORY_CREDIT", schema="ACADEMICS")
public class ProgrammeSpecializationCurriculumCategoryCredit implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProgrammeSpecializationCurriculumCategoryCreditPk id;
	
	@ManyToOne
	@JoinColumn(name="PRGSPLZN_PRG_SPECIALIZATION_ID", referencedColumnName="PROGRAMME_SPECIALIZATION_ID", 
					insertable = false, updatable = false)
	private ProgrammeSpecializationModel programmeSpecializationModel;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATEGORY", referencedColumnName="COURSE_CATEGORY", insertable = false, updatable = false)
	private CurriculumCategoryMaster curriculumCategoryMaster;
	
	@Column(name="CREDIT")
	private int credit;
	
	@Column(name="COURSE_ALLOT_PROCESS_STATUS")
	private int courseAllotProcessStatus;
	
	@Column(name="TOTAL_CREDIT_CALC_STATUS")
	private int totalCreditCalculationStatus;
	
	@Column(name="REG_CREDIT_CALC_STATUS")
	private int registrationCreditCalculationStatus;
	
	@Column(name="CGPA_CALC_STATUS")
	private int cgpaCalculationStatus;
	
	@Column(name="DEGREE_ELIGIBILITY_STATUS")
	private int degreeEligibilityStatus;
		
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddress;

	
	public ProgrammeSpecializationCurriculumCategoryCreditPk getId() {
		return id;
	}

	public void setId(ProgrammeSpecializationCurriculumCategoryCreditPk id) {
		this.id = id;
	}

	public ProgrammeSpecializationModel getProgrammeSpecializationModel() {
		return programmeSpecializationModel;
	}

	public void setProgrammeSpecializationModel(ProgrammeSpecializationModel programmeSpecializationModel) {
		this.programmeSpecializationModel = programmeSpecializationModel;
	}

	public CurriculumCategoryMaster getCurriculumCategoryMaster() {
		return curriculumCategoryMaster;
	}

	public void setCurriculumCategoryMaster(CurriculumCategoryMaster curriculumCategoryMaster) {
		this.curriculumCategoryMaster = curriculumCategoryMaster;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getCourseAllotProcessStatus() {
		return courseAllotProcessStatus;
	}

	public void setCourseAllotProcessStatus(int courseAllotProcessStatus) {
		this.courseAllotProcessStatus = courseAllotProcessStatus;
	}

	public int getTotalCreditCalculationStatus() {
		return totalCreditCalculationStatus;
	}

	public void setTotalCreditCalculationStatus(int totalCreditCalculationStatus) {
		this.totalCreditCalculationStatus = totalCreditCalculationStatus;
	}

	public int getRegistrationCreditCalculationStatus() {
		return registrationCreditCalculationStatus;
	}

	public void setRegistrationCreditCalculationStatus(int registrationCreditCalculationStatus) {
		this.registrationCreditCalculationStatus = registrationCreditCalculationStatus;
	}

	public int getCgpaCalculationStatus() {
		return cgpaCalculationStatus;
	}

	public void setCgpaCalculationStatus(int cgpaCalculationStatus) {
		this.cgpaCalculationStatus = cgpaCalculationStatus;
	}

	public int getDegreeEligibilityStatus() {
		return degreeEligibilityStatus;
	}

	public void setDegreeEligibilityStatus(int degreeEligibilityStatus) {
		this.degreeEligibilityStatus = degreeEligibilityStatus;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public Date getLogTimeStamp() {
		return logTimeStamp;
	}

	public void setLogTimeStamp(Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ProgrammeSpecializationCurriculumCategoryCredit other = (ProgrammeSpecializationCurriculumCategoryCredit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProgrammeSpecializationCurriculumCategoryCredit [id=" + id + ", programmeSpecializationModel="
				+ programmeSpecializationModel + ", curriculumCategoryMaster=" + curriculumCategoryMaster + ", credit="
				+ credit + ", courseAllotProcessStatus=" + courseAllotProcessStatus + ", totalCreditCalculationStatus="
				+ totalCreditCalculationStatus + ", registrationCreditCalculationStatus="
				+ registrationCreditCalculationStatus + ", cgpaCalculationStatus=" + cgpaCalculationStatus
				+ ", degreeEligibilityStatus=" + degreeEligibilityStatus + ", logUserId=" + logUserId
				+ ", logTimeStamp=" + logTimeStamp + ", logIpAddress=" + logIpAddress + "]";
	}
}
