package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProgrammeGroupBasketDetailsPK implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Column(name="BASKET_DETAILS_BASKET_ID")
	private String basketId;
	
	@Column(name="PRGRM_GROUP_PROGRAMME_GROUP_ID")
	private Integer groupId;

	public String getBasketId() {
		return basketId;
	}

	public void setBasketId(String basketId) {
		this.basketId = basketId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basketId == null) ? 0 : basketId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgrammeGroupBasketDetailsPK other = (ProgrammeGroupBasketDetailsPK) obj;
		if (basketId == null) {
			if (other.basketId != null)
				return false;
		} else if (!basketId.equals(other.basketId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}

	
	
	
}
