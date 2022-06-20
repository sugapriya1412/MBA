package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CurriculumProgramModelPK implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Column(name="PROGRAM_SPECIALIZATION_ID")
	private Integer programSpecializationId;
		
	@Column(name="YEAR")
	private Integer year;

	public CurriculumProgramModelPK(){
		
	}
	public CurriculumProgramModelPK(Integer programSpecializationId, Integer year) {
		
		this.programSpecializationId = programSpecializationId;
		this.year = year;
	}

	public Integer getProgramSpecializationId() {
		return programSpecializationId;
	}

	public void setProgramSpecializationId(Integer programSpecializationId) {
		this.programSpecializationId = programSpecializationId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((programSpecializationId == null) ? 0 : programSpecializationId.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		CurriculumProgramModelPK other = (CurriculumProgramModelPK) obj;
		if (programSpecializationId == null) {
			if (other.programSpecializationId != null)
				return false;
		} else if (!programSpecializationId.equals(other.programSpecializationId))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
