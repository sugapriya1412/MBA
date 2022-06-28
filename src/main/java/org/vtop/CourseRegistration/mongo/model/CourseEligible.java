package org.vtop.CourseRegistration.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="CourseEligible")
public class CourseEligible
{
	@Id
	private String id;
	
	private int	progGroupId;
	private String progEligible;
	private String progCGPA;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProgGroupId() {
		return progGroupId;
	}
	public void setProgGroupId(int progGroupId) {
		this.progGroupId = progGroupId;
	}
	public String getProgEligible() {
		return progEligible;
	}
	public void setProgEligible(String progEligible) {
		this.progEligible = progEligible;
	}
	public String getProgCGPA() {
		return progCGPA;
	}
	public void setProgCGPA(String progCGPA) {
		this.progCGPA = progCGPA;
	}
	
	@Override
	public String toString() {
		return "CourseEligible [id=" + id + ", progGroupId=" + progGroupId + ", progEligible=" + progEligible
				+ ", progCGPA=" + progCGPA + "]";
	}
}
