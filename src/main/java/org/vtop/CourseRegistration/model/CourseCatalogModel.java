package org.vtop.CourseRegistration.model;

import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COURSE_CATALOG", schema="academics")
public class CourseCatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COURSE_ID")
	private String courseId;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="COURSE_VERSION")
	private float courseVersion;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="GENERIC_COURSE_TYPE")
	private String genericCourseType;
	
	@ManyToOne
	@JoinColumn(name="GENERIC_COURSE_TYPE", referencedColumnName="COURSE_TYPE", 
					insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="LECTURE_HOURS")
	private int lectureHours;
	
	@Column(name="TUTORIAL_HOURS")
	private int tutorialHours;
	
	@Column(name="PRACTICAL_HOURS")
	private int practicalHours;
	
	@Column(name="PROJECT_HOURS")
	private int projectHours;
	
	@Column(name="LECTURE_CREDITS")
	private float lectureCredits;
	
	@Column(name="PRACTICAL_CREDITS")
	private float practicalCredits;
	
	@Column(name="PROJECT_CREDITS")
	private float projectCredits;
	
	@Column(name="CREDITS")
	private float credits;
	
	@Column(name="PREREQUISITE")
	private String prerequisite;
	
	@Column(name="ANTIREQUISITE")
	private String antirequisite;
	
	@Column(name="COREQUISITE")
	private String corequisite;
	
	@Column(name="COURSE_SYSTEM")
	private String courseSystem;
	
	@Column(name="EVALUATION_TYPE")
	private String evaluationType;
	
	@Column(name="COUNCIL_COUNCIL_NUMBER")
	private long councilNumber;
	
	@Column(name="PAGE_NUMBER")
	private long pageNumber;
	
	@Column(name="DISCIPLINE")
	private String discipline;
	
	@Column(name="PRGRM_GROUP_PROGRAMME_GROUP_ID")
	private Integer groupId;
	
	@ManyToOne
	@JoinColumn(name="PRGRM_GROUP_PROGRAMME_GROUP_ID", referencedColumnName="PROGRAMME_GROUP_ID", 
				insertable = false, updatable = false)
	private ProgrammeGroupModel programmeGroupModel;
		
	@Column(name="PROGRAMME_GROUP_CODE")
	private String groupCode;
	
	@Column(name="OWNER_SCHOOL_CODE")
	private String ownerCode;
			
	@Column(name="CAMPUS_CODE")
	private String campusCode;
	
	@MapsId("campusCode")
	@ManyToOne
	@JoinColumn(name="CAMPUS_CODE", insertable = false, updatable = false)
	private Campus campusModel;
		
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;
	
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getCourseVersion() {
		return courseVersion;
		//return String.format ("%3.2f", courseVersion);
	}

	public void setCourseVersion(float courseVersion) {
		this.courseVersion = courseVersion;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenericCourseType() {
		return genericCourseType;
	}

	public void setGenericCourseType(String genericCourseType) {
		this.genericCourseType = genericCourseType;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public int getLectureHours() {
		return lectureHours;
	}

	public void setLectureHours(int lectureHours) {
		this.lectureHours = lectureHours;
	}

	public int getTutorialHours() {
		return tutorialHours;
	}

	public void setTutorialHours(int tutorialHours) {
		this.tutorialHours = tutorialHours;
	}

	public int getPracticalHours() {
		return practicalHours;
	}

	public void setPracticalHours(int practicalHours) {
		this.practicalHours = practicalHours;
	}

	public int getProjectHours() {
		return projectHours;
	}

	public void setProjectHours(int projectHours) {
		this.projectHours = projectHours;
	}

	public float getLectureCredits() {
		return lectureCredits;
	}

	public void setLectureCredits(float lectureCredits) {
		this.lectureCredits = lectureCredits;
	}

	public float getPracticalCredits() {
		return practicalCredits;
	}

	public void setPracticalCredits(float practicalCredits) {
		this.practicalCredits = practicalCredits;
	}

	public float getProjectCredits() {
		return projectCredits;
	}

	public void setProjectCredits(float projectCredits) {
		this.projectCredits = projectCredits;
	}

	public float getCredits() {
		return credits;
	}

	public void setCredits(float credits) {
		this.credits = credits;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getAntirequisite() {
		return antirequisite;
	}

	public void setAntirequisite(String antirequisite) {
		this.antirequisite = antirequisite;
	}

	public String getCorequisite() {
		return corequisite;
	}

	public void setCorequisite(String corequisite) {
		this.corequisite = corequisite;
	}

	public String getCourseSystem() {
		return courseSystem;
	}

	public void setCourseSystem(String courseSystem) {
		this.courseSystem = courseSystem;
	}

	public String getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public long getCouncilNumber() {
		return councilNumber;
	}

	public void setCouncilNumber(long councilNumber) {
		this.councilNumber = councilNumber;
	}

	public long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
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
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public ProgrammeGroupModel getProgrammeGroupModel() {
		return programmeGroupModel;
	}

	public void setProgrammeGroupModel(ProgrammeGroupModel programmeGroupModel) {
		this.programmeGroupModel = programmeGroupModel;
	}

	@Override
	public String toString() {
		return "CourseCatalogModel [courseId=" + courseId + ", code=" + code + ", courseVersion=" + courseVersion
				+ ", title=" + title + ", genericCourseType=" + genericCourseType + ", courseTypeComponentModel="
				+ courseTypeComponentModel + ", lectureHours=" + lectureHours + ", tutorialHours=" + tutorialHours
				+ ", practicalHours=" + practicalHours + ", projectHours=" + projectHours + ", lectureCredits="
				+ lectureCredits + ", practicalCredits=" + practicalCredits + ", projectCredits=" + projectCredits
				+ ", credits=" + credits + ", prerequisite=" + prerequisite + ", antirequisite=" + antirequisite
				+ ", corequisite=" + corequisite + ", courseSystem=" + courseSystem + ", evaluationType="
				+ evaluationType + ", councilNumber=" + councilNumber + ", pageNumber=" + pageNumber + ", discipline="
				+ discipline + ", groupId=" + groupId + ", programmeGroupModel=" + programmeGroupModel + ", groupCode="
				+ groupCode + ", ownerCode=" + ownerCode + ", campusCode=" + campusCode + ", campusModel=" + campusModel
				+ ", status=" + status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp
				+ ", logIpaddress=" + logIpaddress + "]";
	}
}
