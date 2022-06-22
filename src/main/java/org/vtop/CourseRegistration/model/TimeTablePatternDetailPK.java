package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class TimeTablePatternDetailPK implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Column(name="PATTERN_ID")
		private Integer patternId;
		
		@Column(name="TT_SESSION")
		private String session;

		public Integer getPatternId() {
			return patternId;
		}

		public void setPatternId(Integer patternId) {
			this.patternId = patternId;
		}

		public String getSession() {
			return session;
		}

		public void setSession(String session) {
			this.session = session;
		}
		
		
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((patternId == null) ? 0 : patternId.hashCode());
			result = prime * result + ((session == null) ? 0 : session.hashCode());
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
			TimeTablePatternDetailPK other = (TimeTablePatternDetailPK) obj;
			if (patternId == null) {
				if (other.patternId != null)
					return false;
			} else if (!patternId.equals(other.patternId))
				return false;
			if (session == null) {
				if (other.session != null)
					return false;
			} else if (!session.equals(other.session))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TimeTablePatternDetailPK [patternId=" + patternId + ", session=" + session + "]";
		}	
}
