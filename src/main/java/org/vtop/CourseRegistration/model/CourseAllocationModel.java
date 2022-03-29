package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="COURSE_ALLOCATION", schema="ACADEMICS")
public class CourseAllocationModel {

	@Id
	@Column(name="CLASS_ID")
	private String classId;
	
	@Column(name="SEMSTR_DETAILS_SEMESTER_SUB_ID")
	private String semesterSubId;
		
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;

	@Column(name="CRSTYPCMPNTMASTER_COURSE_TYPE")
	private String courseType;
		
	@Column(name="TIME_TABLE_SLOT_ID")
	private Long slotId;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID", referencedColumnName="SEMESTER_SUB_ID", 
					insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", referencedColumnName="COURSE_ID", 
					insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
	
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", referencedColumnName="COURSE_TYPE", 
						insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@ManyToOne
	@JoinColumn(name="TIME_TABLE_SLOT_ID", referencedColumnName="SLOT_ID", insertable = false, updatable = false)
	private TimeTableModel timeTableModel;
	
	/*@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID",insertable = false, updatable = false),
		@JoinColumn(name="COURSE_CATALOG_COURSE_ID",insertable = false, updatable = false),
		@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE",insertable = false, updatable = false),
		@JoinColumn(name="TIME_TABLE_SLOT_ID",insertable = false, updatable = false)
	})
	private CourseSchedulingModel courseSchedulingModel;*/
				
	@Column(name="BUILDING_MASTER_BUILDING_ID")
	private Long buildingMasterBuildingId;
			
	@Column(name="ROOM_MASTER_ROOM_NUMBER")
	private String roomMasterRoomNumber;
	
	@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name="BUILDING_MASTER_BUILDING_ID", referencedColumnName="BUILDING_MASTER_BUILDING_ID", 
					insertable = false, updatable = false),
		@JoinColumn(name="ROOM_MASTER_ROOM_NUMBER", referencedColumnName="ROOM_NUMBER", insertable = false, updatable = false)
	})
	private RoomMaster roomMaster;
			
	@Column(name="ERP_ID")
	private String erpId;
	
	@ManyToOne
	@JoinColumn(name="ERP_ID", referencedColumnName="EMPLOYEE_ID", insertable = false, updatable = false)
	private EmployeeProfile employeeProfile;
	
	@Column(name="COURSE_MODE_MASTER_CODE")
	private String courseModeMasterCode;
	
	@ManyToOne
	@JoinColumn(name="COURSE_MODE_MASTER_CODE", referencedColumnName="CODE", insertable = false, updatable = false)
	private CourseModeMasterModel courseModeMasterModel;
	
	@Column(name="CLSSGRP_MASTER_CLASS_GROUP_ID")
	private String clsGrpMasterGroupId;
	
	@ManyToOne
	@JoinColumn(name="CLSSGRP_MASTER_CLASS_GROUP_ID", referencedColumnName="CLASS_GROUP_ID", insertable = false, updatable = false)
	private ClassGroupModel classGroupModel;
	
	@Column(name="SPECIALIZATION_BATCH")
	private String specializationBatch;
	
	@Column(name="ARREAR_REGISTERED_SEATS")
	private Integer arrearRegisteredSeats;
		
	@Column(name="DUMMY_NUMBER_STATUS")
	private Integer dummyNumberStatus;
	
	@Column(name="ASSO_CLASS_ID")
	private String assoClassId;
	
	//0 - No/ 1- Yes
	@Column(name="COMBINE_CLASS_STATUS")
	private Integer combineClassStatus;
		
	@Column(name="COMBINE_CLASS_ID")
	private String combineClassId;
	
	@Column(name="LOCK_STATUS")
	private Integer lockStatus;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddress;
	
	@Column(name="TOTAL_SEATS")
	private Integer totalSeats;
	
	@Column(name="CLASS_TYPE")
	private String classType;
		
	@Column(name="REGISTERED_SEATS")
	private Integer registeredSeats;
	
	@Column(name="WAITING_SEATS")
	private Integer waitingSeats;
	
	@Column(name="CLASS_OPTION")
	private Integer classOption;
	
	@Transient
	private Integer availableSeats;
	
	@Transient
	private Integer availableWaitingSeats;
	
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSemesterSubId() {
		return semesterSubId;
	}

	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
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

	public TimeTableModel getTimeTableModel() {
		return timeTableModel;
	}

	public void setTimeTableModel(TimeTableModel timeTableModel) {
		this.timeTableModel = timeTableModel;
	}

	public Long getBuildingMasterBuildingId() {
		return buildingMasterBuildingId;
	}

	public void setBuildingMasterBuildingId(Long buildingMasterBuildingId) {
		this.buildingMasterBuildingId = buildingMasterBuildingId;
	}

	public String getRoomMasterRoomNumber() {
		return roomMasterRoomNumber;
	}

	public void setRoomMasterRoomNumber(String roomMasterRoomNumber) {
		this.roomMasterRoomNumber = roomMasterRoomNumber;
	}

	public RoomMaster getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(RoomMaster roomMaster) {
		this.roomMaster = roomMaster;
	}

	public String getErpId() {
		return erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	public String getCourseModeMasterCode() {
		return courseModeMasterCode;
	}

	public void setCourseModeMasterCode(String courseModeMasterCode) {
		this.courseModeMasterCode = courseModeMasterCode;
	}

	public CourseModeMasterModel getCourseModeMasterModel() {
		return courseModeMasterModel;
	}

	public void setCourseModeMasterModel(CourseModeMasterModel courseModeMasterModel) {
		this.courseModeMasterModel = courseModeMasterModel;
	}

	public String getClsGrpMasterGroupId() {
		return clsGrpMasterGroupId;
	}

	public void setClsGrpMasterGroupId(String clsGrpMasterGroupId) {
		this.clsGrpMasterGroupId = clsGrpMasterGroupId;
	}

	public ClassGroupModel getClassGroupModel() {
		return classGroupModel;
	}

	public void setClassGroupModel(ClassGroupModel classGroupModel) {
		this.classGroupModel = classGroupModel;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Integer getRegisteredSeats() {
		return registeredSeats;
	}

	public void setRegisteredSeats(Integer registeredSeats) {
		this.registeredSeats = registeredSeats;
	}

	public Integer getWaitingSeats() {
		return waitingSeats;
	}

	public void setWaitingSeats(Integer waitingSeats) {
		this.waitingSeats = waitingSeats;
	}

	public String getSpecializationBatch() {
		return specializationBatch;
	}

	public void setSpecializationBatch(String specializationBatch) {
		this.specializationBatch = specializationBatch;
	}

	public Integer getArrearRegisteredSeats() {
		return arrearRegisteredSeats;
	}

	public void setArrearRegisteredSeats(Integer arrearRegisteredSeats) {
		this.arrearRegisteredSeats = arrearRegisteredSeats;
	}

	public Integer getDummyNumberStatus() {
		return dummyNumberStatus;
	}

	public void setDummyNumberStatus(Integer dummyNumberStatus) {
		this.dummyNumberStatus = dummyNumberStatus;
	}

	public String getAssoClassId() {
		return assoClassId;
	}

	public void setAssoClassId(String assoClassId) {
		this.assoClassId = assoClassId;
	}
	
	public Integer getCombineClassStatus() {
		return combineClassStatus;
	}

	public void setCombineClassStatus(Integer combineClassStatus) {
		this.combineClassStatus = combineClassStatus;
	}

	public String getCombineClassId() {
		return combineClassId;
	}

	public void setCombineClassId(String combineClassId) {
		this.combineClassId = combineClassId;
	}

	public Integer getClassOption() {
		return classOption;
	}

	public void setClassOption(Integer classOption) {
		this.classOption = classOption;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
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
	
	public Integer getAvailableSeats() {
		
		availableSeats =  this.totalSeats - this.registeredSeats;
		
		if (availableSeats <= 0){
			availableSeats = 0;
		}
		
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Integer getAvailableWaitingSeats() {
		
		//availableWaitingSeats = 60 - this.waitingSeats;
		availableWaitingSeats = 10 - this.waitingSeats;
		
		if (availableWaitingSeats <= 0){
			availableWaitingSeats = 0;
		}		
		
		return availableWaitingSeats;
	}

	public void setAvailableWaitingSeats(Integer availableWaitingSeats) {
		
		this.availableWaitingSeats = availableWaitingSeats;
	}

	@Override
	public String toString() {
		return "CourseAllocationModel [classId=" + classId + ", semesterSubId=" + semesterSubId + ", courseId="
				+ courseId + ", courseType=" + courseType + ", slotId=" + slotId + ", semesterDetailsModel="
				+ semesterDetailsModel + ", courseCatalogModel=" + courseCatalogModel + ", courseTypeComponentModel="
				+ courseTypeComponentModel + ", timeTableModel=" + timeTableModel + ", buildingMasterBuildingId="
				+ buildingMasterBuildingId + ", roomMasterRoomNumber=" + roomMasterRoomNumber + ", roomMaster="
				+ roomMaster + ", erpId=" + erpId + ", employeeProfile=" + employeeProfile + ", courseModeMasterCode="
				+ courseModeMasterCode + ", courseModeMasterModel=" + courseModeMasterModel + ", clsGrpMasterGroupId="
				+ clsGrpMasterGroupId + ", classGroupModel=" + classGroupModel + ", specializationBatch="
				+ specializationBatch + ", arrearRegisteredSeats=" + arrearRegisteredSeats + ", dummyNumberStatus="
				+ dummyNumberStatus + ", assoClassId=" + assoClassId + ", combineClassStatus=" + combineClassStatus
				+ ", combineClassId=" + combineClassId + ", lockStatus=" + lockStatus + ", logUserId=" + logUserId
				+ ", logTimeStamp=" + logTimeStamp + ", logIpAddress=" + logIpAddress + ", totalSeats=" + totalSeats
				+ ", classType=" + classType + ", registeredSeats=" + registeredSeats + ", waitingSeats=" + waitingSeats
				+ ", classOption=" + classOption + ", availableSeats=" + availableSeats + ", availableWaitingSeats="
				+ availableWaitingSeats + "]";
	}
}
