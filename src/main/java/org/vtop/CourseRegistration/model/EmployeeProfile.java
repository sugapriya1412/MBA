package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEE_PROFILE",schema = "hrms")
public class EmployeeProfile {
	
	@Id
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column (name = "LAST_NAME")
	private String lastName;
	
	@Column (name = "DATE_OF_BIRTH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date dateOfBirth;
	
	@Column (name = "INTERCOM_NUMBER")
	private Long intercomNumber;
	
	@Column (name = "MOBILE_NUMBER")
	private Long mobileNumber;
	
	@Column (name = "VIT_EMAIL_ID")
	private String vitEmailId;
	
	@Column (name = "BLOOD_GROUP")
	private String bloodGroup;
	
	@Column (name = "GENDER")
	private String gender;
	
	@Column (name = "DATE_OF_JOINING")
	private Date dateOfJoining;
	
	@Column (name = "DESIGNATIONS_DESIGNATION_ID")
	private Long designationId;
	
/*	@ManyToOne
	@JoinColumn(name="DESIGNATIONS_DESIGNATION_ID", insertable = false, updatable = false)
	private Designations designations;*/
		
	@Column (name = "CAMPUS_CODE")
	private String campusCode;
	
	@ManyToOne
	@JoinColumn(name="CAMPUS_CODE", insertable = false, updatable = false)
	private Campus campus;
	
	@Column (name = "EMPLOYEMENT_TYPE")
	private String employmentType;
	
	@Column (name = "EMPLOYEECATEGORIES_CATEGORY_ID")
	private Long categoryId;
	
/*	@ManyToOne
	@JoinColumn(name="EMPLOYEECATEGORIES_CATEGORY_ID", insertable = false, updatable = false)
	private EmployeeCatagories employeeCatagories;*/
	
	@Column (name = "COST_CENTRE_CENTRE_ID")
	private Integer centreId;
	
	@ManyToOne
	@JoinColumn(name="COST_CENTRE_CENTRE_ID", insertable = false, updatable = false)
	private CostCentre costCentre;
	
	@Column (name = "DEPARTMENTS_DEPARTMENT_ID")
	private Long departmentId;
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENTS_DEPARTMENT_ID", insertable = false, updatable = false)
	private Departments departments;
	
	@Column (name = "EMPLOYMENT_STATUS")
	private Integer employmentStatus;
	
	@Column (name = "LEAVE_STATUS")
	private Integer leaveStatus;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getIntercomNumber() {
		return intercomNumber;
	}

	public void setIntercomNumber(Long intercomNumber) {
		this.intercomNumber = intercomNumber;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getVitEmailId() {
		return vitEmailId;
	}

	public void setVitEmailId(String vitEmailId) {
		this.vitEmailId = vitEmailId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	/*public Designations getDesignations() {
		return designations;
	}

	public void setDesignations(Designations designations) {
		this.designations = designations;
	}*/

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/*public EmployeeCatagories getEmployeeCatagories() {
		return employeeCatagories;
	}

	public void setEmployeeCatagories(EmployeeCatagories employeeCatagories) {
		this.employeeCatagories = employeeCatagories;
	}*/

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public CostCentre getCostCentre() {
		return costCentre;
	}

	public void setCostCentre(CostCentre costCentre) {
		this.costCentre = costCentre;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Departments getDepartments() {
		return departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
	}

	public Integer getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public Integer getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(Integer leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	/*@Override
	public String toString() {
		return "EmployeeProfile [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", intercomNumber=" + intercomNumber + ", mobileNumber="
				+ mobileNumber + ", vitEmailId=" + vitEmailId + ", bloodGroup=" + bloodGroup + ", gender=" + gender
				+ ", dateOfJoining=" + dateOfJoining + ", designationId=" + designationId + ", designations="
				+ designations + ", campusCode=" + campusCode + ", campus=" + campus + ", employmentType="
				+ employmentType + ", categoryId=" + categoryId + ", employeeCatagories=" + employeeCatagories
				+ ", centreId=" + centreId + ", costCentre=" + costCentre + ", departmentId=" + departmentId
				+ ", departments=" + departments + ", employmentStatus=" + employmentStatus + ", leaveStatus="
				+ leaveStatus + "]";
	}*/

	
}


