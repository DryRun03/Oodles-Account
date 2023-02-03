package com.oodles.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.util.ConstantUtility;
import com.oodles.util.ResponseHandler;
import com.oodles.util.UrlMappings;

@RestController
public class ServerController {
	@Autowired
	private FeignGrailsInterface feignGrailsInterface;

	@GetMapping(value = UrlMappings.CHECK_IS_SERVER_RUNNING)
	public Object isServerRunning() {
		return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS,null);
	}
//	@GetMapping(value = UrlMappings.IS_CONNECTED)
//	public Object isConnectedWithGrails() {
//		Map<String, Object> response = feignGrailsInterface.checkConnectionWithGrails();
//		if (response != null) {
//			if ("success".equals(response.get("result")))
//				return ResponseHandler.generateResponse(HttpStatus.OK, true, ConstantUtility.SUCCESS,
//						response.get("result"));
//		}
//		return ResponseHandler.generateResponse(HttpStatus.SERVICE_UNAVAILABLE, true, ConstantUtility.SUCCESS, null);
//	}
	
}