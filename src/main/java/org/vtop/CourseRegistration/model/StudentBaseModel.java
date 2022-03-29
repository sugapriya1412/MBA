package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="STUDENT_BASE", schema="admissions")
public class StudentBaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "APPLICATION_NUMBER")
	private Long applicationNumber;
	
	@Column(name = "STUDENT_NAME")
	private String studentName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "RANK_NUMBER")
	private Long rankNumber;
	
	@ManyToOne
	@JoinColumn(name = "PRGSPL_PRGRM_SPECIALIZATION_ID")
	private ProgrammeSpecializationModel programmeSpecializationModel;	
	
/*	@ManyToOne
	@JoinColumn(name = "COLLEGE_FEES_FEE_ID")
	private CollegeFees collegeFees;	
	*/
	@Column(name = "FEES_STATUS")
	private String feesStatus;
	
	@Column(name = "ADMISSION_STATUS")
	private String admissionStatus;
	
	@Column(name = "ELIGIBILITY_CODE")
	private String eligibilityCode;
	
	@Column(name = "PERSONAL_DETAIL_STATUS")
	private Integer personalDetailStatus;
	
	@Column(name = "EDUCATION_DETAIL_STATUS")
	private Integer educationDetailsStatus;
	
	@Column(name = "FAMILY_DETAIL_STATUS")
	private Integer familyDetailStatus;
	
	@ManyToOne
	@JoinColumn(name = "CAMPUS_CODE")
	private Campus campus;	
	
	@Column(name = "LOG_USERID")
	private String logUserid;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_TIMESTAMP")
	private Date logTimestamp;
	
	@Column(name = "LOG_IPADDRESS")
	private String logIpAddress;

	public Long getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(Long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getRankNumber() {
		return rankNumber;
	}

	public void setRankNumber(Long rankNumber) {
		this.rankNumber = rankNumber;
	}

	public ProgrammeSpecializationModel getProgrammeSpecializationModel() {
		return programmeSpecializationModel;
	}

	public void setProgrammeSpecializationModel(ProgrammeSpecializationModel programmeSpecializationModel) {
		this.programmeSpecializationModel = programmeSpecializationModel;
	}

	/*public CollegeFees getCollegeFees() {
		return collegeFees;
	}

	public void setCollegeFees(CollegeFees collegeFees) {
		this.collegeFees = collegeFees;
	}	*/

	public String getFeesStatus() {
		return feesStatus;
	}

	public void setFeesStatus(String feesStatus) {
		this.feesStatus = feesStatus;
	}

	public String getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(String admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public String getEligibilityCode() {
		return eligibilityCode;
	}

	public void setEligibilityCode(String eligibilityCode) {
		this.eligibilityCode = eligibilityCode;
	}

	public Integer getPersonalDetailStatus() {
		return personalDetailStatus;
	}

	public void setPersonalDetailStatus(Integer personalDetailStatus) {
		this.personalDetailStatus = personalDetailStatus;
	}

	public Integer getEducationDetailsStatus() {
		return educationDetailsStatus;
	}

	public void setEducationDetailsStatus(Integer educationDetailsStatus) {
		this.educationDetailsStatus = educationDetailsStatus;
	}

	public Integer getFamilyDetailStatus() {
		return familyDetailStatus;
	}

	public void setFamilyDetailStatus(Integer familyDetailStatus) {
		this.familyDetailStatus = familyDetailStatus;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}	

	public String getLogUserid() {
		return logUserid;
	}

	public void setLogUserid(String logUserid) {
		this.logUserid = logUserid;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	/*@Override
	public String toString() {
		return "StudentBase [applicationNumber=" + applicationNumber + ", studentName=" + studentName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", rankNumber=" + rankNumber + ", programmeSpecializationModel="
				+ programmeSpecializationModel + ", collegeFees=" + collegeFees + ", feesStatus=" + feesStatus
				+ ", admissionStatus=" + admissionStatus + ", eligibilityCode=" + eligibilityCode
				+ ", personalDetailStatus=" + personalDetailStatus + ", educationDetailsStatus="
				+ educationDetailsStatus + ", familyDetailStatus=" + familyDetailStatus + ", campus=" + campus
				+ ", logUserid=" + logUserid + ", logTimestamp=" + logTimestamp + ", logIpAddress=" + logIpAddress
				+ "]";
	}	*/
}
