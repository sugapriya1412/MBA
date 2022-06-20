package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ROOM_TYPE database table.
 * 
 */
@Entity
@Table(name="ROOM_TYPE",schema="estates")
@NamedQuery(name="RoomType.findAll", query="SELECT r FROM RoomType r")
public class RoomType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROOM_TYPE_ID")
	private long roomTypeId;

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
	@OneToMany(mappedBy="roomType")
	private List<RoomMaster> roomMasters;

	public RoomType() {
	}

	public long getRoomTypeId() {
		return this.roomTypeId;
	}

	public void setRoomTypeId(long roomTypeId) {
		this.roomTypeId = roomTypeId;
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
		roomMaster.setRoomType(this);

		return roomMaster;
	}

	public RoomMaster removeRoomMaster(RoomMaster roomMaster) {
		getRoomMasters().remove(roomMaster);
		roomMaster.setRoomType(null);

		return roomMaster;
	}

}