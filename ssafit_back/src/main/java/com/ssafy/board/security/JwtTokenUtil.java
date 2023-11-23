package com.ssafy.board.security;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 60 * 60* 24; //토큰의 기한 
    
    @Value("${jwt.secret}")
    private String secret;
    
    //retrieve username from jwt token(토큰으로부터 사용자 이름 가져오기)
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token(토큰으로부터 유효기간 가져오기)
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key(토큰에 저장된 모든정보 가져오기)
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired (토큰의 유효기간 검사)
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user (토큰 생성)
    public String generateToken(JwtUserDetails userDetails) {
    	System.out.println("Generating token for Email: " + userDetails.getUsername() + ", Nickname: " + userDetails.getNickname()+"역할:"+ userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));	
        Map<String, Object> claims = new HashMap<>();
        String encodedNickname = new String(userDetails.getNickname().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        claims.put("email", userDetails.getUsername());
        claims.put("userId",userDetails.getUserId());
        claims.put("cash",userDetails.getCash());
        claims.put("totalcash",userDetails.getTotalcash());
        claims.put("nickname", encodedNickname);
        claims.put("role", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
            );
        // 필요한 다른 정보들을 여기에 추가합니다.
        return doGenerateToken(claims,userDetails.getUsername());
    }

    //while creating the token - (토큰에 정보를 넣고, 시크릿 키를 이용해서 토큰을 compact하게 만든다)
    private String doGenerateToken(Map<String, Object> claims,String email) {
    	System.out.println("Claims used for token generation: " + claims);
        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token (토큰의 유효여부를 검사한다)
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final List<String> roles = getAllClaimsFromToken(token).get("role", List.class);
        // 역할에 따른 추가적인 검증 로직 구현
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}