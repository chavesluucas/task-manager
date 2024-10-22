package com.task.manager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private static final int EXPIRATION_TOKEN_ONE_HOUR = 3600000;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String JWT_KEY = "signinKey";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORITIES = "authorities";
    private static final String ACCESS_CONTROL = "Access-Control-Expose-Headers";
    private static final String AUTH_FAILED = "Authentication failed";

    static public void addJWTToken(HttpServletResponse response, Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();

        List<String> authCollections = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put(AUTHORITIES, authCollections);

        String jwtToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN_ONE_HOUR))
                .signWith(SignatureAlgorithm.HS512, JWT_KEY)
                .addClaims(claims)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, BEARER + jwtToken);
        response.addHeader(ACCESS_CONTROL, HEADER_AUTHORIZATION);
    }

    static public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);

        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(JWT_KEY)
                    .parseClaimsJws(token.replace(BEARER, ""))
                    .getBody();

            if (user != null) {

                List<SimpleGrantedAuthority> permissions = ((ArrayList<String>)user.get(AUTHORITIES))
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                return new UsernamePasswordAuthenticationToken(user, null, permissions);
            } else {
                throw new RuntimeException(AUTH_FAILED);
            }

        }
        return null;
    }

}
