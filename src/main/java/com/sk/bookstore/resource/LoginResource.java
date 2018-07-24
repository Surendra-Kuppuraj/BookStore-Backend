package com.sk.bookstore.resource;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.resource.constant.MessageEnum;
import com.sk.bookstore.resource.util.ResponseMessage;

@RestController
public class LoginResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

	@GetMapping("/token")
	public Map<String, String> token(final HttpSession session, final HttpServletRequest request) {
		LOGGER.info("Requested Remote Host: " + request.getRemoteHost());
		LOGGER.info("Requested Remote Host Port: " + request.getRemotePort());
		LOGGER.info("Requested Remote Host: " + request.getRemoteAddr());
		return Collections.singletonMap("token", session.getId());
	}

	@GetMapping("/isSessionExists")
	public ResponseEntity<ResponseMessage> isSessionExists() {
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.SESSION_ACTIVATE), HttpStatus.OK);
	}
	
	@PostMapping("/user/logout")
	public ResponseEntity<ResponseMessage> logout() {
		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(new ResponseMessage(MessageEnum.LOGOUT_SUCCESS), HttpStatus.OK);
	}
}
