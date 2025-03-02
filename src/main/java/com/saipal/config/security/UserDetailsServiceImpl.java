package com.saipal.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saipal.entity.UserInfo;
import com.saipal.repository.UserInfoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Name: " + username));
        
        return UserDetailsImpl.build(userInfo);
    }
}
