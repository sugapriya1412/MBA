package org.vtop.CourseRegistration.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
	
@Embeddable
public class ProgramMigrationCoursePK implements Serializable{

		private static final long serialVersionUID = 1L;
			
		@Column(name="PROGRAM_SPECIALIZATION_ID")
		private Integer programSpecializationId;
			
		@Column(name="COURSE_SYSTEM")
		private String courseSystem;
		
		@Column(name="COURSE_ID")
		private String courseId;

		public Integer getProgramSpecializationId() {
			return programSpecializationId;
		}

		public void setProgramSpecializationId(Integer programSpecializationId) {
			this.programSpecializationId = programSpecializationId;
		}

		public String getCourseSystem() {
			return courseSystem;
		}

		public void setCourseSystem(String courseSystem) {
			this.courseSystem = courseSystem;
		}

		public String getCourseId() {
			return courseId;
		}

		public void setCourseId(String courseId) {
			this.courseId = courseId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
			result = prime * result + ((courseSystem == null) ? 0 : courseSystem.hashCode());
			result = prime * result + ((programSpecializationId == null) ? 0 : programSpecializationId.hashCode());
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
			ProgramMigrationCoursePK other = (ProgramMigrationCoursePK) obj;
			if (courseId == null) {
				if (other.courseId != null)
					return false;
			} else if (!courseId.equals(other.courseId))
				return false;
			if (courseSystem == null) {
				if (other.courseSystem != null)
					return false;
			} else if (!courseSystem.equals(other.courseSystem))
				return false;
			if (programSpecializationId == null) {
				if (other.programSpecializationId != null)
					return false;
			} else if (!programSpecializationId.equals(other.programSpecializationId))
				return false;
			return true;
		}
}
