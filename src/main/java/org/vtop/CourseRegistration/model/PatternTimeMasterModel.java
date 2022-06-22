package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TT_PATTERN_TIME_MASTER", schema="academics")
public class PatternTimeMasterModel implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@EmbeddedId
	    private PatternTimeMasterPK ptmPkId;
		
		@ManyToOne
		@JoinColumn(name="TMTBLPATTERN_MASTER_PATTERN_ID", insertable = false, updatable = false)
		private TimeTablePatternModel timeTablePatternModel;
		
		@DateTimeFormat(pattern = "HH:mm")
		@Temporal(TemporalType.TIME)	
		@Column(name="STARTING_TIME")
		private Date StartingTime;
		
		@DateTimeFormat(pattern = "HH:mm")
		@Temporal(TemporalType.TIME)
		@Column(name="ENDING_TIME")
		private Date EndingTime;

		public PatternTimeMasterPK getPtmPkId() {
			return ptmPkId;
		}

		public void setPtmPkId(PatternTimeMasterPK ptmPkId) {
			this.ptmPkId = ptmPkId;
		}

		public TimeTablePatternModel getTimeTablePatternModel() {
			return timeTablePatternModel;
		}

		public void setTimeTablePatternModel(TimeTablePatternModel timeTablePatternModel) {
			this.timeTablePatternModel = timeTablePatternModel;
		}

		public Date getStartingTime() {
			return StartingTime;
		}

		public void setStartingTime(Date startingTime) {
			StartingTime = startingTime;
		}

		public Date getEndingTime() {
			return EndingTime;
		}

		public void setEndingTime(Date endingTime) {
			EndingTime = endingTime;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((EndingTime == null) ? 0 : EndingTime.hashCode());
			result = prime * result + ((StartingTime == null) ? 0 : StartingTime.hashCode());
			result = prime * result + ((ptmPkId == null) ? 0 : ptmPkId.hashCode());
			result = prime * result + ((timeTablePatternModel == null) ? 0 : timeTablePatternModel.hashCode());
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
			PatternTimeMasterModel other = (PatternTimeMasterModel) obj;
			if (EndingTime == null) {
				if (other.EndingTime != null)
					return false;
			} else if (!EndingTime.equals(other.EndingTime))
				return false;
			if (StartingTime == null) {
				if (other.StartingTime != null)
					return false;
			} else if (!StartingTime.equals(other.StartingTime))
				return false;
			if (ptmPkId == null) {
				if (other.ptmPkId != null)
					return false;
			} else if (!ptmPkId.equals(other.ptmPkId))
				return false;
			if (timeTablePatternModel == null) {
				if (other.timeTablePatternModel != null)
					return false;
			} else if (!timeTablePatternModel.equals(other.timeTablePatternModel))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "PatternTimeMasterModel [ptmPkId=" + ptmPkId + ", timeTablePatternModel=" + timeTablePatternModel
					+ ", StartingTime=" + StartingTime + ", EndingTime=" + EndingTime + "]";
		}
}
