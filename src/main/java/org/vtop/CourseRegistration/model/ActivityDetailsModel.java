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

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="ACTIVITY_DETAILS", schema="ACADEMICS")
public class ActivityDetailsModel implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActivityDetailsModelPK admPkId;
	
	@ManyToOne
	@JoinColumn(name="ACTIVITY_MASTER_ACTIVITY_ID",insertable = false, updatable = false)
	private ActivityMasterModel activitymastermodel;
	
	@ManyToOne
	@JoinColumn(name="SEMSTR_DETAILS_SEMESTER_SUB_ID",insertable = false, updatable = false)
	private SemesterDetailsModel semesterdetailsmodel;
	
	@Column(name="FROM_DATE")
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Column(name="TO_DATE")
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	@Column(name="EXTENSION_DATE")
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date extensionDate;
	
	@Column(name="PAYMENT_STATUS")
	private Integer paymentStatus;
	
	@Column(name="LOCK_STATUS")
	private int lockStatus;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	public ActivityDetailsModelPK getAdmPkId() {
		return admPkId;
	}

	public void setAdmPkId(ActivityDetailsModelPK admPkId) {
		this.admPkId = admPkId;
	}

	public ActivityMasterModel getActivitymastermodel() {
		return activitymastermodel;
	}

	public void setActivitymastermodel(ActivityMasterModel activitymastermodel) {
		this.activitymastermodel = activitymastermodel;
	}

	public SemesterDetailsModel getSemesterdetailsmodel() {
		return semesterdetailsmodel;
	}

	public void setSemesterdetailsmodel(SemesterDetailsModel semesterdetailsmodel) {
		this.semesterdetailsmodel = semesterdetailsmodel;
	}
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getExtensionDate() {
		return extensionDate;
	}

	public void setExtensionDate(Date extensionDate) {
		this.extensionDate = extensionDate;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
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
	public String toString() {
		return "ActivityDetailsModel [admPkId=" + admPkId + ", activitymastermodel=" + activitymastermodel
				+ ", semesterdetailsmodel=" + semesterdetailsmodel + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", extensionDate=" + extensionDate + ", paymentStatus=" + paymentStatus + ", lockStatus=" + lockStatus
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
}
