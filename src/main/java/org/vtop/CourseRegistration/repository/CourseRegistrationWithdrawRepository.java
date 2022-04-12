package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseRegistrationWithdrawModel;
import org.vtop.CourseRegistration.model.CourseRegistrationWithdrawPKModel;


@Repository
public interface CourseRegistrationWithdrawRepository extends JpaRepository
		<CourseRegistrationWithdrawModel, CourseRegistrationWithdrawPKModel>
{		
	@Modifying
	@Query(value="insert into ACADEMICS.COURSE_REG_WITHDRAW_OTP (SEMSTR_DETAILS_SEMESTER_SUB_ID, "+
					"STDNTSLGNDTLS_REGISTER_NUMBER, COURSE_CATALOG_COURSE_ID, OTP_REASON_TYPE, MAIL_OTP, "+
					"MOBILE_OTP, MAIL_OTP_STATUS, MOBILE_OTP_STATUS, OTP_TIMESTAMP, OTP_USERID, OTP_IPADDRESS) "+
					"values (?1, ?2, ?3, ?4, ?5, ?6, 0, 0, CURRENT_TIMESTAMP, ?7, ?8)", nativeQuery=true)
	void insertCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, Integer otpReasonType, 
				String mailOTP, String mobileOTP, String userId, String ipAddress);
	
	@Modifying
	@Query(value="update ACADEMICS.COURSE_REG_WITHDRAW_OTP set MAIL_OTP=?5, MOBILE_OTP=?6, MAIL_OTP_STATUS=0, "+
					"MOBILE_OTP_STATUS=0, OTP_TIMESTAMP=CURRENT_TIMESTAMP, OTP_USERID=?7, OTP_IPADDRESS=?8, "+
					"CONFIRM_OTP_TIMESTAMP=null, CONFIRM_OTP_USERID=null, CONFIRM_OTP_IPADDRESS=null, "+
					"MOBILE_RESPONSE=null where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 "+
					"and COURSE_CATALOG_COURSE_ID=?3 and OTP_REASON_TYPE=?4", nativeQuery=true)
	void updateCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, Integer otpReasonType, 
				String mailOTP, String mobileOTP, String userId, String ipAddress);
	
	@Modifying
	@Query(value="update ACADEMICS.COURSE_REG_WITHDRAW_OTP set MOBILE_RESPONSE=?5 where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and COURSE_CATALOG_COURSE_ID=?3 and OTP_REASON_TYPE=?4", 
					nativeQuery=true)
	void updateCourseWithdrawOTPResponse(String semesterSubId, String registerNumber, String courseId, 
				Integer otpReasonType, String mobileOTPResponse);
	
	@Modifying
	@Query(value="update ACADEMICS.COURSE_REG_WITHDRAW_OTP set MAIL_OTP_STATUS=?5, MOBILE_OTP_STATUS=?6, "+
					"CONFIRM_OTP_TIMESTAMP=CURRENT_TIMESTAMP, CONFIRM_OTP_USERID=?7, CONFIRM_OTP_IPADDRESS=?8 where "+
					"SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"COURSE_CATALOG_COURSE_ID=?3 and OTP_REASON_TYPE=?4", nativeQuery=true)
	void updateWithdrawOTPConfirmationStatus(String semesterSubId, String registerNumber, String courseId, 
				Integer otpReasonType, Integer mailOTPStatus, Integer mobileOTPStatus, String userId, String ipAddress);
	
	@Modifying
	@Query(value="insert into academics.COURSE_REG_WITHDRAW_OTP_BACKUP (SEMSTR_DETAILS_SEMESTER_SUB_ID, "+
					"STDNTSLGNDTLS_REGISTER_NUMBER, COURSE_CATALOG_COURSE_ID, OTP_REASON_TYPE, MAIL_OTP, "+
					"MOBILE_OTP, MAIL_OTP_STATUS, MOBILE_OTP_STATUS, OTP_TIMESTAMP, OTP_USERID, OTP_IPADDRESS, "+
					"CONFIRM_OTP_TIMESTAMP, CONFIRM_OTP_USERID, CONFIRM_OTP_IPADDRESS, MOBILE_RESPONSE, "+
					"BACKUP_OTP_TIMESTAMP, BACKUP_OTP_USERID, BACKUP_OTP_IPADDRESS) "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, STDNTSLGNDTLS_REGISTER_NUMBER, COURSE_CATALOG_COURSE_ID, "+
					"OTP_REASON_TYPE, MAIL_OTP, MOBILE_OTP, MAIL_OTP_STATUS, MOBILE_OTP_STATUS, OTP_TIMESTAMP, "+
					"OTP_USERID, OTP_IPADDRESS, CONFIRM_OTP_TIMESTAMP, CONFIRM_OTP_USERID, CONFIRM_OTP_IPADDRESS, "+
					"MOBILE_RESPONSE, CURRENT_TIMESTAMP, ?5, ?6 from academics.COURSE_REG_WITHDRAW_OTP where "+
					"SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"COURSE_CATALOG_COURSE_ID=?3 and OTP_REASON_TYPE=?4)", nativeQuery=true)
	void insertCRWOTPBackup(String semesterSubId, String registerNumber, String courseId, Integer otpReasonType, 
				String userId, String ipAddress);
	
	
	//Course Delete/Update OTP  
	@Query(value="select MOBILE_OTP, MAIL_OTP from ACADEMICS.COURSE_REG_WITHDRAW_OTP where "+
					"SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"COURSE_CATALOG_COURSE_ID=?3 and OTP_REASON_TYPE=?4", nativeQuery=true)
	List<Object[]> findCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
						Integer otpReasonType);
	
	@Query(value="select a.hist_type, a.COURSE_CATALOG_COURSE_ID, a.CODE, a.GENERIC_COURSE_TYPE, a.hist_date from ("+
					"(select 1 as hist_type, a.COURSE_CATALOG_COURSE_ID, b.CODE, b.GENERIC_COURSE_TYPE, "+
					"to_char(a.LOG_TIMESTAMP,'DD-MON-YYYY') as hist_date from ACADEMICS.COURSE_REGISTRATION_WITHDRAW a, "+
					"ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE=?2) "+
					"union all "+
					"(select 2 as hist_type, a.COURSE_CATALOG_COURSE_ID, b.CODE, b.GENERIC_COURSE_TYPE, "+
					"to_char(a.LOG_TIMESTAMP,'DD-MON-YYYY') as hist_date from ACADEMICS.COURSE_REGISTRATION_WITHDRAW a, "+
					"ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and (b.CODE in (select EQUIVALENT_COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2 and COURSE_CODE<>EQUIVALENT_COURSE_CODE) "+
					"or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2 "+
					"and COURSE_CODE<>EQUIVALENT_COURSE_CODE)))) a where a.CODE not in "+ 
					"(select b.CODE from ACADEMICS.COURSE_REG_WITHDRAW_REQUEST a, ACADEMICS.COURSE_CATALOG b where "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.WITHDRAWN_STATUS in (2) and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) order by a.hist_type, a.COURSE_CATALOG_COURSE_ID", 
					nativeQuery=true)
	List<Object[]> findByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode);
}
