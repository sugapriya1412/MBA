package org.vtop.CourseRegistration.model;

public class CurriculumCreditCalculationDto
{
	private String categoryCode;
	private String categoryDescription;
	private int categoryNumber;
	private int categoryCredit;
	private float resultPublishedCredit;
	private float resultUnpublishedCredit;
	private float registeredCredit;
	private float waitingListCredit;
	private float obtainedCredit;
	private float remainingCredit;
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public int getCategoryNumber() {
		return categoryNumber;
	}
	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
	}
	public int getCategoryCredit() {
		return categoryCredit;
	}
	public void setCategoryCredit(int categoryCredit) {
		this.categoryCredit = categoryCredit;
	}
	public float getResultPublishedCredit() {
		return resultPublishedCredit;
	}
	public void setResultPublishedCredit(float resultPublishedCredit) {
		this.resultPublishedCredit = resultPublishedCredit;
	}
	public float getResultUnpublishedCredit() {
		return resultUnpublishedCredit;
	}
	public void setResultUnpublishedCredit(float resultUnpublishedCredit) {
		this.resultUnpublishedCredit = resultUnpublishedCredit;
	}
	public float getRegisteredCredit() {
		return registeredCredit;
	}
	public void setRegisteredCredit(float registeredCredit) {
		this.registeredCredit = registeredCredit;
	}
	public float getWaitingListCredit() {
		return waitingListCredit;
	}
	public void setWaitingListCredit(float waitingListCredit) {
		this.waitingListCredit = waitingListCredit;
	}
	public float getObtainedCredit() {
		return obtainedCredit;
	}
	public void setObtainedCredit(float obtainedCredit) {
		this.obtainedCredit = obtainedCredit;
	}
	public float getRemainingCredit() {
		return remainingCredit;
	}
	public void setRemainingCredit(float remainingCredit) {
		this.remainingCredit = remainingCredit;
	}
	
	@Override
	public String toString() {
		return "CurriculumCreditCalculationDto [categoryCode=" + categoryCode + ", categoryDescription="
				+ categoryDescription + ", categoryNumber=" + categoryNumber + ", categoryCredit=" + categoryCredit
				+ ", resultPublishedCredit=" + resultPublishedCredit + ", resultUnpublishedCredit="
				+ resultUnpublishedCredit + ", registeredCredit=" + registeredCredit + ", waitingListCredit="
				+ waitingListCredit + ", obtainedCredit=" + obtainedCredit + ", remainingCredit=" + remainingCredit
				+ "]";
	}
}
