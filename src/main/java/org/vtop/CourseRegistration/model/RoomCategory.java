package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ROOM_CATEGORY database table.
 * 
 */
@Entity
@Table(name="ROOM_CATEGORY",schema="estates")
@NamedQuery(name="RoomCategory.findAll", query="SELECT r FROM RoomCategory r")
public class RoomCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROOM_CATEGORY_ID")
	private long roomCategoryId;

	private String code;

	private String description;

	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus;

	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIMESTAMP")
	private Date logTimestamp;

	@Column(name="LOG_USERID")
	private String logUserid;

	//bi-directional many-to-one association to RoomMaster
	@OneToMany(mappedBy="roomCategory")
	private List<RoomMaster> roomMasters;

	public RoomCategory() {
	}

	public long getRoomCategoryId() {
		return this.roomCategoryId;
	}

	public void setRoomCategoryId(long roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
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

	public List<RoomMaster> getRoomMasters() {
		return this.roomMasters;
	}

	public void setRoomMasters(List<RoomMaster> roomMasters) {
		this.roomMasters = roomMasters;
	}

	public RoomMaster addRoomMaster(RoomMaster roomMaster) {
		getRoomMasters().add(roomMaster);
		roomMaster.setRoomCategory(this);

		return roomMaster;
	}

	public RoomMaster removeRoomMaster(RoomMaster roomMaster) {
		getRoomMasters().remove(roomMaster);
		roomMaster.setRoomCategory(null);

		return roomMaster;
	}

}