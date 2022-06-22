package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BUILDING_MASTER database table.
 * 
 */
@Entity
@Table(name="BUILDING_MASTER",schema="estates")
@NamedQuery(name="BuildingMaster.findAll", query="SELECT b FROM BuildingMaster b")
public class BuildingMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BUILDING_ID")
	private long buildingId;

	@Column(name="BUILDING_BLOCK")
	private String buildingBlock;

	@Column(name="CAMPUS_CODE")
	private String campusCode;

	private String code;

	private String description;

	@Column(name="FLOOR_COUNT")
	private BigDecimal floorCount;

	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus;

	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIMESTAMP")
	private Date logTimestamp;

	@Column(name="LOG_USERID")
	private String logUserid;

	//bi-directional many-to-one association to BuildingType
	@ManyToOne
	@JoinColumn(name="BUILDING_TYPE_CODE")
	private BuildingType buildingType;

	//bi-directional many-to-one association to RoomMaster
	@OneToMany(mappedBy="buildingMaster")
	private List<RoomMaster> roomMasters;

	public BuildingMaster() {
	}

	public long getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingBlock() {
		return this.buildingBlock;
	}

	public void setBuildingBlock(String buildingBlock) {
		this.buildingBlock = buildingBlock;
	}

	public String getCampusCode() {
		return this.campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
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

	public BigDecimal getFloorCount() {
		return this.floorCount;
	}

	public void setFloorCount(BigDecimal floorCount) {
		this.floorCount = floorCount;
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

	public BuildingType getBuildingType() {
		return this.buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public List<RoomMaster> getRoomMasters() {
		return this.roomMasters;
	}

	public void setRoomMasters(List<RoomMaster> roomMasters) {
		this.roomMasters = roomMasters;
	}

	public RoomMaster addRoomMaster(RoomMaster roomMaster) {
		getRoomMasters().add(roomMaster);
		roomMaster.setBuildingMaster(this);

		return roomMaster;
	}

	public RoomMaster removeRoomMaster(RoomMaster roomMaster) {
		getRoomMasters().remove(roomMaster);
		roomMaster.setBuildingMaster(null);

		return roomMaster;
	}

}