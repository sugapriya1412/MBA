/*package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT_REGISTRATION", schema="ACADEMICS")
public class ProjectRegistrationModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private ProjectRegistrationPKModel projectRegistrationPKId;
	
	@Column(name="SEMSTR_DETAILS_SEMESTER_SUB_ID")
	private String semesterSubId;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
	
	@Column(name="CRSTYPCMPNTMASTER_COURSE_TYPE")
	private String courseType;
	
	@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID",insertable = false, updatable = false),
		@JoinColumn(name="STDNTSLGNDTLS_REGISTER_NUMBER",insertable = false, updatable = false),
		@JoinColumn(name="COURSE_CATALOG_COURSE_ID",insertable = false, updatable = false),
		@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE",insertable = false, updatable = false)
	})
	private CourseRegistrationModel courseRegistrationModel;
		
	@Column(name="TIME_TABLE_SLOT_ID")
	private Integer slotId;
	
	@Column(name="PROJECT_TITLE")
	private String projectTitle;
	
	@Column(name="GUIDE_ERPID")
	private String guideErpid;
	
	@Column(name="PROJECT_DURATION")
	private int projectDuration;
	
	@Column(name="RESULT_SEMESTER_SUB_ID")
	private String resultSemesterSubId;
		
	@Column(name="INTERNAL_FOILCARD_NUMBER")
	private Long internalFoilcardNumber;
	
	@Column(name="EXTERNAL_FOILCARD_NUMBER")
	private Long externalFoilcardNumber;

	public ProjectRegistrationPKModel getProjectRegistrationPKId() {
		return projectRegistrationPKId;
	}

	public void setProjectRegistrationPKId(ProjectRegistrationPKModel projectRegistrationPKId) {
		this.projectRegistrationPKId = projectRegistrationPKId;
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

	public CourseRegistrationModel getCourseRegistrationModel() {
		return courseRegistrationModel;
	}

	public void setCourseRegistrationModel(CourseRegistrationModel courseRegistrationModel) {
		this.courseRegistrationModel = courseRegistrationModel;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getGuideErpid() {
		return guideErpid;
	}

	public void setGuideErpid(String guideErpid) {
		this.guideErpid = guideErpid;
	}

	public int getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}

	public String getResultSemesterSubId() {
		return resultSemesterSubId;
	}

	public void setResultSemesterSubId(String resultSemesterSubId) {
		this.resultSemesterSubId = resultSemesterSubId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectRegistrationPKId == null) ? 0 : projectRegistrationPKId.hashCode());
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
		ProjectRegistrationModel other = (ProjectRegistrationModel) obj;
		if (projectRegistrationPKId == null) {
			if (other.projectRegistrationPKId != null)
				return false;
		} else if (!projectRegistrationPKId.equals(other.projectRegistrationPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectRegistrationModel [projectRegistrationPKId=" + projectRegistrationPKId + ", semesterSubId="
				+ semesterSubId + ", courseId=" + courseId + ", courseType=" + courseType + ", courseRegistrationModel="
				+ courseRegistrationModel + ", slotId=" + slotId + ", projectTitle=" + projectTitle + ", guideErpid="
				+ guideErpid + ", projectDuration=" + projectDuration + ", resultSemesterSubId=" + resultSemesterSubId
				+ ", internalFoilcardNumber=" + internalFoilcardNumber + ", externalFoilcardNumber="
				+ externalFoilcardNumber + "]";
	}
	
}
*/
package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*import org.vtop.academics.common.model.CourseTypeMasterModel;
import org.vtop.academics.course.model.CourseCatalogModel;
import org.vtop.academics.course.model.CourseTypeComponentModel;
import org.vtop.common.academics2.model.SemesterDetailsModel;
import org.vtop.common.admission.model.StudentsLoginDetails;
import org.vtop.common.hrms.model.EmployeeProfile;*/

@Entity
@Table(name="PROJECT_REGISTRATION", schema="ACADEMICS")
public class ProjectRegistrationModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private ProjectRegistrationPKModel projectRegistrationPKId;
	
	@ManyToOne
	@JoinColumn(name="STDNTSLGNDTLS_REGISTER_NUMBER" ,insertable = false, updatable = false)
	private StudentsLoginDetailsModel studentLoginDetials;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID",insertable = false, updatable = false)
	private SemesterDetailsModel semesterDetailsModel;
	
	@Column(name="SEMSTR_DETAILS_SEMESTER_SUB_ID")
	private String semesterSubId;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID",insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
	
	@ManyToOne
	@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE",insertable = false, updatable = false)
	private CourseTypeComponentModel courseTypeComponentModel;
	
	@Column(name="CRSTYPCMPNTMASTER_COURSE_TYPE")
	private String courseType;
	
	/*@ManyToOne
	@JoinColumns({ 
		@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID", referencedColumnName="SEMSTR_DETAILS_SEMESTER_SUB_ID",
						insertable = false, updatable = false),
		@JoinColumn(name="STDNTSLGNDTLS_REGISTER_NUMBER", referencedColumnName="STDNTSLGNDTLS_REGISTER_NUMBER", 
						insertable = false, updatable = false),
		@JoinColumn(name="COURSE_CATALOG_COURSE_ID", referencedColumnName="COURSE_CATALOG_COURSE_ID", 
						insertable = false, updatable = false),
		@JoinColumn(name="CRSTYPCMPNTMASTER_COURSE_TYPE", referencedColumnName="CRSTYPCMPNTMASTER_COURSE_TYPE", 
						insertable = false, updatable = false)
	})
	private CourseRegistrationModel courseRegistrationModel;*/
		
	@Column(name="TIME_TABLE_SLOT_ID")
	private Integer slotId;
	
	@Column(name="PROJECT_TITLE")
	private String projectTitle;
	
	@Column(name="GUIDE_ERPID")
	private String guideErpid;
	
	@ManyToOne
	@JoinColumn(name="GUIDE_ERPID", referencedColumnName="EMPLOYEE_ID", insertable = false, updatable = false)
	private EmployeeProfile employeeProfile;
	
	@Column(name="PROJECT_DURATION")
	private int projectDuration;
	
	@Column(name="RESULT_SEMESTER_SUB_ID")
	private String resultSemesterSubId;
		
	@Column(name="INTERNAL_FOILCARD_NUMBER")
	private Long internalFoilcardNumber;
	
	@Column(name="EXTERNAL_FOILCARD_NUMBER")
	private Long externalFoilcardNumber;
	
	@Column(name="PROJECT_OPTION")
	private String projectOption;

	public ProjectRegistrationPKModel getProjectRegistrationPKId() {
		return projectRegistrationPKId;
	}

	public void setProjectRegistrationPKId(ProjectRegistrationPKModel projectRegistrationPKId) {
		this.projectRegistrationPKId = projectRegistrationPKId;
	}

	public StudentsLoginDetailsModel getStudentLoginDetials() {
		return studentLoginDetials;
	}

	public void setStudentLoginDetials(StudentsLoginDetailsModel studentLoginDetials) {
		this.studentLoginDetials = studentLoginDetials;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public String getSemesterSubId() {
		return semesterSubId;
	}

	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}

	public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getGuideErpid() {
		return guideErpid;
	}

	public void setGuideErpid(String guideErpid) {
		this.guideErpid = guideErpid;
	}

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	public int getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}

	public String getResultSemesterSubId() {
		return resultSemesterSubId;
	}

	public void setResultSemesterSubId(String resultSemesterSubId) {
		this.resultSemesterSubId = resultSemesterSubId;
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

	public String getProjectOption() {
		return projectOption;
	}

	public void setProjectOption(String projectOption) {
		this.projectOption = projectOption;
	}

	@Override
	public String toString() {
		return "ProjectRegistrationModel [projectRegistrationPKId=" + projectRegistrationPKId + ", studentLoginDetials="
				+ studentLoginDetials + ", semesterDetailsModel=" + semesterDetailsModel + ", semesterSubId="
				+ semesterSubId + ", courseCatalogModel=" + courseCatalogModel + ", courseId=" + courseId
				+ ", courseTypeComponentModel=" + courseTypeComponentModel + ", courseType=" + courseType + ", slotId="
				+ slotId + ", projectTitle=" + projectTitle + ", guideErpid=" + guideErpid + ", employeeProfile="
				+ employeeProfile + ", projectDuration=" + projectDuration + ", resultSemesterSubId="
				+ resultSemesterSubId + ", internalFoilcardNumber=" + internalFoilcardNumber
				+ ", externalFoilcardNumber=" + externalFoilcardNumber + ", projectOption=" + projectOption + "]";
	}
	
	

	/*public ProjectRegistrationPKModel getProjectRegistrationPKId() {
		return projectRegistrationPKId;
	}

	public void setProjectRegistrationPKId(ProjectRegistrationPKModel projectRegistrationPKId) {
		this.projectRegistrationPKId = projectRegistrationPKId;
	}

	public StudentsLoginDetailsModel getStudentLoginDetials() {
		return studentLoginDetials;
	}

	public void setStudentLoginDetials(StudentsLoginDetailsModel studentLoginDetials) {
		this.studentLoginDetials = studentLoginDetials;
	}

	public SemesterDetailsModel getSemesterDetailsModel() {
		return semesterDetailsModel;
	}

	public void setSemesterDetailsModel(SemesterDetailsModel semesterDetailsModel) {
		this.semesterDetailsModel = semesterDetailsModel;
	}

	public String getSemesterSubId() {
		return semesterSubId;
	}

	public void setSemesterSubId(String semesterSubId) {
		this.semesterSubId = semesterSubId;
	}

	public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public CourseTypeComponentModel getCourseTypeComponentModel() {
		return courseTypeComponentModel;
	}

	public void setCourseTypeComponentModel(CourseTypeComponentModel courseTypeComponentModel) {
		this.courseTypeComponentModel = courseTypeComponentModel;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getGuideErpid() {
		return guideErpid;
	}

	public void setGuideErpid(String guideErpid) {
		this.guideErpid = guideErpid;
	}

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	public int getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}

	public String getResultSemesterSubId() {
		return resultSemesterSubId;
	}

	public void setResultSemesterSubId(String resultSemesterSubId) {
		this.resultSemesterSubId = resultSemesterSubId;
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

	@Override
	public String toString() {
		return "ProjectRegistrationModel [projectRegistrationPKId=" + projectRegistrationPKId + ", studentLoginDetials="
				+ studentLoginDetials + ", semesterDetailsModel=" + semesterDetailsModel + ", semesterSubId="
				+ semesterSubId + ", courseCatalogModel=" + courseCatalogModel + ", courseId=" + courseId
				+ ", courseTypeComponentModel=" + courseTypeComponentModel + ", courseType=" + courseType + ", slotId="
				+ slotId + ", projectTitle=" + projectTitle + ", guideErpid=" + guideErpid + ", employeeProfile="
				+ employeeProfile + ", projectDuration=" + projectDuration + ", resultSemesterSubId="
				+ resultSemesterSubId + ", internalFoilcardNumber=" + internalFoilcardNumber
				+ ", externalFoilcardNumber=" + externalFoilcardNumber + "]";
	}*/

	
	
	/*public ProjectRegistrationPKModel getProjectRegistrationPKId() {
		return projectRegistrationPKId;
	}

	public void setProjectRegistrationPKId(ProjectRegistrationPKModel projectRegistrationPKId) {
		this.projectRegistrationPKId = projectRegistrationPKId;
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

	public CourseRegistrationModel getCourseRegistrationModel() {
		return courseRegistrationModel;
	}

	public void setCourseRegistrationModel(CourseRegistrationModel courseRegistrationModel) {
		this.courseRegistrationModel = courseRegistrationModel;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getGuideErpid() {
		return guideErpid;
	}

	public void setGuideErpid(String guideErpid) {
		this.guideErpid = guideErpid;
	}

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	public int getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}

	public String getResultSemesterSubId() {
		return resultSemesterSubId;
	}

	public void setResultSemesterSubId(String resultSemesterSubId) {
		this.resultSemesterSubId = resultSemesterSubId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectRegistrationPKId == null) ? 0 : projectRegistrationPKId.hashCode());
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
		ProjectRegistrationModel other = (ProjectRegistrationModel) obj;
		if (projectRegistrationPKId == null) {
			if (other.projectRegistrationPKId != null)
				return false;
		} else if (!projectRegistrationPKId.equals(other.projectRegistrationPKId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectRegistrationModel [projectRegistrationPKId=" + projectRegistrationPKId + ", semesterSubId="
				+ semesterSubId + ", courseId=" + courseId + ", courseType=" + courseType + ", courseRegistrationModel="
				+ courseRegistrationModel + ", slotId=" + slotId + ", projectTitle=" + projectTitle + ", guideErpid="
				+ guideErpid + ", employeeProfile=" + employeeProfile + ", projectDuration=" + projectDuration
				+ ", resultSemesterSubId=" + resultSemesterSubId + ", internalFoilcardNumber=" + internalFoilcardNumber
				+ ", externalFoilcardNumber=" + externalFoilcardNumber + "]";
	}

	@Override
	public String toString() {
		return "ProjectRegistrationModel [projectRegistrationPKId=" + projectRegistrationPKId + ", semesterSubId="
				+ semesterSubId + ", courseId=" + courseId + ", courseType=" + courseType + ", courseRegistrationModel="
				+ courseRegistrationModel + ", slotId=" + slotId + ", projectTitle=" + projectTitle + ", guideErpid="
				+ guideErpid + ", projectDuration=" + projectDuration + ", resultSemesterSubId=" + resultSemesterSubId
				+ ", internalFoilcardNumber=" + internalFoilcardNumber + ", externalFoilcardNumber="
				+ externalFoilcardNumber + "]";
	}*/
	
	
	
}
