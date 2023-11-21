package com.ssafy.board.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.model.dto.User;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userdao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userdao.findByEmail(email); //사용자가 존재하지않을경우 예외처리필요
        List<SimpleGrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
            );
        return new JwtUserDetails(user, authorities);
    }
}