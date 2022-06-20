package org.vtop.CourseRegistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BasketCourseCatalogPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="BASKET_DETAILS_BASKET_ID")
	private String basketId;
	
	@Column(name="COURSE_CATALOG_COURSE_ID")
	private String courseId;
	
	public String getBasketId() {
		return basketId;
	}
	public void setBasketId(String basketId) {
		this.basketId = basketId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basketId == null) ? 0 : basketId.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
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
		BasketCourseCatalogPK other = (BasketCourseCatalogPK) obj;
		if (basketId == null) {
			if (other.basketId != null)
				return false;
		} else if (!basketId.equals(other.basketId))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BasketCourseCatalogPK [basketId=" + basketId + ", courseId=" + courseId + "]";
	}
}
