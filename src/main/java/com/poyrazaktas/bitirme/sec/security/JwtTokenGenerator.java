package com.poyrazaktas.bitirme.sec.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {
    @Value("${poyrazaktas.bitirme.jwt.security.app_key}")
    private String APP_KEY;

    @Value("${poyrazaktas.bitirme.jwt.security.expire_time}")
    private Long EXPIRE_TIME;

    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        String token = Jwts.builder()
                .setSubject(jwtUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_KEY)
                .compact();

        return token;
    }

    public String findUserNameByToken(String token) {
        Jws<Claims> claimsJws = parseToken(token);
        String userName = claimsJws.getBody().getSubject();
        return userName;
    }

    public boolean validateToken(String token) {
        boolean isValid;

        try {
            Jws<Claims> claimsJws = parseToken(token);
            isValid = !isTokenExpired(claimsJws);
        } catch (Exception e) {
            isValid = false;
        }

        return isValid;
    }

    private Jws<Claims> parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_KEY).parseClaimsJws(token);
        return claimsJws;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {
        Date expirationDate = claimsJws.getBody().getExpiration();
        return expirationDate.before(new Date());
    }
}
