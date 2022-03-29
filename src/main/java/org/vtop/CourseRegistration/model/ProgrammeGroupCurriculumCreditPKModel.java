package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProgrammeGroupCurriculumCreditPKModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="PRGM_GROUP_PROGRAMME_GROUP_ID")
	private Integer groupId;
	
	@Column(name="ADMISSION_YEAR")
	private Integer admissionYear;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(Integer admissionYear) {
		this.admissionYear = admissionYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admissionYear == null) ? 0 : admissionYear.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
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
		ProgrammeGroupCurriculumCreditPKModel other = (ProgrammeGroupCurriculumCreditPKModel) obj;
		if (admissionYear == null) {
			if (other.admissionYear != null)
				return false;
		} else if (!admissionYear.equals(other.admissionYear))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}

}
