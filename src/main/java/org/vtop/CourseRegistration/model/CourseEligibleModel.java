package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="COURSE_ELIGIBLE", schema="academics")
public class CourseEligibleModel implements Serializable
{			
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRGRM_GROUP_PROGRAMME_GROUP_ID")
	private Integer groupId;
	
	@ManyToOne
	@JoinColumn(name="PRGRM_GROUP_PROGRAMME_GROUP_ID", insertable = false, updatable = false)
	private ProgrammeGroupModel programmeGroupModel;
		
	@Column(name="PROGRAM_ELIGIBLE")
	private String programEligible;
		
	@Column(name="PROGRAM_CGPA")
	private String programCgpa;
	
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
	
	public String getProgramEligible() {
		return programEligible;
	}

	public void setProgramEligible(String programEligible) {
		this.programEligible = programEligible;
	}

	public String getProgramCgpa() {
		return programCgpa;
	}

	public void setProgramCgpa(String programCgpa) {
		this.programCgpa = programCgpa;
	}

	@Override
	public String toString() {
		return "CourseEligibleModel [groupId=" + groupId + ", programmeGroupModel=" + programmeGroupModel
					+ ", programEligible=" + programEligible + ", programCgpa=" + programCgpa + "]";
	}	
}
