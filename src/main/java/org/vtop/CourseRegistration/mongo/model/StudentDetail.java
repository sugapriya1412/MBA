package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="StudentDetail")
public class StudentDetail 
{
	@Id
	private String id;
	
	private	String registerNumber;
	private	String nickName;
	private	long applicationNumber;
	private	String studentName;
	private	String gender;
	private	int progSpecializationId;
	private	String progSpecializationCode;
	private	String progSpecializationDescription;
	private	int progGroupId;
	private	String progGroupCode;
	private	String progGroupDescription;
	private	String progGroupMode;
	private	int	progGroupDuration;
	private	String progGroupLevel;
	private	int	centreId;
	private	String centreCode;
	private	String centreDescription;
	private	int	admissionYear;
	private	String studySystem;
	private	String password;
	private	String educationStatus;
	private	String educationStatusDescription;
	private	int	lockStatus;
	private	String email;
	private	String mobile;
	private	int	feeId;
	private	String feeCategoryDescription;
		
	public String gsetId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getProgSpecializationId() {
		return progSpecializationId;
	}
	public void setProgSpecializationId(int progSpecializationId) {
		this.progSpecializationId = progSpecializationId;
	}
	public String getProgSpecializationCode() {
		return progSpecializationCode;
	}
	public void setProgSpecializationCode(String progSpecializationCode) {
		this.progSpecializationCode = progSpecializationCode;
	}
	public String getProgSpecializationDescription() {
		return progSpecializationDescription;
	}
	public void setProgSpecializationDescription(String progSpecializationDescription) {
		this.progSpecializationDescription = progSpecializationDescription;
	}
	public int getProgGroupId() {
		return progGroupId;
	}
	public void setProgGroupId(int progGroupId) {
		this.progGroupId = progGroupId;
	}
	public String getProgGroupCode() {
		return progGroupCode;
	}
	public void setProgGroupCode(String progGroupCode) {
		this.progGroupCode = progGroupCode;
	}
	public String getProgGroupDescription() {
		return progGroupDescription;
	}
	public void setProgGroupDescription(String progGroupDescription) {
		this.progGroupDescription = progGroupDescription;
	}
	public String getProgGroupMode() {
		return progGroupMode;
	}
	public void setProgGroupMode(String progGroupMode) {
		this.progGroupMode = progGroupMode;
	}
	public int getProgGroupDuration() {
		return progGroupDuration;
	}
	public void setProgGroupDuration(int progGroupDuration) {
		this.progGroupDuration = progGroupDuration;
	}
	public String getProgGroupLevel() {
		return progGroupLevel;
	}
	public void setProgGroupLevel(String progGroupLevel) {
		this.progGroupLevel = progGroupLevel;
	}
	public int getCentreId() {
		return centreId;
	}
	public void setCentreId(int centreId) {
		this.centreId = centreId;
	}
	public String getCentreCode() {
		return centreCode;
	}
	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}
	public String getCentreDescription() {
		return centreDescription;
	}
	public void setCentreDescription(String centreDescription) {
		this.centreDescription = centreDescription;
	}
	public int getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}
	public String getStudySystem() {
		return studySystem;
	}
	public void setStudySystem(String studySystem) {
		this.studySystem = studySystem;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEducationStatus() {
		return educationStatus;
	}
	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}
	public String getEducationStatusDescription() {
		return educationStatusDescription;
	}
	public void setEducationStatusDescription(String educationStatusDescription) {
		this.educationStatusDescription = educationStatusDescription;
	}
	public int getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public String getFeeCategoryDescription() {
		return feeCategoryDescription;
	}
	public void setFeeCategoryDescription(String feeCategoryDescription) {
		this.feeCategoryDescription = feeCategoryDescription;
	}
		
	@Override
	public String toString()
	{
		return "StudentDetail [id=" + id + ", registerNumber=" + registerNumber + ", nickName=" + nickName
					+ ", applicationNumber=" + applicationNumber + ", studentName=" + studentName + ", gender=" + gender
					+ ", progSpecializationId=" + progSpecializationId + ", progSpecializationCode="
					+ progSpecializationCode + ", progSpecializationDescription=" + progSpecializationDescription
					+ ", progGroupId=" + progGroupId + ", progGroupCode=" + progGroupCode + ", progGroupDescription="
					+ progGroupDescription + ", progGroupMode=" + progGroupMode + ", progGroupDuration="
					+ progGroupDuration + ", progGroupLevel=" + progGroupLevel + ", centreId=" + centreId
					+ ", centreCode=" + centreCode + ", centreDescription=" + centreDescription + ", admissionYear="
					+ admissionYear + ", studySystem=" + studySystem + ", password=" + password + ", educationStatus="
					+ educationStatus + ", educationStatusDescription=" + educationStatusDescription + ", lockStatus="
					+ lockStatus + ", email=" + email + ", mobile=" + mobile + ", feeId=" + feeId
					+ ", feeCategoryDescription=" + feeCategoryDescription + "]";
	}
}
