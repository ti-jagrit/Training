package com.saipal.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.saipal.entity.UserInfo;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private final UserInfo userInfo;

	public UserDetailsImpl(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(userInfo.getUserType().getUserType()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userInfo.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {   
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userInfo.getUserStatus() == 1;
	}

	public static UserDetailsImpl build(UserInfo user) {
		return new UserDetailsImpl(user);
	}
}
