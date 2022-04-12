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


@Entity
@Table(name="BASKET_COURSE_CATALOG", schema="ACADEMICS")
public class BasketCourseCatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private BasketCourseCatalogPK bccPkId;
	
	@ManyToOne
	@JoinColumn(name="BASKET_DETAILS_BASKET_ID", insertable = false, updatable = false)
	private BasketDetailsModel basketDetailsModel;
	
	@ManyToOne
	@JoinColumn(name="COURSE_CATALOG_COURSE_ID", insertable = false, updatable = false)
	private CourseCatalogModel courseCatalogModel;
		
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

		
	public BasketCourseCatalogPK getBccPkId() {
		return bccPkId;
	}

	public void setBccPkId(BasketCourseCatalogPK bccPkId) {
		this.bccPkId = bccPkId;
	}

	public BasketDetailsModel getBasketDetailsModel() {
		return basketDetailsModel;
	}

	public void setBasketDetailsModel(BasketDetailsModel basketDetailsModel) {
		this.basketDetailsModel = basketDetailsModel;
	}

	public CourseCatalogModel getCourseCatalogModel() {
		return courseCatalogModel;
	}

	public void setCourseCatalogModel(CourseCatalogModel courseCatalogModel) {
		this.courseCatalogModel = courseCatalogModel;
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
		result = prime * result + ((basketDetailsModel == null) ? 0 : basketDetailsModel.hashCode());
		result = prime * result + ((bccPkId == null) ? 0 : bccPkId.hashCode());
		result = prime * result + ((courseCatalogModel == null) ? 0 : courseCatalogModel.hashCode());
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
		BasketCourseCatalogModel other = (BasketCourseCatalogModel) obj;
		if (basketDetailsModel == null) {
			if (other.basketDetailsModel != null)
				return false;
		} else if (!basketDetailsModel.equals(other.basketDetailsModel))
			return false;
		if (bccPkId == null) {
			if (other.bccPkId != null)
				return false;
		} else if (!bccPkId.equals(other.bccPkId))
			return false;
		if (courseCatalogModel == null) {
			if (other.courseCatalogModel != null)
				return false;
		} else if (!courseCatalogModel.equals(other.courseCatalogModel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasketCourseCatalogModel [bccPkId=" + bccPkId + ", basketDetailsModel=" + basketDetailsModel
				+ ", courseCatalogModel=" + courseCatalogModel + ", status=" + status + ", logUserId=" + logUserId
				+ ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress + "]";
	}
}
