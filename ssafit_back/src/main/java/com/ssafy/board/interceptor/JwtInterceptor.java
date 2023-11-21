package com.ssafy.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.board.security.JwtTokenUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "Authorization";
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		String requestURI = request.getRequestURI();
		if (!requestURI.contains("/api-user/login") && !requestURI.contains("/api-user/signup")) {
			if (request.getMethod().equals("OPTIONS")) {
				return true;
			}
			
			String token = request.getHeader(HEADER_AUTH);
			if (token == null || !token.startsWith("Bearer ")) {
			    response.setStatus(HttpStatus.UNAUTHORIZED.value());
			    response.getWriter().write("Unauthorized: No Bearer Token Provided");
			    return false;
			}
			
			
		 token = token.substring(7); // Bearer 접두사 제거
	        try {
	            String username = jwtUtil.getUsernameFromToken(token);
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            jwtUtil.validateToken(token, userDetails);
	            return true;
	        } catch (Exception e) {
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.getWriter().write("Unauthorized: Invalid Token");
	            return false;
	        }
	        
	    }
		return true; // 계속 진행
    }

}
