package com.ssafy.board.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.board.model.dto.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class JwtUserDetails implements UserDetails {

    private User user;
	private List<SimpleGrantedAuthority> authorities;

    public JwtUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        super();
        this.user = user;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // 사용자의 이메일을 사용자명으로 반환
        return user.getEmail();
    }

    // 추가적인 사용자 정보를 반환하는 메서드
    public String getNickname() {
        return user.getNickname(); // User 객체에 nickname 필드가 있다고 가정
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
        return true;
    }


	


}