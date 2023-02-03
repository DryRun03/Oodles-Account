package com.oodles.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.service.LoginUtiltiyService;
import com.oodles.util.ErrorCollectionUtil;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class LoginUtilityController {
	
	@Autowired
	LoginUtiltiyService loginUtiltiyService;
	
	@GetMapping(UrlMappings.USER_VERIFY_PATH)
	public ResponseEntity<Object> findTicketsByFilter( BindingResult bindingResult, @RequestHeader("Authorization") String accessToken) throws ParseException {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.errorResponse(ErrorCollectionUtil.getError(bindingResult), HttpStatus.BAD_REQUEST);
		}
		Map tickets = loginUtiltiyService.getUserInfo(accessToken);
		return ResponseHandler.response(tickets, "Tickets");
	}


}
