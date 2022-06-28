package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="CourseAllocation")
public class CourseAllocation 
{
	@Id
	private String id;
	
	private String semesterSubId;
	private String classId;
	private String classGroupId;
	private String classType;
	private String courseId;
	private String courseCode;
	private String courseTitle;
	private String courseType;
	private String roomNumber;
	private long slotId;
	private String slot;
	private String clashSlot;
	private String employeeId;
	private String employeeName;
	private int	classOption;
	private String specializationBatch;
	private int	combineClassStatus;
	private String combineClassId;
	private String associateClassId;
	private int	totalSeat;
	private int	registeredSeat;
	private int	waitingSeat;
	private int	status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSemesterSubId() {
		return semesterSubId;
	}
	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassGroupId() {
		return classGroupId;
	}
	public void setClassGroupId(String classGroupId) {
		this.classGroupId = classGroupId;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public long getSlotId() {
		return slotId;
	}
	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getClashSlot() {
		return clashSlot;
	}
	public void setClashSlot(String clashSlot) {
		this.clashSlot = clashSlot;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getClassOption() {
		return classOption;
	}
	public void setClassOption(int classOption) {
		this.classOption = classOption;
	}
	public String getSpecializationBatch() {
		return specializationBatch;
	}
	public void setSpecializationBatch(String specializationBatch) {
		this.specializationBatch = specializationBatch;
	}
	public int getCombineClassStatus() {
		return combineClassStatus;
	}
	public void setCombineClassStatus(int combineClassStatus) {
		this.combineClassStatus = combineClassStatus;
	}
	public String getCombineClassId() {
		return combineClassId;
	}
	public void setCombineClassId(String combineClassId) {
		this.combineClassId = combineClassId;
	}
	public String getAssociateClassId() {
		return associateClassId;
	}
	public void setAssociateClassId(String associateClassId) {
		this.associateClassId = associateClassId;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public int getRegisteredSeat() {
		return registeredSeat;
	}
	public void setRegisteredSeat(int registeredSeat) {
		this.registeredSeat = registeredSeat;
	}
	public int getWaitingSeat() {
		return waitingSeat;
	}
	public void setWaitingSeat(int waitingSeat) {
		this.waitingSeat = waitingSeat;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CourseAllocation [id=" + id + ", semesterSubId=" + semesterSubId + ", classId=" + classId
				+ ", classGroupId=" + classGroupId + ", classType=" + classType + ", courseId=" + courseId
				+ ", courseCode=" + courseCode + ", courseTitle=" + courseTitle + ", courseType=" + courseType
				+ ", roomNumber=" + roomNumber + ", slotId=" + slotId + ", slot=" + slot + ", clashSlot=" + clashSlot
				+ ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", classOption=" + classOption
				+ ", specializationBatch=" + specializationBatch + ", combineClassStatus=" + combineClassStatus
				+ ", combineClassId=" + combineClassId + ", associateClassId=" + associateClassId + ", totalSeat="
				+ totalSeat + ", registeredSeat=" + registeredSeat + ", waitingSeat=" + waitingSeat + ", status="
				+ status + "]";
	}	
}

