package org.vtop.CourseRegistration.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="RegistrationLog")
public class RegistrationLog
{
	@Id
	private String id;
	
	private String registerNumber;
	private int	logStatus;
	private Date loginTimeStamp;
	private String loginIpAddress;
	private Date activeTimeStamp;
	private Date logoutTimeStamp;
	private String logoutIpAddress;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public int getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(int logStatus) {
		this.logStatus = logStatus;
	}
	public Date getLoginTimeStamp() {
		return loginTimeStamp;
	}
	public void setLoginTimeStamp(Date loginTimeStamp) {
		this.loginTimeStamp = loginTimeStamp;
	}
	public String getLoginIpAddress() {
		return loginIpAddress;
	}
	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}
	public Date getActiveTimeStamp() {
		return activeTimeStamp;
	}
	public void setActiveTimeStamp(Date activeTimeStamp) {
		this.activeTimeStamp = activeTimeStamp;
	}
	public Date getLogoutTimeStamp() {
		return logoutTimeStamp;
	}
	public void setLogoutTimeStamp(Date logoutTimeStamp) {
		this.logoutTimeStamp = logoutTimeStamp;
	}
	public String getLogoutIpAddress() {
		return logoutIpAddress;
	}
	public void setLogoutIpAddress(String logoutIpAddress) {
		this.logoutIpAddress = logoutIpAddress;
	}
	
	@Override
	public String toString() {
		return "RegistrationLog [id=" + id + ", registerNumber=" + registerNumber + ", logStatus=" + logStatus
				+ ", loginTimeStamp=" + loginTimeStamp + ", loginIpAddress=" + loginIpAddress + ", activeTimeStamp="
				+ activeTimeStamp + ", logoutTimeStamp=" + logoutTimeStamp + ", logoutIpAddress=" + logoutIpAddress
				+ "]";
	}
}
