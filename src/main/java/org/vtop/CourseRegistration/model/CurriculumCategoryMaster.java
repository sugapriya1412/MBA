package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CURRICULUM_CATEGORY_MASTER", schema="ACADEMICS")
public class CurriculumCategoryMaster
{
	@Id
	@Column(name="COURSE_CATEGORY")
	private String courseCategory;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ASSOCIATE_CATEGORY")
	private String associateCategory;
	
	@Column(name="ORDER_NO")
	private int orderNo;
		
	@Column(name="LOCK_STATUS")
	private int lockStatus;
	
	@Column(name="LOG_USERID")
	private String logUserId;
	
	@Column(name="LOG_TIMESTAMP")
	private Date logTimeStamp;
	
	@Column(name="LOG_IPADDRESS")
	private String logIpAddress;

	
	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssociateCategory() {
		return associateCategory;
	}

	public void setAssociateCategory(String associateCategory) {
		this.associateCategory = associateCategory;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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

	public Date getLogTimeStamp() {
		return logTimeStamp;
	}

	public void setLogTimeStamp(Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	
	@Override
	public String toString() {
		return "CurriculumCategoryMaster [courseCategory=" + courseCategory + ", description=" + description
				+ ", associateCategory=" + associateCategory + ", orderNo=" + orderNo + ", lockStatus=" + lockStatus
				+ ", logUserId=" + logUserId + ", logTimeStamp=" + logTimeStamp + ", logIpAddress=" + logIpAddress
				+ "]";
	}
}
