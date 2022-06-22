package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProgrammeSpecializationCurriculumCreditPKModel implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@Column(name="PRGSPLZN_PRG_SPECIALIZATION_ID")
	private Integer specializationId;
	
	@Column(name="ADMISSION_YEAR")
	private Integer admissionYear;
	
	@Column(name="CURRICULUM_VERSION")
	private Float curriculumVersion;

	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public Integer getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(Integer admissionYear) {
		this.admissionYear = admissionYear;
	}

	public Float getCurriculumVersion() {
		return curriculumVersion;
	}

	public void setCurriculumVersion(Float curriculumVersion) {
		this.curriculumVersion = curriculumVersion;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admissionYear == null) ? 0 : admissionYear.hashCode());
		result = prime * result + ((curriculumVersion == null) ? 0 : curriculumVersion.hashCode());
		result = prime * result + ((specializationId == null) ? 0 : specializationId.hashCode());
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
		ProgrammeSpecializationCurriculumCreditPKModel other = (ProgrammeSpecializationCurriculumCreditPKModel) obj;
		if (admissionYear == null) {
			if (other.admissionYear != null)
				return false;
		} else if (!admissionYear.equals(other.admissionYear))
			return false;
		if (curriculumVersion == null) {
			if (other.curriculumVersion != null)
				return false;
		} else if (!curriculumVersion.equals(other.curriculumVersion))
			return false;
		if (specializationId == null) {
			if (other.specializationId != null)
				return false;
		} else if (!specializationId.equals(other.specializationId))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "ProgrammeSpecializationCurriculumCreditPKModel [specializationId=" + specializationId
				+ ", admissionYear=" + admissionYear + ", curriculumVersion=" + curriculumVersion + "]";
	}
}
