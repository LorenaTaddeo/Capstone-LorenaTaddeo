package com.capstone.project.security.service;

import com.capstone.project.security.payload.LoginDto;
import com.capstone.project.security.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
