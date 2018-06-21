/**
 * 
 */
package com.sk.bookstore.domain.security;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Surendra Kumar
 *
 */
public class Authority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 18762390L;

	private final String authority;

	public Authority(final String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
