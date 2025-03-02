package com.saipal.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private final Logger logger=LoggerFactory.getLogger(JwtAuthFilter.class);
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = jwtUtils.getJwtFromHeader(request);
			if(jwt!=null && jwtUtils.validateJwtToken(jwt)) {
				String username=jwtUtils.getUserNameFromToken(jwt);

				UserDetails userDetails= userDetailsServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken
						(userDetails,null,userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

			
		} catch (Exception e) {
			logger.error("Cannot ser user Authentication: {}",e);
		}
		filterChain.doFilter(request, response);
		
	}

}
