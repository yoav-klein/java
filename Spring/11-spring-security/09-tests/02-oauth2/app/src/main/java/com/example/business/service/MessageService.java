package com.example.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @PreAuthorize("principal.username == 'yoav'")
	public String getMessage() {
		Authentication authentication = SecurityContextHolder.getContext()
			.getAuthentication();
		return "Hello " + authentication;
	}

	@PreAuthorize("authenticated")
	public String onlyAuthenticated() {
		return "Hello";
	}
}
