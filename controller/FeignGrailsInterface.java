package com.oodles.controller;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.oodles.dto.ProjectDto;
import com.oodles.dto.SecurityGroupUserDTO;

@FeignClient(name = "oodles-dashboard", url = "http://"+"${DASHBOARD_GRAILS_HOST_ADDRESS}"+":"+"${DASHBOARD_GRAILS_PORT_NUMBER}")
public interface FeignGrailsInterface {
	
	@GetMapping("/ticket/getUserInformation/{id}")
	public Map<String,Object> getUserInformation(@PathVariable(value="id") Long id, @RequestHeader("Authorization") String headerValue);
	
	@GetMapping("/accountUtility/addProjectPaymentSettings")
	public Map addProjectPaymentSettings(@RequestParam(value="projectName") String projectName,@RequestParam(value="modeOfPayment") Long modeOfPayment,@RequestParam(value="paymentTerms") Long paymentTerms,@RequestParam(value="invoiceCycle") Long invoiceCycle, @RequestHeader("Authorization") String headerValue);
	
	@GetMapping("/accountUtility/getProjectDetailsForAccounts")
	public Map getProjectDetails();
	
	@GetMapping("portfolio/getProjectManagedByUserId")
    public Map<String,Object> getManagerByProjectId(@RequestHeader("Authorization")String accessToken,@RequestParam(value="projectId") Long projectId);

	@GetMapping("ticket/getSupervisorList")
    public Map<String,Object> getSupervisorIdList(@RequestHeader("Authorization")String accessToken,@RequestParam(value="id") Long id);
	
	@GetMapping("businessVertical/getOfficeCodeAndVertical")
	public Map<String,Object> getBusinessVerticals();
	
	@GetMapping("portfolio/findProjectDescription")
	public Map<String,Object> findProjectDescription(@RequestHeader("Authorization")String accessToken,@RequestParam(value="name") String projectName);

	@GetMapping("/payRegister/userDetailsForPayRoll")
	public Map<String,Object> getAllUsers(@RequestHeader("Authorization") String accessToken,@RequestParam(value="userStatus") String userStatus,@RequestParam(value="month") int month,@RequestParam(value="year") int year);
	
	@GetMapping ("/accountUtility/getUserDetails")
	public Map<String,Object> getUserDetails(@RequestHeader("Authorization")String accessToken,@RequestParam(value="id")long id);

	@GetMapping("/attendance/userLeavesdataForPayroll")
	public Map<String,Object> userLeavesdataForPayroll(@RequestHeader("Authorization")String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year,@RequestParam(value="id")long id);
	
	@GetMapping("portfolio/sendMailOnInvoiceStatusChange")
	public Map<String,Object> sendMailOnInvoiceStatusChange(@RequestHeader("Authorization")String accessToken,@RequestParam("data")Map<String,Object> data);

	@GetMapping("portfolio/findProjectDescription")
    public Map<String,Object> getProjectDescription(@RequestParam(value="id")Long id);

	@GetMapping("/accountUtility/sendInvoiceReminderMail")
	public Map<String,Object> sendInvoiceReminderMail(@RequestParam("data")Map<String,Object> data);

	@GetMapping("/accountUtility/getProjectDetailsExceptClosedForAccounts")
	public Map getProjectDetailsExceptClosed();
	
	@GetMapping("/accountUtility/getProjectIdsExceptClosedForAccounts")
	public Map getProjectIdExceptClosed();
	
	@GetMapping("/payRegister/getTimeSheetHours")
	public Map<String,Object> getTimeSheetHours(@RequestHeader("Authorization") String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year,@RequestParam(value="userId") long userId);
	
	@GetMapping("/payRegister/allUsersForPayregister")
	public Map<String,Object> getUsersForPayregister(@RequestHeader("Authorization") String accessToken,@RequestParam(value="userStatus") String userStatus,@RequestParam(value="month") int month);
	
	@GetMapping("/timeTracker/getUserMonthWiseTimesheetData")
	public Map<String,Object> getUserMonthWiseTimesheetData(@RequestHeader("Authorization") String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year,@RequestParam(value="userId") long userId);

	@GetMapping("/accountUtility/getDirectCostExpectedHours")
	public Map<String,Object> getDirectCostExpectedHours(@RequestHeader("Authorization") String accessToken,@RequestParam(value="projectId") long projectId,@RequestParam(value="month") int month,@RequestParam(value="year") int year);
	
	@GetMapping("/user/workingDaysPayroll")
	public Map<String,Object> getWorkingDays(@RequestHeader("Authorization") String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year);

	@GetMapping("/user/getTotalOodlites")
	public Map<String, Object> getTotalOodlites(@RequestHeader("Authorization") String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year);

	@GetMapping("/indirectCost/getCompanyExpectedHours")
	public Map<String, Object> getCompanyExpectedHours(
			/* @RequestHeader("Authorization") String accessToken, */@RequestParam(value="month") int month,@RequestParam(value="year") String year);

	@GetMapping("/indirectCost/getBuWiseProjects")
	public Map<String, Object> getBuWiseProjects(@RequestHeader("Authorization")String accessToken,@RequestParam(value="businessVertical") String businessVertical,@RequestParam(value="month") int month,@RequestParam(value="year") String year);

	@GetMapping("/indirectCost/getBuProjectIds")
	public Map<String, Object> getBuProjectIds(@RequestHeader("Authorization")String accessToken,@RequestParam(value="businessVertical") String businessVertical,@RequestParam(value="month") int month,@RequestParam(value="year") String year);

	@GetMapping("/indirectCost/getProjectDataById")
	public Map<String, Object> getIndirectCostProjects(@RequestHeader("Authorization")String accessToken,@RequestParam(value="projectId") Long id, @RequestParam(value="month") int month,@RequestParam(value="year") String year);

	@GetMapping("/user/payrollWorkingDays")
	public Map<String, Object> getpayrollWorkingDays(
			/* @RequestHeader("Authorization") String accessToken, */@RequestParam(value="month") int month,@RequestParam(value="year") int year);

	@GetMapping("/accountUtility/getexpectedHours")
	public Map<String,Object> getMarginExpectedHours(@RequestHeader("Authorization") String accessToken,@RequestParam(value="projectId") long projectId,@RequestParam(value="month") int month,@RequestParam(value="year") int year);

	@GetMapping("/indirectCost/getBuUsers")
	public Map<String, Object> getBuUsers(
			/* @RequestHeader("Authorization") String accessToken, */ @RequestParam(value="month") int month,@RequestParam(value="year") int year,@RequestParam(value="businessVertical") String businessVertical);

	@GetMapping("/dashboard/getUserName")
	public Map<String,Object> getUserName(@RequestParam(value="userId") Long userId);
	
	@GetMapping("/securityGroupUtility/getAllrolesAndGrades")
	public Map<String, Object> getAllrolesAndGrades(@RequestHeader("Authorization") String accessToken);

	@GetMapping("/securityGroupUtility/getAllActiveUsers" )
	public List<SecurityGroupUserDTO> getAllActiveUsersForSecurityGroup(@RequestHeader("Authorization") String accessToken);
	
	@GetMapping("securityGroupUtility/getUserDetailsByUserId")
	public SecurityGroupUserDTO getUserDetailsByUserId(@RequestHeader("Authorization") String accessToken,@RequestParam Long userId);
	
	@GetMapping("securityGroupUtility/getAllUsersBySecurity")
	public Map<String, Object> getAllUsersBySecurity(@RequestParam String securityType,@RequestParam String security);

	@GetMapping("/payRegister/usersForPayregisterWidget")
	public Map<String,Object> getAllUsersForPayregister(@RequestHeader("Authorization") String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year);
	
	@GetMapping("server/isMyServiceRunning")
	public Map<String, Object> checkConnectionWithGrails();

	@GetMapping("accountUtility/getTokenForCron")
	public Map<String,Object> getTokenForCron(@RequestParam("tokenType") String tokenType);

	@GetMapping("accountUtility/getUserDataForConsolidatedPage")
	public List<Object> getUserDataForConsolidatedPage(@RequestHeader("Authorization") String accessToken, @RequestParam(value="month")int month,@RequestParam(value="year") int year, @RequestParam(value="userStatus")String userStatus,@RequestParam(value="businessVertical") String businessVertical);
	
	@GetMapping("/utility/getStartedProject")
	public List<Object> getMonthlyStartedProjects(@RequestParam(value="month")int month,@RequestParam(value="year") int year,@RequestParam(value="businessVertical")String businessVertical);
	
	@GetMapping("payRegister/getAttendanceDays")
	public Map<String,Object> getAttendanceDays(@RequestHeader("Authorization")String accessToken,@RequestParam(value="month") int month,@RequestParam(value="year") int year,@RequestParam(value="userId")long userId);

	@GetMapping("/utility/getGradeWiseUserList")
	public Map<String, Object> getGradeWiseUserData(@RequestParam("month") int month, @RequestParam("year") int year);

	@GetMapping("/utility/getMonthlyResources")
	public Map<String, Object> getMonthlyResources(@RequestParam("year")int year,@RequestParam("projectId") long projectId);
	
	@GetMapping("/utility/getMonthlyLeaveData")
	public Map<String, Object> getMonthlyLeaveData(@RequestParam("year")int year,@RequestParam("userId") long userId,@RequestParam("month") int month, @RequestParam("projectId")long projectId);

	@GetMapping("/utility/getTeamExpectedhours")
	public List<Object> getTeamExpectedHours(@RequestParam("year") int year,@RequestParam("teamList") List<Object> teamList,@RequestParam("month") int month,@RequestParam("projectId") long projectId);
	
	@GetMapping("/utility/getUserTimesheetData")
	public Map<String, Object> getUserTimesheetData(@RequestHeader("Authorization") String accessToken, @RequestParam List<Long> list, @RequestParam int month, @RequestParam int year);	
	
	@GetMapping("/utility/getUserBillableStatusAndRemarks")
	public Map<Long, Object> getUserBillableStatusAndRemarks(@RequestHeader("Authorization") String accessToken, @RequestParam List<Long> userIdList);
	
	@GetMapping("/payRegister/getUserTimesheet")
	public Map<String, Object> getPayrollUsersTimesheet(@RequestParam("userList")List<Object> users,@RequestParam("month") int month,@RequestParam("year") int year);
	
	@GetMapping("/portfolio/accountsCompliantStatusChange")
	public Object accountsCompliantStatusChange(@RequestParam Long projectId,@RequestParam String comment,@RequestParam boolean compliantStatus,@RequestParam String issueType,@RequestHeader("Authorization") String token,@RequestParam String data, @RequestParam String callFrom);
	
	@GetMapping("/accountUtility/getProjectDetailsForAccounts")
	public Map getProjectDetail();
	
	@GetMapping("/utility/getAllOpenProjects")
	public List<Object> getAllOpenProjects(@RequestHeader("Authorization") String accessToken);
	
	@GetMapping("/utility/getUsersByGrade")
	public Map<String, Object> getUsersByGrade(@RequestHeader("Authorization") String accessToken, @RequestParam String grade);
	
	@GetMapping("/utility/getProjectExpectedHours")
	public Map<String, Object> getProjectExpectedHours(@RequestParam int month, @RequestParam long year, @RequestParam List<Long> projectIds);
	
	@GetMapping("/indirectCost/getBuProjectsForsnapshots")
	public Map<String, Object> getBuProjectsForsnapshots(@RequestParam String businessVertical);
	
	@GetMapping("/utility/projectsForAccountsCompliance")
	public List<Long> getProjectIdListForComplianceCron(@RequestParam int month, @RequestHeader("Authorization") String accessToken, @RequestParam
			int year);
	
	@GetMapping("/accountUtility/getAllProjects")
	public List<ProjectDto> getAllProjects(@RequestHeader("Authorization") String accessToken);
	
	@GetMapping("/utility/gradeWiseExpectedHours")
	public Map<String, Object> gradeWiseExpectedHours(@RequestParam("month") int month, @RequestParam("year") int year);
	
	@GetMapping("/accountUtility/getProjectsForInvoicePipeline")
	public List<Object> getProjectsForInvoicePipeline(@RequestParam("month") int month, @RequestParam("year") int year,@RequestParam("projectType") String projectType);

	@GetMapping("/accountUtility/getProjectsForAverageBilling")
	public List<Object> getProjectsForAverageBilling(@RequestParam("month") int month, @RequestParam("year") int year);

	@GetMapping("/ticket/getConcernedTeamUserIdsForNotification")
	public List<Long> getTeamMemeberUserId(@RequestHeader String acccessToken, @RequestParam String teamName);
	
	@GetMapping("/legalDocuments/legalTeamDataByProjectIds")
	public Map<String, Object> getLegalTeamDataByProjectIds(@RequestHeader("Authorization") String accessToken,
			@RequestParam String buFilter, @RequestParam List<Long> ids, @RequestParam String projectStatus);
	
	@GetMapping("/accountUtility/getProjectDataForComplianceMail")
	public Map<String, Object> getProjectDataForComplianceMail(@RequestHeader("Authorization") String accessToken,@RequestParam("month") int month, @RequestParam("year") int year,@RequestParam("projectId") Long projectId);
	
	@GetMapping("/widget/getActiveProjectsList")
	public Map<String, Object> getAllActiveProjectList(@RequestHeader("Authorization") String accessToken,@RequestParam int month, @RequestParam int year,@RequestParam String projectStatus);

	@GetMapping("/businessVertical/getAllBusinessVertical")
	public Map<String, Object> getBusinessVerticalDetails(@RequestHeader("Authorization") String accessToken);

	@GetMapping("/accountUtility/getDatewiseTeamMembers")
	public List<Object> getDatewiseTeamMembers(@RequestParam Long projectId,@RequestParam Long from,@RequestParam Long to);
}
