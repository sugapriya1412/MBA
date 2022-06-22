package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BUILDING_TYPE database table.
 * 
 */
@Entity
@Table(name="BUILDING_TYPE",schema="estates")
@NamedQuery(name="BuildingType.findAll", query="SELECT b FROM BuildingType b")
public class BuildingType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Valid
	@NotEmpty
	@Id
	@Column(name="CODE")
	private String code;

	@NotEmpty
	@Column(name="DESCRIPTION")
	private String description;

	@NotNull
	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus;

	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIMESTAMP")
	private Date logTimestamp;

	@Column(name="LOG_USERID")
	private String logUserid;

	//bi-directional many-to-one association to BuildingMaster
	@OneToMany(mappedBy="buildingType")
	private List<BuildingMaster> buildingMasters;

	public BuildingType() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(BigDecimal lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLogIpaddress() {
		return this.logIpaddress;
	}

	public void setLogIpaddress(String logIpaddress) {
		this.logIpaddress = logIpaddress;
	}

	public Date getLogTimestamp() {
		return this.logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogUserid() {
		return this.logUserid;
	}

	public void setLogUserid(String logUserid) {
		this.logUserid = logUserid;
	}

	public List<BuildingMaster> getBuildingMasters() {
		return this.buildingMasters;
	}

	public void setBuildingMasters(List<BuildingMaster> buildingMasters) {
		this.buildingMasters = buildingMasters;
	}

	public BuildingMaster addBuildingMaster(BuildingMaster buildingMaster) {
		getBuildingMasters().add(buildingMaster);
		buildingMaster.setBuildingType(this);

		return buildingMaster;
	}

	public BuildingMaster removeBuildingMaster(BuildingMaster buildingMaster) {
		getBuildingMasters().remove(buildingMaster);
		buildingMaster.setBuildingType(null);

		return buildingMaster;
	}

}