package com.softtek.spring.seguridad.impl;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

public class JwtTokenMalformedException extends AuthenticationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8048273188791083016L;

	public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
