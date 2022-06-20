package org.vtop.CourseRegistration.model;

public class StudentCGPAData
{
	private String registerNo;
	private String creditRegistered;
	private String creditEarned;
	private String CGPA;
	private String totalArrear;
	private String totalCourse;
	private String SGradeCount;
	private String aGradeCount;
	private String bGradeCount;
	private String cGradeCount;
	private String dGradeCount;
	private String eGradeCount;
	private String fGradeCount;
	private String nGradeCount;
	private Integer pgmSpecId;
	
	public String getCreditRegistered() {
		return creditRegistered;
	}
	public void setCreditRegistered(String creditRegistered) {
		this.creditRegistered = creditRegistered;
	}
	public String getCreditEarned() {
		return creditEarned;
	}
	public void setCreditEarned(String creditEarned) {
		this.creditEarned = creditEarned;
	}
	public String getCGPA() {
		return CGPA;
	}
	public void setCGPA(String cGPA) {
		CGPA = cGPA;
	}
	public String getTotalArrear() {
		return totalArrear;
	}
	public void setTotalArrear(String totalArrear) {
		this.totalArrear = totalArrear;
	}
	public String getTotalCourse() {
		return totalCourse;
	}
	public void setTotalCourse(String totalCourse) {
		this.totalCourse = totalCourse;
	}
	public String getSGradeCount() {
		return SGradeCount;
	}
	public void setSGradeCount(String sGradeCount) {
		SGradeCount = sGradeCount;
	}
	public String getaGradeCount() {
		return aGradeCount;
	}
	public void setaGradeCount(String aGradeCount) {
		this.aGradeCount = aGradeCount;
	}
	public String getbGradeCount() {
		return bGradeCount;
	}
	public void setbGradeCount(String bGradeCount) {
		this.bGradeCount = bGradeCount;
	}
	public String getcGradeCount() {
		return cGradeCount;
	}
	public void setcGradeCount(String cGradeCount) {
		this.cGradeCount = cGradeCount;
	}
	public String getdGradeCount() {
		return dGradeCount;
	}
	public void setdGradeCount(String dGradeCount) {
		this.dGradeCount = dGradeCount;
	}
	public String geteGradeCount() {
		return eGradeCount;
	}
	public void seteGradeCount(String eGradeCount) {
		this.eGradeCount = eGradeCount;
	}
	public String getfGradeCount() {
		return fGradeCount;
	}
	public void setfGradeCount(String fGradeCount) {
		this.fGradeCount = fGradeCount;
	}
	public String getnGradeCount() {
		return nGradeCount;
	}
	public void setnGradeCount(String nGradeCount) {
		this.nGradeCount = nGradeCount;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public Integer getPgmSpecId() {
		return pgmSpecId;
	}
	public void setPgmSpecId(Integer pgmSpecId) {
		this.pgmSpecId = pgmSpecId;
	}
	
	@Override
	public String toString() {
		return "StudentCGPAData [registerNo=" + registerNo + ", creditRegistered=" + creditRegistered
				+ ", creditEarned=" + creditEarned + ", CGPA=" + CGPA + ", totalArrear=" + totalArrear
				+ ", totalCourse=" + totalCourse + ", SGradeCount=" + SGradeCount + ", aGradeCount=" + aGradeCount
				+ ", bGradeCount=" + bGradeCount + ", cGradeCount=" + cGradeCount + ", dGradeCount=" + dGradeCount
				+ ", eGradeCount=" + eGradeCount + ", fGradeCount=" + fGradeCount + ", nGradeCount=" + nGradeCount
				+ ", pgmSpecId=" + pgmSpecId + "]";
	}
}
