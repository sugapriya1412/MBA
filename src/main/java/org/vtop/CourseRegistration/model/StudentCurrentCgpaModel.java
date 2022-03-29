package org.vtop.CourseRegistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT_CURRENT_CGPA", schema="ACADEMICS")
public class StudentCurrentCgpaModel {

	
	@Id
	@Column(name="REGNO")
	private String regNo;
	
	@Column(name="TOTAL_CREDIT_REGISTERED")
	private Integer totalCreditRegistered;
	
	@Column(name="TOTAL_CREDIT_EARNED")
	private Integer totalCreditEarned; 
	
	@Column(name="CGPA")
	private Float cgpa;
	
	@Column(name="TIMESTAMP")
	private Date timeStamp;

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public Integer getTotalCreditRegistered() {
		return totalCreditRegistered;
	}

	public void setTotalCreditRegistered(Integer totalCreditRegistered) {
		this.totalCreditRegistered = totalCreditRegistered;
	}

	public Integer getTotalCreditEarned() {
		return totalCreditEarned;
	}

	public void setTotalCreditEarned(Integer totalCreditEarned) {
		this.totalCreditEarned = totalCreditEarned;
	}

	public Float getCgpa() {
		return cgpa;
	}

	public void setCgpa(Float cgpa) {
		this.cgpa = cgpa;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "StudentCurrentCgpaModel [regNo=" + regNo + ", totalCreditRegistered=" + totalCreditRegistered
				+ ", totalCreditEarned=" + totalCreditEarned + ", cgpa=" + cgpa + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
}
