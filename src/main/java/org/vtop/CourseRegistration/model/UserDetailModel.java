package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the USER_DETAILS database table.
 * 
 */
@Entity
@Table(name="USER_DETAILS", schema ="VTOPMASTER")
public class UserDetailModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String userId;

	@Column(name="ACCOUNT_STATUS")
	private BigDecimal accountStatus;

	@Column(name="CAMPUS_CODE")
	private String campusCode;

	private String email;

	@Column(name="LAST_LOGIN_IPADDRESS")
	private String lastLoginIpaddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN_TIMESTAMP")
	private Date lastLoginTimestamp;

	@Temporal(TemporalType.DATE)
	@Column(name="LOGIN_FAIL_LOCK_TIMESTAMP")
	private Date loginFailLockTimestamp;


	private String mobile;
	private Integer role;

	@Column(name="NUM_FAIL_ATTEMPT")
	private BigDecimal numFailAttempt;

	private String passwd;

	@Column(name="PASSWD_STATUS")
	private BigDecimal passwdStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="PASSWD_UPDATE_TIMESTAMP")
	private Date passwdUpdateTimestamp;
	@Column(name="OTP_MOBILE")
	private String otpMobile;
	@Column(name="OTP_EMAIL")
	private String otpEmail;
	
	
	@Column(name="PASSWD_RESET_FG")
	private Integer resetFlag;
	
	
	public Integer getResetFlag() {
		return resetFlag;
	}

	public void setResetFlag(Integer resetFlag) {
		this.resetFlag = resetFlag;
	}

	public String getOtpMobile() {
		return otpMobile;
	}

	public void setOtpMobile(String otpMobile) {
		this.otpMobile = otpMobile;
	}

	public String getOtpEmail() {
		return otpEmail;
	}

	public void setOtpEmail(String otpEmail) {
		this.otpEmail = otpEmail;
	}
	public UserDetailModel() {
	}

	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(BigDecimal accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCampusCode() {
		return this.campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLoginIpaddress() {
		return this.lastLoginIpaddress;
	}

	public void setLastLoginIpaddress(String lastLoginIpaddress) {
		this.lastLoginIpaddress = lastLoginIpaddress;
	}

	public Date getLastLoginTimestamp() {
		return this.lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Date lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

	public Date getLoginFailLockTimestamp() {
		return this.loginFailLockTimestamp;
	}

	public void setLoginFailLockTimestamp(Date loginFailLockTimestamp) {
		this.loginFailLockTimestamp = loginFailLockTimestamp;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getNumFailAttempt() {
		return this.numFailAttempt;
	}

	public void setNumFailAttempt(BigDecimal numFailAttempt) {
		this.numFailAttempt = numFailAttempt;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public BigDecimal getPasswdStatus() {
		return this.passwdStatus;
	}

	public void setPasswdStatus(BigDecimal passwdStatus) {
		this.passwdStatus = passwdStatus;
	}

	public Date getPasswdUpdateTimestamp() {
		return this.passwdUpdateTimestamp;
	}

	public void setPasswdUpdateTimestamp(Date passwdUpdateTimestamp) {
		this.passwdUpdateTimestamp = passwdUpdateTimestamp;
	}

}