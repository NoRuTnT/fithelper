package com.ssafy.board.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil  implements Serializable{
	
	private static final String secretkey = "SSAFIT";
	
	//토큰생성메서드 인자는1개라고 가정 유효기간제거
	public String createToken(String key, String value) {
		try {
			return Jwts.builder()
					.setHeaderParam("alg", "HS256")
					.setHeaderParam("typ", "JWT")
					.claim(key, value)
					.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
					.signWith(SignatureAlgorithm.HS256, secretkey.getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//토큰 유효성 검사
	public void valid(String token) {
		try {
			Jwts.parser().setSigningKey(secretkey.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
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
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
    }

    //check if the token has expired (토큰의 유효기간 검사)
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
