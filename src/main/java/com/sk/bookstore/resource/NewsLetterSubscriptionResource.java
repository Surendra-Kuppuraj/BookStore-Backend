/**
 * 
 */
package com.sk.bookstore.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.bookstore.resource.util.ResponseMessage;

/**
 * @author Surendra Kumar
 *
 */
@RestController
@RequestMapping("/newsletter")
public class NewsLetterSubscriptionResource {

	@PostMapping 
	public ResponseEntity<ResponseMessage> subscribe(@RequestBody final String emailAddress) {
		return null;
	}
}
