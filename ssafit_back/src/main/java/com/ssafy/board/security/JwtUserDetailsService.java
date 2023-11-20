package com.ssafy.board.security;

import org.springframework.beans.factory.annotation.Autowired;
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
        User entityUser = userdao.findByEmail(email);

        if(entityUser != null) return new JwtUserDetails(entityUser);

        throw new UsernameNotFoundException("Could not find user with email : " + email);
    }
}