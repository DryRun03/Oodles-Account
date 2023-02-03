package com.oodles.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.ClientModel;
import com.oodles.domain.UserModel;
import com.oodles.dto.SecurityDepositDto;
import com.oodles.security.JwtValidator;
import com.oodles.service.ProjectInvoiceService;
import com.oodles.service.SecurityDepositService;
import com.oodles.util.ConstantUtility;
import com.oodles.util.MessageUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class SecurityDepositController {
	
	@Autowired
	SecurityDepositService secuirtyDepositService;

	@Autowired
	JwtValidator validator;

	@Autowired
	FeignLegacyInterface feignLegacyInterface;
	
	@Autowired
	ProjectInvoiceService projectInvoiceService;

	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@PostMapping(UrlMappings.ADD_SECURITY_DEPOSIT)
	public ResponseEntity<Object> addProjectInvoice(@RequestBody SecurityDepositDto securityDepositDto,
			@RequestHeader String authorization) {
		boolean response = false;
		response = secuirtyDepositService.addSecurityDeposit(securityDepositDto, authorization);
		if (response)
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true, ConstantUtility.SUCCESS,
					securityDepositDto.getProjectId());
		else
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false,
					MessageUtility.getMessage("Please.fill.all.mandatory.fields"), null);

	}

	@GetMapping(UrlMappings.GET_ALL_SECURITY_DEPOSITS)
	public ResponseEntity<Object> getInvoiceData(@RequestHeader String authorization,
			@RequestParam(required = false) String businessVertical) {
//		UserModel currentLoginUser = validator.tokenbValidate(authorization);
//		List<String> roles = currentLoginUser.getRoles();
//		String userGrade = currentLoginUser.getGrade();
//		if (roles.contains("ROLE_ACCOUNTS") || roles.contains("ROLE_ACCOUNTS_ADMIN") || userGrade.equals("M3")
//				|| userGrade.equals("C") || userGrade.equals("D") || userGrade.equals("V")) {
		List<Map<String, Object>> allProjectData = projectInvoiceService.getProjectDetails(authorization);
		Map<String, Object> listAllUsers = (Map<String, Object>) feignLegacyInterface.getUserNameAndDob(authorization);
		List<Map<String, Object>> UserInfo = (List<Map<String, Object>>) listAllUsers.get(ConstantUtility.DATA_);
	    List response = secuirtyDepositService.getSecurityDepositDataV2(authorization,businessVertical, allProjectData, UserInfo);
		//List response = secuirtyDepositService.getSecurityDepositData(authorization, businessVertical);
		if (!response.isEmpty())
			return ResponseHandler.generateResponse(HttpStatus.OK, true,
					MessageUtility.getMessage("data.fetched.successfully"), response);
		else
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false,
					MessageUtility.getMessage(ConstantUtility.FAILED), null);
		// }
//		else
//			return ResponseHandler.generateResponse(HttpStatus.FORBIDDEN, false,
//					MessageUtility.getMessage(ConstantUtility.ACCESS_DENIED), null);

	}

	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@PutMapping(UrlMappings.EDIT_SECURITY_DEPOSIT)
	public ResponseEntity<Object> editProjectInvoice(@RequestBody SecurityDepositDto secuirtyDepositDto,
			@RequestHeader String authorization) {
		boolean response = secuirtyDepositService.editSecurityDeposit(secuirtyDepositDto, authorization);
		if (response)
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true,
					MessageUtility.getMessage(ConstantUtility.SUCCESS), secuirtyDepositDto.getProjectId());
		else
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false,
					MessageUtility.getMessage(ConstantUtility.FAILED), null);

	}

	@PreAuthorize("hasAnyRole('ACCOUNTS','ACCOUNTS_ADMIN')")
	@DeleteMapping(UrlMappings.DELETE_SECURITY_DEPOSIT)
	public ResponseEntity<Object> deleteProjectInvoice(@RequestParam Long id, @RequestHeader String authorization) {
		boolean result = secuirtyDepositService.deleteSecurityDeposit(id, authorization);
		if (result)
			return ResponseHandler.generateResponse(HttpStatus.OK, true,ConstantUtility.DELETED_SUCCESSFULLY, id);
		else
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false,
					MessageUtility.getMessage(ConstantUtility.FAILED), null);
	}
	
	@GetMapping(UrlMappings.GET_PROJECTWISE_SECURITY_DATA)
	public ResponseEntity<Object> getProjectWiseSecurityData(@RequestHeader String authorization, 
			@RequestParam Long projectId) {
		UserModel currentLoginUser = validator.tokenbValidate(authorization);
		List<String> roles = currentLoginUser.getRoles();
		String userGrade = currentLoginUser.getGrade();
		Map<String,Object>data = (Map<String, Object>) feignLegacyInterface.getManagerByProjectId(authorization, projectId).get("data");
		 Integer managerId=	(Integer) data.get("userId");
		//Integer managerId = (Integer) feignGrailsInterface.getManagerByProjectId(authorization, projectId).get("userId");
		List<Long> supervisorsIdList = new ArrayList<>();
		if (managerId != null) {
			if (managerId == currentLoginUser.getUserId()) {
//				Map<String, Object> supervisorsList = feignGrailsInterface.getSupervisorIdList(authorization,
//						Long.valueOf(managerId));
//				supervisorsIdList = (ArrayList<String>) supervisorsList.get("userSupervisorList");
				Map<String, Object> supervisorsList = (Map<String, Object>) feignLegacyInterface.getSupervisorIdList(authorization,
						Long.valueOf(managerId)).get("data") ;
				supervisorsIdList = (List<Long>) supervisorsList.get("userSupervisorList");
				
			}
		}
		if (roles.contains("ROLE_ACCOUNTS") || roles.contains("ROLE_ADMIN") || roles.contains("ROLE_PMO")
				|| roles.contains("ROLE_ACCOUNTS_ADMIN") || userGrade.equals("M3") || userGrade.equals("C")
				|| userGrade.equals("D") || userGrade.equals("V") || managerId.equals(currentLoginUser.getUserId())
				|| supervisorsIdList.contains(Long.toString(currentLoginUser.getUserId()))) {
			List<Object> response = secuirtyDepositService.getProjectWiseSecurityData(authorization,
					projectId);
			if (!response.isEmpty())
				return ResponseHandler.generateResponse(HttpStatus.CREATED, true, ConstantUtility.SUCCESS, response);
			else
				return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false,ConstantUtility.FAILED, null);
		} else {
			return ResponseHandler.generateResponse(HttpStatus.FORBIDDEN, false, ConstantUtility.ACCESS_DENIED, null);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@GetMapping(UrlMappings.GET_PROJECTWISE_SECURITY_DATA_CLIENT)
	public ResponseEntity<Object> getProjectWiseSecurityDataClient(@RequestHeader String authorization, 
			@RequestParam long projectId) {
		List<Object> response = secuirtyDepositService.getProjectWiseSecurityData(authorization,
				projectId);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, true, ConstantUtility.SUCCESS, response);
	}
	
	@GetMapping(UrlMappings.GET_SECURITY_DEPOSITE_STATUS)
    public ResponseEntity<Object> getSecurityDepositeEnumsStatus(@RequestHeader  String authorization){
		List<String> response =secuirtyDepositService.getSecurityDepositeEnumsStatus(authorization);
		if (!response.isEmpty())
			return ResponseHandler.generateResponse(HttpStatus.OK, true,ConstantUtility.SUCCESS, response);
		else
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT,false,ConstantUtility.FAILED,null);
	}

}
