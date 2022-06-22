package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import org.vtop.academics.course.model.CourseCatalogModel;
//import org.vtop.common.academics.model.ProgrammeSpecializationModel;
	
@Entity
@Table(name="PROGRAM_MIGRATION_COURSE")
public class ProgramMigrationCourseModel implements Serializable {
			
			private static final long serialVersionUID = 1L;
			
			@EmbeddedId
		    private ProgramMigrationCoursePK pmcPkId;
			
			@ManyToOne
			@JoinColumn(name="PROGRAM_SPECIALIZATION_ID", insertable = false, updatable = false)
			private ProgrammeSpecializationModel programmeSpecializationModel;
			
			@ManyToOne
			@JoinColumn(name="COURSE_ID", insertable = false, updatable = false)
			private CourseCatalogModel courseCatalogModel;

			@Column(name="STATUS")
			private Integer status;
					
			@Column(name="LOG_USERID")
			private String logUserId;
					
			@Column(name="LOG_TIMESTAMP")
			@Temporal(TemporalType.TIMESTAMP)
			private Date logTimestamp;
					
			@Column(name="LOG_IPADDRESS")
			private String logIpaddress;

			public ProgramMigrationCoursePK getPmcPkId() {
				return pmcPkId;
			}

			public void setPmcPkId(ProgramMigrationCoursePK pmcPkId) {
				this.pmcPkId = pmcPkId;
			}

			public ProgrammeSpecializationModel getProgrammeSpecializationModel() {
				return programmeSpecializationModel;
			}

			public void setProgrammeSpecializationModel(ProgrammeSpecializationModel programmeSpecializationModel) {
				this.programmeSpecializationModel = programmeSpecializationModel;
			}

			public CourseCatalogModel getCourseCatalogModel() {
				return courseCatalogModel;
			}

			public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
				this.courseCatalogModel = courseCatalogModel;
			}

			public Integer getStatus() {
				return status;
			}

			public void setStatus(Integer status) {
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

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((courseCatalogModel == null) ? 0 : courseCatalogModel.hashCode());
				result = prime * result + ((logIpaddress == null) ? 0 : logIpaddress.hashCode());
				result = prime * result + ((logTimestamp == null) ? 0 : logTimestamp.hashCode());
				result = prime * result + ((logUserId == null) ? 0 : logUserId.hashCode());
				result = prime * result + ((pmcPkId == null) ? 0 : pmcPkId.hashCode());
				result = prime * result
						+ ((programmeSpecializationModel == null) ? 0 : programmeSpecializationModel.hashCode());
				result = prime * result + ((status == null) ? 0 : status.hashCode());
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
				ProgramMigrationCourseModel other = (ProgramMigrationCourseModel) obj;
				if (courseCatalogModel == null) {
					if (other.courseCatalogModel != null)
						return false;
				} else if (!courseCatalogModel.equals(other.courseCatalogModel))
					return false;
				if (logIpaddress == null) {
					if (other.logIpaddress != null)
						return false;
				} else if (!logIpaddress.equals(other.logIpaddress))
					return false;
				if (logTimestamp == null) {
					if (other.logTimestamp != null)
						return false;
				} else if (!logTimestamp.equals(other.logTimestamp))
					return false;
				if (logUserId == null) {
					if (other.logUserId != null)
						return false;
				} else if (!logUserId.equals(other.logUserId))
					return false;
				if (pmcPkId == null) {
					if (other.pmcPkId != null)
						return false;
				} else if (!pmcPkId.equals(other.pmcPkId))
					return false;
				if (programmeSpecializationModel == null) {
					if (other.programmeSpecializationModel != null)
						return false;
				} else if (!programmeSpecializationModel.equals(other.programmeSpecializationModel))
					return false;
				if (status == null) {
					if (other.status != null)
						return false;
				} else if (!status.equals(other.status))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "ProgramMigrationCourseModel [pmcPkId=" + pmcPkId + ", programmeSpecializationModel="
						+ programmeSpecializationModel + ", courseCatalogModel=" + courseCatalogModel + ", status="
						+ status + ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress="
						+ logIpaddress + "]";
			}
}
