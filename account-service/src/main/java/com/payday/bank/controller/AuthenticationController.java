package com.payday.bank.controller;

import java.util.Map;

import com.payday.bank.domain.AuthenticationRequest;
import com.payday.bank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author anar
 *
 */
@RestController
public class AuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	/**
	 * the service to delegate to.
	 */
	@Autowired
	private AccountService service;
	
	/**
	 * Logins in the user from the authentication request passed in body.
	 * 
	 * @param authenticationRequest The request with username and password.
	 * @return HTTP status CREATED if successful.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public Map<String, Object> login(@RequestBody AuthenticationRequest authenticationRequest) {
		logger.debug("AuthenticationController.login: login request for username: " + authenticationRequest.getUsername());
		Map<String, Object> authenticationResponse = this.service.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		return authenticationResponse;// authToken and accountId;
	}

	/**
	 * Logs out the user.
	 * 
	 * @param userId The user id to log out.
	 */
	@RequestMapping(value = "/logout/{user}", method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void logout(@PathVariable("user") final String userId) {
		logger.debug("AuthenticationController.logout: logout request for userid: " + userId);
		this.service.logout(userId);
	}
	
	/**
	 * To ensure no one does login through HTTP GET.
	 * returns METHOD_NOT_ALLOWED.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.METHOD_NOT_ALLOWED )
	public void get() {
		
	}
}
