package org.vtop.CourseRegistration.model;

import java.io.Serializable;
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
@Table(name="COURSE_REGISTRATION_WITHDRAW", schema="ACADEMICS")
public class CourseRegistrationWithdrawModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private CourseRegistrationWithdrawPKModel crwPKId;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID", referencedColumnName="SEMESTER_SUB_ID", 
					insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="STDNTSLGNDTLS_REGISTER_NUMBER", referencedColumnName="REG_NO", 
					insertable = false, updatable = false)
	private StudentsLoginDetailsModel studentsLoginDetails;
	
	@Column(name="COURSE_ALLOCATION_CLASS_ID")
	private String classId;
	
	@ManyToOne
	@JoinColumn(name="COURSE_ALLOCATION_CLASS_ID", referencedColumnName="CLASS_ID", 
					insertable = false, updatable = false)
	private CourseAllocationModel courseAllocationModel;
		
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", referencedColumnName="COURSE_ID", 
					insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
		
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", referencedColumnName="COURSE_TYPE", 
					insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="COURSE_OPTION_MASTER_CODE")
	private String courseOptionCode;
	
	@ManyToOne
	@JoinColumn(name="COURSE_OPTION_MASTER_CODE", referencedColumnName="CODE", 
					insertable = false, updatable = false)
	private CourseOptionModel courseOptionModel;
	
	@Column(name="RGSTRTNSTSMSTR_STATUS_NUMBER")
	private int statusNumber;
	
	@ManyToOne
	@JoinColumn(name="RGSTRTNSTSMSTR_STATUS_NUMBER", referencedColumnName="STATUS_NUMBER", 
					insertable = false, updatable = false)
	private RegistrationStatusMasterModel registrationStatusMasterModel;
	
	@Column(name="REGISTRATION_COMPONENT_TYPE")
	private int componentType;
	
	@Column(name="INVOICES_INVOICE_NUMBER")
	private String invoiceNumber;
	
	@Column(name="INTERNAL_FOILCARD_NUMBER")
	private Long internalFoilcardNumber;
	
	@Column(name="EXTERNAL_FOILCARD_NUMBER")
	private Long externalFoilcardNumber;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	
	@Column(name="WITHDRAWN_LOG_USERID")
	private String withdrawnLogUserId;
	
	@Column(name="WITHDRAWN_LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date withdrawnLogTimestamp;
	
	@Column(name="WITHDRAWN_LOG_IPADDRESS")
	private String withdrawnLogIpaddress;

	public CourseRegistrationWithdrawPKModel getCrwPKId() {
		return crwPKId;
	}

	public void setCrwPKId(CourseRegistrationWithdrawPKModel crwPKId) {
		this.crwPKId = crwPKId;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public StudentsLoginDetailsModel getStudentsLoginDetails() {
		return studentsLoginDetails;
	}

	public void setStudentsLoginDetails(StudentsLoginDetailsModel studentsLoginDetails) {
		this.studentsLoginDetails = studentsLoginDetails;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public CourseAllocationModel getCourseAllocationModel() {
		return courseAllocationModel;
	}

	public void setCourseAllocationModel(CourseAllocationModel courseAllocationModel) {
		this.courseAllocationModel = courseAllocationModel;
	}

	public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public String getCourseOptionCode() {
		return courseOptionCode;
	}

	public void setCourseOptionCode(String courseOptionCode) {
		this.courseOptionCode = courseOptionCode;
	}

	public CourseOptionModel getCourseOptionModel() {
		return courseOptionModel;
	}

	public void setCourseOptionModel(CourseOptionModel courseOptionModel) {
		this.courseOptionModel = courseOptionModel;
	}

	public int getStatusNumber() {
		return statusNumber;
	}

	public void setStatusNumber(int statusNumber) {
		this.statusNumber = statusNumber;
	}

	public RegistrationStatusMasterModel getRegistrationStatusMasterModel() {
		return registrationStatusMasterModel;
	}

	public void setRegistrationStatusMasterModel(RegistrationStatusMasterModel registrationStatusMasterModel) {
		this.registrationStatusMasterModel = registrationStatusMasterModel;
	}

	public int getComponentType() {
		return componentType;
	}

	public void setComponentType(int componentType) {
		this.componentType = componentType;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getInternalFoilcardNumber() {
		return internalFoilcardNumber;
	}

	public void setInternalFoilcardNumber(Long internalFoilcardNumber) {
		this.internalFoilcardNumber = internalFoilcardNumber;
	}

	public Long getExternalFoilcardNumber() {
		return externalFoilcardNumber;
	}

	public void setExternalFoilcardNumber(Long externalFoilcardNumber) {
		this.externalFoilcardNumber = externalFoilcardNumber;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
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

	public String getWithdrawnLogUserId() {
		return withdrawnLogUserId;
	}

	public void setWithdrawnLogUserId(String withdrawnLogUserId) {
		this.withdrawnLogUserId = withdrawnLogUserId;
	}

	public Date getWithdrawnLogTimestamp() {
		return withdrawnLogTimestamp;
	}

	public void setWithdrawnLogTimestamp(Date withdrawnLogTimestamp) {
		this.withdrawnLogTimestamp = withdrawnLogTimestamp;
	}

	public String getWithdrawnLogIpaddress() {
		return withdrawnLogIpaddress;
	}

	public void setWithdrawnLogIpaddress(String withdrawnLogIpaddress) {
		this.withdrawnLogIpaddress = withdrawnLogIpaddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crwPKId == null) ? 0 : crwPKId.hashCode());
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
		CourseRegistrationWithdrawModel other = (CourseRegistrationWithdrawModel) obj;
		if (crwPKId == null) {
			if (other.crwPKId != null)
				return false;
		} else if (!crwPKId.equals(other.crwPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseRegistrationWithdrawModel [crwPKId=" + crwPKId + ", semesterDetailsModel=" + semesterDetailsModel
				+ ", studentsLoginDetails=" + studentsLoginDetails + ", classId=" + classId + ", courseAllocationModel="
				+ courseAllocationModel + ", courseCatalogModel=" + courseCatalogModel + ", courseTypeComponentModel="
				+ courseTypeComponentModel + ", courseOptionCode=" + courseOptionCode + ", courseOptionModel="
				+ courseOptionModel + ", statusNumber=" + statusNumber + ", registrationStatusMasterModel="
				+ registrationStatusMasterModel + ", componentType=" + componentType + ", invoiceNumber="
				+ invoiceNumber + ", internalFoilcardNumber=" + internalFoilcardNumber + ", externalFoilcardNumber="
				+ externalFoilcardNumber + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + ", withdrawnLogUserId=" + withdrawnLogUserId
				+ ", withdrawnLogTimestamp=" + withdrawnLogTimestamp + ", withdrawnLogIpaddress="
				+ withdrawnLogIpaddress + "]";
	}
	
}
