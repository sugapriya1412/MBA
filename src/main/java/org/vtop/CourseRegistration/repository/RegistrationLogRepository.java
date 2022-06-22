package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.RegistrationLogModel;


@Repository
public interface RegistrationLogRepository extends JpaRepository<RegistrationLogModel, String>
{
	@Modifying
	@Query(value="INSERT INTO ACADEMICS.REGISTRATION_LOG (REGNO, LOG_STATUS, LOGIN_TIMESTAMP, "+
					"LOGIN_IPADDRESS, ACTIVE_TIMESTAMP) VALUES (?1, 1, CURRENT_TIMESTAMP, ?2, "+
					"CURRENT_TIMESTAMP)", nativeQuery=true)
	void AddRegistrationLog(String registerNumber, String ipAddress);
	
	@Modifying
	@Query("update RegistrationLogModel a set a.logstatus=2, a.logoutTimestamp=CURRENT_TIMESTAMP, "+
			"a.logoutIpaddress=?1 where a.regNo=?2")
	public void UpdateLogoutTimeStamp(String ipAddress, String registerNumber);
	
	@Modifying
	@Query(value="UPDATE ACADEMICS.REGISTRATION_LOG SET ACTIVE_TIMESTAMP=CURRENT_TIMESTAMP where "+
					"REGNO=?1 AND LOG_STATUS=1", nativeQuery=true)
	void UpdateActiveTimeStamp(String registerNumber);

	@Modifying
	@Query(value="UPDATE ACADEMICS.REGISTRATION_LOG SET LOG_STATUS=1, ACTIVE_TIMESTAMP=CURRENT_TIMESTAMP, "+
					"LOGIN_TIMESTAMP=CURRENT_TIMESTAMP, LOGIN_IPADDRESS=?1 where REGNO=?2", nativeQuery=true)
	void UpdateLoginTimeStamp2(String ipAddress, String registerNumber);

	@Modifying
	@Query(value="UPDATE ACADEMICS.REGISTRATION_LOG SET LOG_STATUS=2, LOGOUT_TIMESTAMP=CURRENT_TIMESTAMP, "+
					"LOGOUT_IPADDRESS=?1 where REGNO=?2", nativeQuery=true)
	void UpdateLogoutTimeStamp2(String ipAddress, String registerNumber);
	
	@Modifying
	@Query(value="INSERT INTO ACADEMICS.ERROR_LOG (EXCEPTION_MESSAGE, ERROR_DATETIME, VERTICAL_NAME, "+
					"PACKAGE_NAME, PROGRAMME_NAME, LOG_USERID, LOG_IPADDRESS) VALUES (?1, CURRENT_TIMESTAMP, "+
					"?2, ?3, ?4, ?5, ?6)", nativeQuery=true)
	void InsertErrorLog(String exceptionMessage, String verticalName, String packageName, String programName, 
				String userId, String ipAddress);
	
	
	@Query(value="select to_char(ACTIVE_TIMESTAMP,'DD-MON-YYYY HH24:MI:SS') as active_tmstp, LOG_STATUS, "+
					"to_char(CURRENT_TIMESTAMP,'DD-MON-YYYY HH24:MI:SS') as current_tmstp from "+
					"ACADEMICS.REGISTRATION_LOG where REGNO=?1", nativeQuery=true)
	List<Object[]> findRegistrationLogByRegisterNumber(String registerNumber);
	
	@Query(value="select a.LOG_STATUS, to_number((a.hrs||a.mts||a.scs), '999999') as time_diff from "+ 
					"(select a.LOG_STATUS, (case when (a.hrs < 10) then '0'||to_char(a.hrs,'9') else to_char(a.hrs,'99') end) as hrs, "+ 
					"(case when (a.mins < 10) then '0'||to_char(a.mins,'9') else to_char(a.mins,'99') end) as mts, "+ 
					"(case when (a.secs < 10) then '0'||to_char(a.secs,'9') else to_char(a.secs,'99') end) as scs from "+ 
					"(select LOG_STATUS, extract(hour from (CURRENT_TIMESTAMP - ACTIVE_TIMESTAMP)) as hrs, "+ 
					"extract(minute from (CURRENT_TIMESTAMP - ACTIVE_TIMESTAMP)) as mins, "+ 
					"extract(second from (CURRENT_TIMESTAMP - ACTIVE_TIMESTAMP)) as secs "+ 
					"from ACADEMICS.REGISTRATION_LOG where REGNO=?1) a) a", nativeQuery=true)
	List<Object[]> findRegistrationLogTimeDifference(String registerNumber);
		
	@Query(value="select REASON_TYPE from ACADEMICS.REGISTRATION_EXEMPTION_DETAIL where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+ 
					"and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and LOCK_STATUS=0", nativeQuery=true)
	Integer findRegistrationExemptionReasonTypeBySemesterSubIdAndRegisterNumber(String semesterSubId, String registerNumber);
}
