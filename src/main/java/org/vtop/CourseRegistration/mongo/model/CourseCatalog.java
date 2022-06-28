package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="CourseCatalog")
public class CourseCatalog 
{
	@Id
	private String id;
	
	private String courseId;
	private String courseCode;
	private float courseVersion;
	private String courseTitle;
	private String genericCourseType;
	private int	lectureHours;
	private int	tutorialHours;
	private int	practicalHours;
	private int	projectHours;
	private float lectureCredits;
	private float practicalCredits;
	private float projectCredits;
	private float credits;
	private String preRequisite;
	private String antiRequisite;
	private String coRequisite;
	private String courseSystem;
	private String evaluationType;
	private String discipline;
	private int	programGroupId;
	private String programGroupCode;
	private String alternateProgramGroup;
	private int	costCentreId;
	private String costCentreCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public float getCourseVersion() {
		return courseVersion;
	}
	public void setCourseVersion(float courseVersion) {
		this.courseVersion = courseVersion;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getGenericCourseType() {
		return genericCourseType;
	}
	public void setGenericCourseType(String genericCourseType) {
		this.genericCourseType = genericCourseType;
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
	public String getPreRequisite() {
		return preRequisite;
	}
	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}
	public String getAntiRequisite() {
		return antiRequisite;
	}
	public void setAntiRequisite(String antiRequisite) {
		this.antiRequisite = antiRequisite;
	}
	public String getCoRequisite() {
		return coRequisite;
	}
	public void setCoRequisite(String coRequisite) {
		this.coRequisite = coRequisite;
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
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public int getProgramGroupId() {
		return programGroupId;
	}
	public void setProgramGroupId(int programGroupId) {
		this.programGroupId = programGroupId;
	}
	public String getProgramGroupCode() {
		return programGroupCode;
	}
	public void setProgramGroupCode(String programGroupCode) {
		this.programGroupCode = programGroupCode;
	}
	public String getAlternateProgramGroup() {
		return alternateProgramGroup;
	}
	public void setAlternateProgramGroup(String alternateProgramGroup) {
		this.alternateProgramGroup = alternateProgramGroup;
	}
	public int getCostCentreId() {
		return costCentreId;
	}
	public void setCostCentreId(int costCentreId) {
		this.costCentreId = costCentreId;
	}
	public String getCostCentreCode() {
		return costCentreCode;
	}
	public void setCostCentreCode(String costCentreCode) {
		this.costCentreCode = costCentreCode;
	}
	
	@Override
	public String toString() {
		return "CourseCatalog [id=" + id + ", courseId=" + courseId + ", courseCode=" + courseCode + ", courseVersion="
				+ courseVersion + ", courseTitle=" + courseTitle + ", genericCourseType=" + genericCourseType
				+ ", lectureHours=" + lectureHours + ", tutorialHours=" + tutorialHours + ", practicalHours="
				+ practicalHours + ", projectHours=" + projectHours + ", lectureCredits=" + lectureCredits
				+ ", practicalCredits=" + practicalCredits + ", projectCredits=" + projectCredits + ", credits="
				+ credits + ", preRequisite=" + preRequisite + ", antiRequisite=" + antiRequisite + ", coRequisite="
				+ coRequisite + ", courseSystem=" + courseSystem + ", evaluationType=" + evaluationType
				+ ", discipline=" + discipline + ", programGroupId=" + programGroupId + ", programGroupCode="
				+ programGroupCode + ", alternateProgramGroup=" + alternateProgramGroup + ", costCentreId="
				+ costCentreId + ", costCentreCode=" + costCentreCode + "]";
	}
}
