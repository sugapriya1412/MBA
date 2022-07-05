package org.vtop.CourseRegistration.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseAllocationModel;
import org.vtop.CourseRegistration.model.SlotTimeMasterModel;
import org.vtop.CourseRegistration.repository.CourseAllocationRepository;


@Service
@Transactional(readOnly=true)
public class CourseAllocationService
{
	@Autowired private CourseAllocationRepository courseAllocationRepository;
	
	private static final Logger logger = LogManager.getLogger(CourseAllocationService.class);
	
	
	public CourseAllocationModel getOne(String classId)
	{
		return courseAllocationRepository.findById(classId).orElse(null);
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdList2(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, List<String> courseType, 
											String progGroupCode, String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseIdAndCourseType(semesterSubId, classGroupId, 
								classType, courseId, courseType);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeAndClassOption(semesterSubId, 
								classGroupId, classType, courseId, courseType, progGroupCode, progSpecCode, 
								costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdTypeList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, String courseType, String progGroupCode, 
											String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseIdAndCourseType2(semesterSubId, classGroupId, 
								classType, courseId, courseType);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeAndClassOption2(semesterSubId, 
								classGroupId, classType, courseId, courseType, progGroupCode, progSpecCode, 
								costCentreCode);
		}
		
		return tempModelList;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseIdTypeEmpidList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseId, String courseType, String erpId, 
											String progGroupCode, String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeAndEmpId(semesterSubId, classGroupId, 
								classType, courseId, courseType, erpId);
		}
		else
		{
			tempModelList = courseAllocationRepository.findByCourseIdCourseTypeEmpIdAndClassOption(semesterSubId, 
								classGroupId, classType, courseId, courseType, erpId, progGroupCode, progSpecCode, 
								costCentreCode);
		}
		
		return tempModelList;
	}
	
	public CourseAllocationModel getCourseAllocationCourseIdTypeEmpidSlotAssoList(String semesterSubId, 
										String[] classGroupId, String[] classType, String courseId, String courseType, 
										String erpId, Long slotId, String assoClassId, String progGroupCode, 
										String progSpecCode, String costCentreCode)
	{
		CourseAllocationModel tempModel = new CourseAllocationModel();

		if (progGroupCode.equals("RP"))
		{
			tempModel = courseAllocationRepository.findByCourseIdCourseTypeEmpIdSlotIdAndAssoClassId(semesterSubId, 
							classGroupId, classType, courseId, courseType, erpId, slotId, assoClassId);
		}
		else
		{
			tempModel = courseAllocationRepository.findByCourseIdCourseTypeEmpIdSlotIdAssoClassIdAndClassOption(
							semesterSubId, classGroupId, classType, courseId, courseType, erpId, slotId, assoClassId, 
							progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModel;
	}
	
	public List<CourseAllocationModel> getCourseAllocationCourseCodeAvbList(String semesterSubId, String[] classGroupId, 
											String[] classType, String courseCode, String[] courseSystem, String progGroupCode, 
											String progSpecCode, String costCentreCode)
	{
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		
		if (progGroupCode.equals("RP"))
		{
			tempModelList = courseAllocationRepository.findAvailableClassByCourseCode(semesterSubId, classGroupId, 
								classType, courseCode, courseSystem);
		}
		else
		{
			tempModelList = courseAllocationRepository.findAvailableClassByCourseCodeAndClassOption(semesterSubId, 
								classGroupId, classType, courseCode, courseSystem, progGroupCode, progSpecCode, costCentreCode);
		}
		
		return tempModelList;
	}
	
	
	//Filter Course Allocation by Employee Id
	public List<CourseAllocationModel> getAllocationByEmployeeId(List<CourseAllocationModel> camList, String employeeId, 
											String ccCourseSystem, String combineClassId)
	{
		int filterType = 2; 
		List<CourseAllocationModel> tempModelList = new ArrayList<CourseAllocationModel>();
		List<String> classIdList = new ArrayList<String>();
		logger.trace("\n ccCourseSystem: "+ ccCourseSystem +" | combineClassId: "+ combineClassId);
		
		if ((!camList.isEmpty()) && (employeeId != null) && (!employeeId.equals("")))
		{
			if (ccCourseSystem.equals("NONFFCS") || ccCourseSystem.equals("FFCS") || ccCourseSystem.equals("CAL"))
			{
				filterType = 2;
			}
			else
			{
				filterType = 1;
			}
			
			if (filterType == 1)
			{
				for (CourseAllocationModel e : camList)
				{
					if (e.getCombineClassId().equals(combineClassId))
					{
						tempModelList.add(e);
					}
				}
			}
			else
			{
				for (CourseAllocationModel e : camList)
				{
					if (e.getErpId().equals(employeeId))
					{
						classIdList.add(e.getClassId());
					}
				}
				
				if (!classIdList.isEmpty())
				{
					for (CourseAllocationModel e : camList)
					{
						if (classIdList.contains(e.getAssoClassId()))
						{
							tempModelList.add(e);
						}
					}
				}
			}
		}
		
		return tempModelList;
	}	
	
	public Integer getAvailableRegisteredSeats(String classId)
	{
		Integer returnCount = 0;
		
		returnCount = courseAllocationRepository.findAvailableRegisteredSeats(classId);
		if ((returnCount == null) || (returnCount < 0))
		{
			returnCount = 0;
		}
		
		return returnCount;
	}
	
	public Integer getAvailableWaitingSeats(String classId)
	{
		Integer returnCount = 0;
		
		returnCount = courseAllocationRepository.findAvailableWaitingSeats(classId);
		if ((returnCount == null) || (returnCount < 0))
		{
			returnCount = 0;
		}
		
		return returnCount;
	}
	
	public String getClashStatus(Integer patternId, String clashSlot, List<Object[]> allottedSlot, 
							Map<String, List<SlotTimeMasterModel>> slotTimeMapList)
	{
		int clashStatus = 2, allotPatternId = 0;
		long clashStartTime = 0, clashEndTime = 0, allotStartTime = 0, allotEndTime = 0;
		String checkSlot = "NONE", allotSlot = "", clashWeekDay = "", allotWeekDay = "", clashSlotType = "", 
					allotSlotType = "", key = "", key2 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		List<SlotTimeMasterModel> stmModelList = new ArrayList<SlotTimeMasterModel>();
		List<SlotTimeMasterModel> stmModelList2 = new ArrayList<SlotTimeMasterModel>();
				
		try
		{
			logger.trace("\n patternId: "+ patternId +" | clashSlot: "+ clashSlot);
			logger.trace("\n allottedSlot size: "+ allottedSlot.size() +" | slotTimeMapList size: "+ slotTimeMapList.size());
			
			if ((patternId != null) && (patternId > 0) && (clashSlot != null) && (!clashSlot.equals("")) 
					&& (allottedSlot.isEmpty()))
			{
				clashStatus = 1;
			}
			else if ((patternId != null) && (patternId > 0) && (clashSlot != null) && (!clashSlot.equals("")) 
						&& (!allottedSlot.isEmpty()) && (!slotTimeMapList.isEmpty()))
			{
				for (String clhSt : clashSlot.split("/"))
				{
					stmModelList.clear();
										
					key = patternId +"_"+ clhSt;
					if (slotTimeMapList.containsKey(key))
					{
						stmModelList.addAll(slotTimeMapList.get(key));
					}
					//logger.trace("\n Clash=> key: "+ key +" | stmModelList size: "+ stmModelList.size());
										
					if (!stmModelList.isEmpty())
					{
						for (SlotTimeMasterModel stm : stmModelList)
						{
							clashWeekDay = stm.getStmPkId().getWeekdays();
							clashStartTime = Long.parseLong(sdf.format(stm.getStmPkId().getSlotStartingTime()));
							clashEndTime = Long.parseLong(sdf.format(stm.getStmPkId().getSlotEndingTime()));
							clashSlotType = stm.getSlotType();
							//logger.trace("\n clashWeekDay: "+ clashWeekDay +" | clashStartTime: "+ clashStartTime 
							//		+" | clashEndTime: "+ clashEndTime +" | clashSlotType: "+ clashSlotType);
							
							for (Object[] obj : allottedSlot)
							{
								allotPatternId = Integer.parseInt(obj[0].toString());
								allotSlot = obj[1].toString();
								//logger.trace("\n allotPatternId: "+ allotPatternId +" | allotSlot: "+ allotSlot);
								
								for (String altSt : allotSlot.split("\\+"))
								{
									stmModelList2.clear();
										
									key2 = allotPatternId +"_"+ altSt;
									if (slotTimeMapList.containsKey(key2))
									{
										stmModelList2.addAll(slotTimeMapList.get(key2));
									}
									//logger.trace("\n Allot=> key2: "+ key2 +" | stmModelList2 size: "+ stmModelList2.size());
									
									if (!stmModelList2.isEmpty())
									{
										for (SlotTimeMasterModel stm2 : stmModelList2)
										{
											clashStatus = 2;
											checkSlot = "NONE";
											
											allotWeekDay = stm2.getStmPkId().getWeekdays();
											allotStartTime = Long.parseLong(sdf.format(stm2.getStmPkId().getSlotStartingTime()));
											allotEndTime = Long.parseLong(sdf.format(stm2.getStmPkId().getSlotEndingTime()));
											allotSlotType = stm2.getSlotType();
											//logger.trace("\n allotWeekDay: "+ allotWeekDay +" | allotStartTime: "+ allotStartTime 
											//		+" | allotEndTime: "+ allotEndTime +" | allotSlotType: "+ allotSlotType);
											
											if (altSt.equals(clhSt))
											{
												checkSlot = altSt;
												//logger.trace("\n Clash Check Level 1: Failed");
											}
											else if (allotWeekDay.equals(clashWeekDay) && allotSlotType.equals(clashSlotType) 
															&& (((allotStartTime >= clashStartTime) && (allotStartTime <= clashEndTime))
																	|| ((allotEndTime >= clashStartTime) && (allotEndTime <= clashEndTime))))
											{
												checkSlot = altSt;
												//logger.trace("\n Clash Check Level 2: Failed");
											}
											else if (allotWeekDay.equals(clashWeekDay) && (!allotSlotType.equals(clashSlotType))  
															&& (clashSlotType.equals("GENERAL") || allotSlotType.equals("GENERAL")) 
															&& (((allotStartTime >= clashStartTime) && (allotStartTime <= clashEndTime))
																	|| ((allotEndTime >= clashStartTime) && (allotEndTime <= clashEndTime))))
											{
												checkSlot = altSt;
												//logger.trace("\n Clash Check Level 3: Failed");
											}
											else
											{
												clashStatus = 1;
											}
											//logger.trace("\n clashStatus: "+ clashStatus);
											
											if (clashStatus == 2) break;
										}
									}
									
									if (clashStatus == 2) break;
								}
								
								if (clashStatus == 2) break;
							}
							
							if (clashStatus == 2) break;
						}
					}
					
					if (clashStatus == 2) break;
				}
			}
		}
		catch (Exception e)
		{
			logger.trace(e);
		}
		
		return clashStatus +"|"+ checkSlot;
	}
	
	public String getSlotInfoStatus(Integer patternId, String slot, Long buildingId, String buildingCode, List<Object[]> allottedSlot, 
							Map<String, List<SlotTimeMasterModel>> slotTimeMapList, Map<String, Integer> slotFixedInfoList)
	{
		int clashStatus = 2, allotPatternId = 0;
		long allotBuildingId = 0, diffValue = 0;
		String message = "NONE", allotSlot = "", clashWeekDay = "", allotWeekDay = "", key = "", key2 = "", color = "";
				
		List<SlotTimeMasterModel> stmModelList = new ArrayList<SlotTimeMasterModel>();
		List<SlotTimeMasterModel> stmModelList2 = new ArrayList<SlotTimeMasterModel>();
				
		try
		{
			logger.trace("\n patternId: "+ patternId +" | slot: "+ slot 
					+" | buildingId: "+ buildingId +" | buildingCode: "+ buildingCode);
			logger.trace("\n allottedSlot size: "+ allottedSlot.size() 
					+" | slotTimeMapList size: "+ slotTimeMapList.size() 
					+" | slotFixedInfoList size: "+ slotFixedInfoList.size());
			
			if ((patternId != null) && (patternId > 0) && (slot != null) && (!slot.equals("")) 
					&& (allottedSlot.isEmpty()))
			{
				clashStatus = 1;
			}
			else if ((patternId != null) && (patternId > 0) && (slot != null) && (!slot.equals("")) 
						&& (!allottedSlot.isEmpty()) && (!slotTimeMapList.isEmpty()))
			{
				for (String slt : slot.split("\\+"))
				{
					stmModelList.clear();
										
					key = patternId +"_"+ slt;
					if (slotTimeMapList.containsKey(key))
					{
						stmModelList.addAll(slotTimeMapList.get(key));
					}
					//logger.trace("\n Clash=> key: "+ key +" | stmModelList size: "+ stmModelList.size());
										
					if (!stmModelList.isEmpty())
					{
						for (SlotTimeMasterModel stm : stmModelList)
						{
							clashWeekDay = stm.getStmPkId().getWeekdays();
							logger.trace("\n clashWeekDay: "+ clashWeekDay);
							
							for (Object[] obj : allottedSlot)
							{
								allotPatternId = Integer.parseInt(obj[0].toString());
								allotSlot = obj[1].toString();
								allotBuildingId = Long.parseLong(obj[4].toString());
								//logger.trace("\n allotPatternId: "+ allotPatternId +" | allotSlot: "+ allotSlot 
								//		+" | allotBuildingId: "+ allotBuildingId);
								
								for (String altSt : allotSlot.split("\\+"))
								{
									stmModelList2.clear();
										
									key2 = allotPatternId +"_"+ altSt;
									if (slotTimeMapList.containsKey(key2))
									{
										stmModelList2.addAll(slotTimeMapList.get(key2));
									}
									//logger.trace("\n Allot=> key2: "+ key2 +" | stmModelList2 size: "+ stmModelList2.size());
									
									if (!stmModelList2.isEmpty())
									{	
										message = "NONE";
										color = "green";
										
										//Checking the clash
										for (SlotTimeMasterModel stm2 : stmModelList2)
										{
											clashStatus = 2;
											diffValue = 0;
																						
											allotWeekDay = stm2.getStmPkId().getWeekdays();
											//logger.trace("\n allotWeekDay: "+ allotWeekDay);
											
											if (allotWeekDay.equals(clashWeekDay))
											{													
												if (slotFixedInfoList.containsKey(slt +"_"+ altSt))
												{
													diffValue = slotFixedInfoList.get(slt +"_"+ altSt);
												}
												else if (slotFixedInfoList.containsKey(altSt +"_"+ slt))
												{
													diffValue = slotFixedInfoList.get(altSt +"_"+ slt);
												}
												//logger.trace("\n diffValue: "+ diffValue);
												
												if (allotBuildingId != buildingId) 
												{
													if (diffValue > 0)
													{
														message = "Different building block in same week day and having only "+ diffValue 
																		+" minute(s) difference with registered slot "+ allotSlot +".";
													}
													else
													{
														message = "Different building block in same week day with registered slot "+ allotSlot +".";
														clashStatus = 1;
													}
													color = "#1E6B16";
												}
												else if (allotBuildingId == buildingId)
												{
													if (diffValue > 0)
													{
														message = "Same building block in same week day and having only "+ diffValue 
																		+" minute(s) difference with registered slot "+ allotSlot +".";
													}
													else
													{
														message = "Same building block in same week day with registered slot "+ allotSlot +".";
														clashStatus = 1;
													}
													color = "#1E6B16";
												}
												else if (diffValue > 0)
												{
													message = "Having only "+ diffValue +" minute(s) difference with registered slot "+ allotSlot +".";
													color = "#1E6B16";
												}
												else
												{
													clashStatus = 1;
												}													
											}
											else
											{
												clashStatus = 1;
											}
												
											if (clashStatus == 2) break;
											//logger.trace("\n Block & Time Check Status: "+ clashStatus);
										}
									}
									
									if (clashStatus == 2) break;
								}
								
								if (clashStatus == 2) break;
							}
							
							if (clashStatus == 2) break;
						}
					}
					
					if (clashStatus == 2) break;
				}
			}
		}
		catch (Exception e)
		{
			logger.trace(e);
		}
				
		return clashStatus +"|"+ message +"|"+ color;
	}
	
	/*public Map<String, Integer> getSlotFixedInfoList()
	{
		Map<String, Integer> returnMapList = new HashMap<String, Integer>();
		
		//Note: Slot with No. of minutes
		
		returnMapList.put("F1_L3", 1);
		returnMapList.put("F1_L4", 1);
		returnMapList.put("A2_L5", 40);
		returnMapList.put("A2_L6", 40);
		returnMapList.put("L31_L5", 40);
		returnMapList.put("L31_L6", 40);
		returnMapList.put("G1_L9", 1);
		returnMapList.put("G1_L10", 1);
		returnMapList.put("B2_L11", 40);
		returnMapList.put("B2_L12", 40);
		returnMapList.put("L37_L11", 40);
		returnMapList.put("L37_L12", 40);
		returnMapList.put("A1_L15", 1);
		returnMapList.put("A1_L16", 1);
		returnMapList.put("C2_L17", 40);
		returnMapList.put("C2_L18", 40);
		returnMapList.put("L43_L17", 40);
		returnMapList.put("L43_L18", 40);
		returnMapList.put("B1_L21", 1);
		returnMapList.put("B1_L22", 1);
		returnMapList.put("D2_L23", 40);
		returnMapList.put("D2_L24", 40);
		returnMapList.put("L49_L23", 40);
		returnMapList.put("L49_L24", 40);
		returnMapList.put("C1_L27", 1);
		returnMapList.put("C1_L28", 1);
		returnMapList.put("E2_L29", 40);
		returnMapList.put("E2_L30", 40);
		returnMapList.put("L55_L29", 40);
		returnMapList.put("L55_L30", 40);
		returnMapList.put("F2_L33", 1);
		returnMapList.put("F2_L34", 1);
		returnMapList.put("G2_L39", 1);
		returnMapList.put("G2_L40", 1);
		returnMapList.put("A2_L45", 1);
		returnMapList.put("A2_L46", 1);
		returnMapList.put("B2_L51", 1);
		returnMapList.put("B2_L52", 1);
		returnMapList.put("C2_L57", 1);
		returnMapList.put("C2_L58", 1);
		
		return returnMapList;
	}*/
}
