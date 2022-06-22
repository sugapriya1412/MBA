package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ROOM_MASTER database table.
 * 
 */
@Embeddable
public class RoomMasterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BUILDING_MASTER_BUILDING_ID", insertable=false, updatable=false)
	private long buildingMasterBuildingId;

	@Column(name="ROOM_NUMBER")
	private String roomNumber;

	public RoomMasterPK() {
	}
	public long getBuildingMasterBuildingId() {
		return this.buildingMasterBuildingId;
	}
	public void setBuildingMasterBuildingId(long buildingMasterBuildingId) {
		this.buildingMasterBuildingId = buildingMasterBuildingId;
	}
	public String getRoomNumber() {
		return this.roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoomMasterPK)) {
			return false;
		}
		RoomMasterPK castOther = (RoomMasterPK)other;
		return 
			(this.buildingMasterBuildingId == castOther.buildingMasterBuildingId)
			&& this.roomNumber.equals(castOther.roomNumber);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.buildingMasterBuildingId ^ (this.buildingMasterBuildingId >>> 32)));
		hash = hash * prime + this.roomNumber.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "RoomMasterPK [buildingMasterBuildingId=" + buildingMasterBuildingId + ", roomNumber=" + roomNumber
				+ "]";
	}
	
	
}