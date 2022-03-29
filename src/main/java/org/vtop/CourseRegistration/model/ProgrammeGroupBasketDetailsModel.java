package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PROGRAMME_GROUP_BASKET_DETAILS", schema="ACADEMICS")
public class ProgrammeGroupBasketDetailsModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private ProgrammeGroupBasketDetailsPK pgbPkId;
	
	@MapsId("basketId")
	@ManyToOne
	@JoinColumn(name="BASKET_DETAILS_BASKET_ID", insertable = false, updatable = false)
	private BasketDetailsModel basketDetailsModel;

	
	@MapsId("groupId")
	@ManyToOne
	@JoinColumn(name="PRGRM_GROUP_PROGRAMME_GROUP_ID", insertable = false, updatable = false)
	private ProgrammeGroupModel programmeGroupModel;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	
	public ProgrammeGroupBasketDetailsPK getPgbPkId() {
		return pgbPkId;
	}

	public void setPgbPkId(ProgrammeGroupBasketDetailsPK pgbPkId) {
		this.pgbPkId = pgbPkId;
	}

	public BasketDetailsModel getBasketDetailsModel() {
		return basketDetailsModel;
	}

	public void setBasketDetailsModel(BasketDetailsModel basketDetailsModel) {
		this.basketDetailsModel = basketDetailsModel;
	}

	public ProgrammeGroupModel getProgrammeGroupModel() {
		return programmeGroupModel;
	}

	public void setProgrammeGroupModel(ProgrammeGroupModel programmeGroupModel) {
		this.programmeGroupModel = programmeGroupModel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
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
		result = prime * result + ((basketDetailsModel == null) ? 0 : basketDetailsModel.hashCode());
		result = prime * result + ((pgbPkId == null) ? 0 : pgbPkId.hashCode());
		result = prime * result + ((programmeGroupModel == null) ? 0 : programmeGroupModel.hashCode());
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
		ProgrammeGroupBasketDetailsModel other = (ProgrammeGroupBasketDetailsModel) obj;
		if (basketDetailsModel == null) {
			if (other.basketDetailsModel != null)
				return false;
		} else if (!basketDetailsModel.equals(other.basketDetailsModel))
			return false;
		if (pgbPkId == null) {
			if (other.pgbPkId != null)
				return false;
		} else if (!pgbPkId.equals(other.pgbPkId))
			return false;
		if (programmeGroupModel == null) {
			if (other.programmeGroupModel != null)
				return false;
		} else if (!programmeGroupModel.equals(other.programmeGroupModel))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProgrammeGroupBasketDetailsModel [pgbPkId=" + pgbPkId + ", basketDetailsModel=" + basketDetailsModel
				+ ", programmeGroupModel=" + programmeGroupModel + ", status=" + status + ", logUserId=" + logUserId
				+ ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
}