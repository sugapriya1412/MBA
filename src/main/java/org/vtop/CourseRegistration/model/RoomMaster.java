package org.vtop.CourseRegistration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the ROOM_MASTER database table.
 * 
 */
@Entity
@Table(name="ROOM_MASTER",schema="estates")
@NamedQuery(name="RoomMaster.findAll", query="SELECT r FROM RoomMaster r")
public class RoomMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoomMasterPK id;

	@Column(name="COST_CENTRE_CENTRE_ID")
	private Integer costCentreCentreId;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name="FLOOR_NUMBER")
	private BigDecimal floorNumber;

	@Column(name="LOCK_STATUS")
	private BigDecimal lockStatus;

	@Column(name="LOG_IPADDRESS")
	private String logIpaddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LOG_TIMESTAMP")
	private Date logTimestamp;

	@Column(name="LOG_USERID")
	private String logUserid;

	@Column(name="ROOM_CAPACITY")
	private BigDecimal roomCapacity;

	//bi-directional many-to-one association to BuildingMaster
	@MapsId("buildingMasterBuildingId")
	@ManyToOne
	@JoinColumn(name="BUILDING_MASTER_BUILDING_ID", insertable=false,updatable=false)
	private BuildingMaster buildingMaster;
	
	@Column(name="ROOM_CATEGORY_ID")
	private Integer roomCategoryId;

	//bi-directional many-to-one association to RoomCategory
	@MapsId("roomCategoryId")
	@ManyToOne
	@JoinColumn(name="ROOM_CATEGORY_ID", insertable = false, updatable = false)
	private RoomCategory roomCategory;
	
	@Column(name="ROOM_TYPE_ID")
	private Integer roomTypeId;

	//bi-directional many-to-one association to RoomType
	@MapsId("roomTypeId")
	@ManyToOne
	@JoinColumn(name="ROOM_TYPE_ID", insertable = false, updatable = false)
	private RoomType roomType;

	public RoomMasterPK getId() {
		return id;
	}

	public void setId(RoomMasterPK id) {
		this.id = id;
	}

	public Integer getCostCentreCentreId() {
		return costCentreCentreId;
	}

	public void setCostCentreCentreId(Integer costCentreCentreId) {
		this.costCentreCentreId = costCentreCentreId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEffectiveDate() throws ParseException{
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	      if(this.effectiveDate==null) {
	        //return dateFormat.format(new Date());
	        return null;
	      } else {
	        return dateFormat.format(effectiveDate); 
	      }
	  }

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(BigDecimal floorNumber) {
		this.floorNumber = floorNumber;
	}

	public BigDecimal getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(BigDecimal lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getLogIpaddress() {
		return logIpaddress;
	}

	public void setLogIpaddress(String logIpaddress) {
		this.logIpaddress = logIpaddress;
	}

	public Date getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(Date logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public String getLogUserid() {
		return logUserid;
	}

	public void setLogUserid(String logUserid) {
		this.logUserid = logUserid;
	}

	public BigDecimal getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(BigDecimal roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public BuildingMaster getBuildingMaster() {
		return buildingMaster;
	}

	public void setBuildingMaster(BuildingMaster buildingMaster) {
		this.buildingMaster = buildingMaster;
	}

	public Integer getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Integer roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	public RoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "RoomMaster [id=" + id + ", costCentreCentreId=" + costCentreCentreId + ", description=" + description
				+ ", effectiveDate=" + effectiveDate + ", floorNumber=" + floorNumber + ", lockStatus=" + lockStatus
				+ ", logIpaddress=" + logIpaddress + ", logTimestamp=" + logTimestamp + ", logUserid=" + logUserid
				+ ", roomCapacity=" + roomCapacity + ", buildingMaster=" + buildingMaster + ", roomCategoryId="
				+ roomCategoryId + ", roomCategory=" + roomCategory + ", roomTypeId=" + roomTypeId + ", roomType="
				+ roomType + "]";
	}
	
}