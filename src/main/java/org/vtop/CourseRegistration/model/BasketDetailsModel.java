package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BASKET_DETAILS", schema="ACADEMICS")
public class BasketDetailsModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BASKET_ID")
	private String basketId;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="BASKET_VERSION")
	private float basketVersion;
	
	@Column(name="BASKET_TYPE")
	private String basketType;
	
	@Column(name="CREDITS")
	private int credits;
	
	@Column(name="NUMBER_OF_COURSES")
	private int noOfcourses;
	
	@Column(name="BASKET_CATEGORY")
	private String basketCategory;
	
	@Column(name="LOCK_STATUS")
	private int status;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTimestamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	
	public String getBasketId() {
		return basketId;
	}

	public void setBasketId(String basketId) {
		this.basketId = basketId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getBasketVersion() {
		return basketVersion;
	}

	public void setBasketVersion(float basketVersion) {
		this.basketVersion = basketVersion;
	}

	public String getBasketType() {
		return basketType;
	}

	public void setBasketType(String basketType) {
		this.basketType = basketType;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getNoOfcourses() {
		return noOfcourses;
	}

	public void setNoOfcourses(int noOfcourses) {
		this.noOfcourses = noOfcourses;
	}

	public String getBasketCategory() {
		return basketCategory;
	}

	public void setBasketCategory(String basketCategory) {
		this.basketCategory = basketCategory;
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
	public String toString() {
		return "BasketDetailsModel [basketId=" + basketId + ", code=" + code + ", description=" + description
				+ ", basketVersion=" + basketVersion + ", basketType=" + basketType + ", credits=" + credits
				+ ", noOfcourses=" + noOfcourses + ", basketCategory=" + basketCategory + ", status=" + status
				+ ", logUserId=" + logUserId + ", logTimestamp=" + logTimestamp + ", logIpaddress=" + logIpaddress
				+ "]";
	}
}
