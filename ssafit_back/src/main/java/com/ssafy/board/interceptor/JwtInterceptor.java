package com.ssafy.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.board.util.JwtUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "Authorization";
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		String requestURI = request.getRequestURI();
		if (!requestURI.contains("/api-user/login") && !requestURI.contains("/api-user/signup")) {
			if (request.getMethod().equals("OPTIONS")) {
				return true;
			}
			
			String token = request.getHeader(HEADER_AUTH);
			
			try {
				if (token != null && token.startsWith("Bearer ")) {
					token = token.substring(7);
					jwtUtil.valid(token);
					return true;
				} else {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.getWriter().write("Unauthorized: No Bearer Token Provided");
					return false;
				}
			} catch (Exception e) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("Unauthorized: Invalid Token");
				return false;
			}
	        
	    }
		return true; // 계속 진행
    }

}
